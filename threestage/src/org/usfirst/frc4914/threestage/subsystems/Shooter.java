package org.usfirst.frc4914.threestage.subsystems;

import org.usfirst.frc4914.threestage.RobotMap;
import org.usfirst.frc4914.threestage.commands.*;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.TalonSRX;
import edu.wpi.first.wpilibj.VictorSP;

import edu.wpi.first.wpilibj.command.Subsystem;


public class Shooter extends Subsystem {

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    private final SpeedController topFlywheel = RobotMap.shooterTopFlywheel;
    private final SpeedController bottomFlywheel = RobotMap.shooterBottomFlywheel;
    private final SpeedController intakeRoller = RobotMap.shooterIntakeRoller;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS

    public void setTopFlywheel(double speed) {
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

