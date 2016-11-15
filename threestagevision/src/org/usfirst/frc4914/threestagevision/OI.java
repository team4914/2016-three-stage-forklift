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

import org.usfirst.frc4914.threestagevision.commands.*;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.*;
import org.usfirst.frc4914.threestagevision.subsystems.*;


/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
    //// CREATING BUTTONS
    // One type of button is a joystick button which is any button on a joystick.
    // You create one by telling it which joystick it's on and which button
    // number it is.
    // Joystick stick = new Joystick(port);
    // Button button = new JoystickButton(stick, buttonNumber);

    // There are a few additional built in buttons you can use. Additionally,
    // by subclassing Button you can create custom triggers and bind those to
    // commands the same as any other Button.

    //// TRIGGERING COMMANDS WITH BUTTONS
    // Once you have a button, it's trivial to bind it to a button in one of
    // three ways:

    // Start the command when the button is pressed and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenPressed(new ExampleCommand());

    // Run the command while the button is being held down and interrupt it once
    // the button is released.
    // button.whileHeld(new ExampleCommand());

    // Start the command when the button is released  and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenReleased(new ExampleCommand());


    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    public JoystickButton x;
    public Joystick driverController;
    public JoystickButton codriverTrigger;
    public JoystickButton codriverThumb;
    public JoystickButton codriverRSideButton;
    public Joystick codriverController;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS

    public OI() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS

        codriverController = new Joystick(1);
        
        codriverRSideButton = new JoystickButton(codriverController, 4);
        codriverRSideButton.whenPressed(new ToggleHighGoalSpeeds());
        codriverThumb = new JoystickButton(codriverController, 2);
        codriverThumb.whileHeld(new ReverseIntake());
        codriverTrigger = new JoystickButton(codriverController, 1);
        codriverTrigger.whileHeld(new RunIntake());
        driverController = new Joystick(0);
        
        x = new JoystickButton(driverController, 3);
        x.whenPressed(new InvertDrive());


        // SmartDashboard Buttons
        SmartDashboard.putData("Autonomous Command", new AutonomousCommand());
        SmartDashboard.putData("Toggle High Goal Speeds", new ToggleHighGoalSpeeds());
        SmartDashboard.putData("Invert Drive", new InvertDrive());

        // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
    }

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=FUNCTIONS
    public Joystick getDriverController() {
        return driverController;
    }

    public Joystick getCodriverController() {
        return codriverController;
    }


    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=FUNCTIONS
    
 // driver controller rumble
    public void setDriverRumble(float value) {
    	driverController.setRumble(Joystick.RumbleType.kRightRumble, value);
    }
    
    // driver controller joysticks
    public double driverLJ() { return Math.round(driverController.getRawAxis(5) * 10000.0) / 10000.0; }
    public double driverRJ() { return Math.round(driverController.getRawAxis(1) * 10000.0) / 10000.0; }
    public double driverLT() { return Math.round(driverController.getRawAxis(2) * 10000.0) / 10000.0; }
    public double driverRT() { return Math.round(driverController.getRawAxis(3) * 10000.0) / 10000.0; }
    // codriver controller joysticks
    public double codriverX() { return Math.round(codriverController.getRawAxis(0) * 10000.0) / 10000.0; }
    public double codriverY() { return Math.round(codriverController.getRawAxis(1) * 10000.0) / 10000.0; }
    public double codriverZ() { return Math.round(codriverController.getRawAxis(2) * 10000.0) / 10000.0; }
}

