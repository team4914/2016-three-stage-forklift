// RobotBuilder Version: 2.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.


package org.usfirst.frc4914.threestagevision;

// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.TalonSRX;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.VictorSP;

// END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    public static SpeedController drivetrainRightSide;
    public static SpeedController drivetrainLeftSide;
    public static SpeedController shooterTopFlywheel;
    public static SpeedController shooterBottomFlywheel;
    public static SpeedController shooterIntakeRoller;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS

    public static void init() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
        drivetrainRightSide = new Victor(8);
        LiveWindow.addActuator("Drivetrain", "Right Side", (Victor) drivetrainRightSide);
        
        drivetrainLeftSide = new Victor(9);
        LiveWindow.addActuator("Drivetrain", "Left Side", (Victor) drivetrainLeftSide);
        
        shooterTopFlywheel = new VictorSP(3);
        LiveWindow.addActuator("Shooter", "Top Flywheel", (VictorSP) shooterTopFlywheel);
        
        shooterBottomFlywheel = new VictorSP(0);
        LiveWindow.addActuator("Shooter", "Bottom Flywheel", (VictorSP) shooterBottomFlywheel);
        
        shooterIntakeRoller = new TalonSRX(2);
        LiveWindow.addActuator("Shooter", "Intake Roller", (TalonSRX) shooterIntakeRoller);
        

        // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
    }
}
