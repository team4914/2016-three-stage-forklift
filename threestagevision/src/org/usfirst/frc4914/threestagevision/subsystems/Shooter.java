package org.usfirst.frc4914.threestagevision.subsystems;

import org.usfirst.frc4914.threestagevision.RobotMap;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.command.Subsystem;


public class Shooter extends Subsystem {

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS
	
	public static final boolean speedToggle = false; // true if flywheels running, false otherwise

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    private final SpeedController topFlywheel = RobotMap.shooterTopFlywheel;
    private final SpeedController bottomFlywheel = RobotMap.shooterBottomFlywheel;
    private final SpeedController intakeRoller = RobotMap.shooterIntakeRoller;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS

    public void setTopFlywheel(double speed) {
    	speed = -speed;
    	if (speed <= 1 && speed >= -1) {
    		topFlywheel.set(speed);
    	} else {
    		topFlywheel.set(0);
    		System.out.println("Tried to set top flywheel out of safe range!");
    		System.out.println("speed: " + speed);
    	}
    }
    
    public void setBottomFlywheel(double speed) {
    	if (speed <= 1 && speed >= -1) {
    		bottomFlywheel.set(speed);
    	} else {
    		bottomFlywheel.set(0);
    		System.out.println("Tried to set bottom flywheel out of safe range!");
    		System.out.println("speed: " + speed);
    	}
    }
    
    public void setFlywheels(double topSpeed, double bottomSpeed) {
    	setTopFlywheel(topSpeed);
    	setBottomFlywheel(bottomSpeed);
    }
    
    public void setIntake(double speed) {
    	if (speed <= 1 && speed >= -1) {
    		intakeRoller.set(speed);
    	} else {
    		intakeRoller.set(0);
    		System.out.println("Tried to set intake out of safe range!");
    		System.out.println("speed: " + speed);
    	}
    }

    public double getTopFlywheelSpeed() {
    	return topFlywheel.get();
    }

    public double getBottomFlywheelSpeed() {
    	return bottomFlywheel.get();
    }

    public double getIntakeSpeed() {
    	return intakeRoller.get();
    }

    public void stop() {
    	setTopFlywheel(0);
    	setBottomFlywheel(0);
    	setIntake(0);
    }
    
    public void initDefaultCommand() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND


    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND

        // Set the default command for a subsystem here.
        // setDefaultCommand(new MySpecialCommand());
    }
}

