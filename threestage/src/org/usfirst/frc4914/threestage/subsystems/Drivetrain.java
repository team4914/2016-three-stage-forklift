package org.usfirst.frc4914.threestage.subsystems;

import org.usfirst.frc4914.threestage.RobotMap;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.command.Subsystem;


public class Drivetrain extends Subsystem {

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS
	
	public final double speedMultiplier = 1;
	public boolean inverted = false;

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    private final SpeedController rightSide = RobotMap.drivetrainRightSide;
    private final SpeedController leftSide = RobotMap.drivetrainLeftSide;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS

    public void setLeftSide(double speed) {
    	if (speed <= 1 && speed >= -1) {
    		rightSide.set(speed);
    	} else {
    		// warn user about unsafe speed
    		System.out.println("Tried to set left side drive out of safe range!");
    		System.out.println("speed: " + speed);
    		// set controller to max speed
    		if (speed > 0) {
    			rightSide.set(1);
    		} else if (speed < 0) {
    			rightSide.set(-1);
    		}
    	}
    }

    public void setRightSide(double speed) {
    	speed = -speed; // inverts value
    	if (speed <= 1 && speed >= -1) {
    		leftSide.set(speed);
    	} else {
    		// warn user about unsafe speed
    		System.out.println("Tried to set right side drive out of safe range!");
    		System.out.println("speed: " + speed);
    		// set controller to max speed
    		if (speed > 0) {
    			leftSide.set(1);
    		} else if (speed < 0) {
    			leftSide.set(-1);
    		}
    	}
    }
    
    public void drive(double speed) {
    	speed = -speed;
    	setRightSide(speed);
    	setLeftSide(speed);
    }
    
    public double getLeftSpeed() {
    	return leftSide.get();
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

