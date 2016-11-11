// RobotBuilder Version: 2.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.


package org.usfirst.frc4914.threestage;

import com.ni.vision.NIVision;
import com.ni.vision.NIVision.DrawMode;
import com.ni.vision.NIVision.Image;
import com.ni.vision.NIVision.ShapeMode;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc4914.threestage.commands.*;
import org.usfirst.frc4914.threestage.subsystems.*;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {

    CommandGroup autonomousCommand;
    SendableChooser autoChooser;
    SendableChooser autoTurnChooser;
    static char currentServer = 'f';

    public static OI oi;
    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    public static Drivetrain drivetrain;
    public static Shooter shooter;
    public static MotorizedArm motorizedArm;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS

    // static CameraServer server;        
    static int session;
    public static boolean switchRequest = false;
    Image frame;
    NIVision.Rect highGoalHorizontal, highGoalVertical;
    NIVision.Rect lowGoalVertical;
    
    // distance from top of image for horizontal line
    public static final int horizontalPos = 340;
    // distance from left of image or vertical line
    public static final int verticalPos = 397;
    // thickness of goal markers
    public static final int markerThickness = 10;
    // length of goal markers
    public static final int markerLength = 180;
    
    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
    RobotMap.init();
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
        drivetrain = new Drivetrain();
        shooter = new Shooter();
        motorizedArm = new MotorizedArm();

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
        // don't touch the next line.
        oi = new OI();
        
        // creates autonomous chooser interface on SmartDashboard
        autoChooser = new SendableChooser();
        autoChooser.addDefault("Rock Wall/Ramparts/Rough Terrain", 
        		new DriveStraight(0.4, 7));
        autoChooser.addObject("Moat", new Moat());
        SmartDashboard.putData("Auto Chooser", autoChooser);
        
        // creates autonomous turning chooser on SmartDashboard
        autoTurnChooser = new SendableChooser();
        autoTurnChooser.addDefault("Do nothing", new CommandGroup());
        autoTurnChooser.addObject("Turn Right", new AutoTurnRight());
        autoTurnChooser.addObject("Turn Left", new AutoTurnLeft());
        SmartDashboard.putData("Auto Turn", autoTurnChooser);
        
        // NIVision camera code
        frame = NIVision.imaqCreateImage(NIVision.ImageType.IMAGE_RGB, 1);
        session = NIVision.IMAQdxOpenCamera("cam1",
        		NIVision.IMAQdxCameraControlMode.CameraControlModeController);
        NIVision.IMAQdxConfigureGrab(session);

        // sets arm to brake mode
        // Robot.motorizedArm.setBrake(true);
        // resets encoder
        Robot.motorizedArm.resetEncoder();
    }

    /**
     * This function is run once the robot gets disabled
     * Safely stops all subsystems and closes camera feed
     */
    public void disabledInit() {
    	// safely stops all subsystems
    	Robot.drivetrain.stop();
    	Robot.shooter.stop();
    	Robot.motorizedArm.stop();
    	
    	// closes camera
    	NIVision.IMAQdxStopAcquisition(session);
    }

    /**
     * This function runs periodically after the robot is disabled
     */
    public void disabledPeriodic() {
        Scheduler.getInstance().run();
    }

    /**
     * This function runs once autonomous begins
     */
    public void autonomousInit() {
    	autonomousCommand = new CommandGroup();
    	// constructs autonomous command based on SmartDashboard inputs
    	autonomousCommand.addSequential((Command) autoChooser.getSelected());
    	autonomousCommand.addSequential((Command) autoTurnChooser.getSelected());
        
        // begins autonomous command
        if (autonomousCommand != null) autonomousCommand.start();
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
        
        // DEBUG
        System.out.println(Robot.drivetrain.getLeftSpeed());
    }

    /**
     * This function is called once teleop begins
     */
    public void teleopInit() {
    	// stops autonomous command from running
        if (autonomousCommand != null) autonomousCommand.cancel();
        
        // warms up flywheels
        Command postAutoRunFlywheel = new ToggleHighGoalSpeeds();
        postAutoRunFlywheel.start();
        
        // begins image acquisition
        NIVision.IMAQdxStartAcquisition(session);
        // creates bounds for image overlay
        highGoalHorizontal = new NIVision.Rect(horizontalPos - 5, 260, markerThickness, markerLength);
        highGoalVertical = new NIVision.Rect(200, verticalPos - 5, markerLength, markerThickness);
        lowGoalVertical = new NIVision.Rect(0, 320 - 5, 480, markerThickness);
    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
        Scheduler.getInstance().run();
        
        boolean inverted = Robot.drivetrain.inverted;
        double lSpeed = 0;
        double rSpeed = 0;

        // driver inputs
        lSpeed += Robot.oi.driverLJ();
        rSpeed += Robot.oi.driverRJ();
        
        if (inverted) { // switches left and right speeds and multiplies by -1
        	double temp = lSpeed;
        	lSpeed = -rSpeed;
        	rSpeed = -temp;
        }
        
        // codriver fine turning
        lSpeed += Robot.oi.codriverZ() * 0.35;
        rSpeed += -Robot.oi.codriverZ() * 0.35;
        
        // codriver fine distance
        /*
        lSpeed += Robot.oi.codriverX() * 0.25;
        rSpeed += Robot.oi.codriverX() * 0.25;
        */
        
        // passing values to speed controllers
        Robot.drivetrain.setLeftSide(lSpeed);
        Robot.drivetrain.setRightSide(rSpeed);

        // if behind backward setpoint, only allows arm to move forward
        if (Robot.motorizedArm.getEncoder() > MotorizedArm.backwardLimit) {
        	Robot.motorizedArm.setMotors(Robot.oi.driverRT()*-0.25);
        }
        // if below forward setpoint, only allows arm to move backward
        else if (Robot.motorizedArm.getEncoder() < MotorizedArm.forwardLimit) {
        	Robot.motorizedArm.setMotors(Robot.oi.driverLT()*0.25);
        }
        // if between backward and forward setpoint, allows free control
        else {
	        // free arm control
	        if (Robot.oi.driverLT() < 0.1) {
	        	// passes control to left trigger (bringing up)
	        	Robot.motorizedArm.setMotors(Robot.oi.driverRT()*-0.25);
	        } else if (Robot.oi.driverRT() < 0.1) {
	        	// passes control to right trigger (bringing down)
	        	Robot.motorizedArm.setMotors(Robot.oi.driverLT()*0.25);
	        }
        }
        
        // DEBUG
        // System.out.println("Top: " + Robot.shooter.getBottomFlywheelSpeed());
        // System.out.println("Btm: " + Robot.shooter.getTopFlywheelSpeed());
        System.out.println("Enc: " + Robot.motorizedArm.getEncoder());
        
        
        // if camera switch requested
        if (switchRequest) {
        	// stops acquisition from current camera
        	NIVision.IMAQdxStopAcquisition(session);
        	// closes operation of current camera
        	NIVision.IMAQdxCloseCamera(session);
        	
        	// switches camera server from front to back
        	if (currentServer == 'f') {
        		session = NIVision.IMAQdxOpenCamera("cam0",
        				NIVision.IMAQdxCameraControlMode.CameraControlModeController);
        		currentServer = 'b';
        	} 
        	// switches camera server from back to front
        	else if (currentServer == 'b') {
                session = NIVision.IMAQdxOpenCamera("cam1",
                		NIVision.IMAQdxCameraControlMode.CameraControlModeController);
        		currentServer = 'f';
        	}
        	
        	// reconfigures grab from newly set camera
        	NIVision.IMAQdxConfigureGrab(session);
        	// restarts acquisition from newly set camera
        	NIVision.IMAQdxStartAcquisition(session);
        	// resets switchRequest
        	switchRequest = false;
        }
        // regular camera operation; no camera switch
        else {
        	// sets frame to grabbed image from camera
	        NIVision.IMAQdxGrab(session, frame, 1);
		    
	        // high goal markers
	        if (currentServer == 'f') {
	        	NIVision.imaqDrawShapeOnImage(frame, frame, highGoalHorizontal,
	                DrawMode.PAINT_VALUE, ShapeMode.SHAPE_RECT, 0x00ffff);
	        	NIVision.imaqDrawShapeOnImage(frame,  frame,  highGoalVertical,
	        		DrawMode.PAINT_VALUE, ShapeMode.SHAPE_RECT, 0x00ffff);
	        }
	        // low goal markers
	        else if (currentServer == 'b') {
	        	NIVision.imaqDrawShapeOnImage(frame,  frame,  lowGoalVertical,
	        		DrawMode.PAINT_VALUE, ShapeMode.SHAPE_RECT, 0x00ffff);
	        }
	        
	        // sends image to cameraServer
	        CameraServer.getInstance().setImage(frame);
        }
    }

    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
        LiveWindow.run();
    }
    
    /**
     * Changes camera from front to back, or vice versa
     */
    public static void switchCamera() {
    	switchRequest = true;
    }
}
