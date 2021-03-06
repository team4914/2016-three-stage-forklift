package org.usfirst.frc4914.threestage.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc4914.threestage.Robot;

/**
 *
 */
public class ToggleHighGoalSpeeds extends Command {

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_DECLARATIONS
 
    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_DECLARATIONS
	
	private double topSpeed = 0;
	private double bottomSpeed = 0;

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTOR
    public ToggleHighGoalSpeeds() {

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTOR
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_SETTING

        // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_SETTING
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	if (Robot.shooter.getTopFlywheelSpeed() == 0 &&
    			Robot.shooter.getBottomFlywheelSpeed() == 0) {
        	topSpeed = 0.7;
        	bottomSpeed = 0.8;
    	}
    	else {
    		topSpeed = 0;
    		bottomSpeed = 0;
    	}
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.shooter.setFlywheels(topSpeed, bottomSpeed);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return true;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
