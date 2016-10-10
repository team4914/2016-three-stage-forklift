package org.usfirst.frc4914.threestage.subsystems;

import org.usfirst.frc4914.threestage.RobotMap;
import org.usfirst.frc4914.threestage.commands.*;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Victor;

import edu.wpi.first.wpilibj.command.Subsystem;


public class Drivetrain extends Subsystem {

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    private final SpeedController rightSide = RobotMap.drivetrainRightSide;
    private final SpeedController leftSide = RobotMap.drivetrainLeftSide;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS

    public void setRightSide(double speed) {
    	speed = -speed; // inverts value
    	if (speed <= 1 && speed >= -1) {
    		rightSide.set(speed);
    	} else {
    		rightSide.set(0);
    		System.out.println("Tried to set right side drive out of safe range!");
    		System.out.println("speed: " + speed);
    	}
    }

    public void setLeftSide(double speed) {
    	if (speed <= 1 && speed >= -1) {
    		leftSide.set(speed);
    	} else {
    		leftSide.set(0);
    		System.out.println("Tried to set left side drive out of safe range!");
    		System.out.println("speed: " + speed);
    	}
    }
    
    public void drive(double speed) {
    	setRightSide(speed);
    	setLeftSide(speed);
    }

    public void stop() {
    	drive(0);
    }
    
    public void initDefaultCommand() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND


    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND

        // Set the default command for a subsystem here.
        // setDefaultCommand(new MySpecialCommand());
    }
}

