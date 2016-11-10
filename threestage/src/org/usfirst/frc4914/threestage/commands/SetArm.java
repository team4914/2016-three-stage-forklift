// RobotBuilder Version: 2.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.


package org.usfirst.frc4914.threestage.commands;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc4914.threestage.Robot;

/**
 *
 */
public class SetArm extends Command {
	private PIDController pid;
    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_DECLARATIONS
    private double m_setpoint;
 
    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_DECLARATIONS

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTOR
    public SetArm(double setpoint) {

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTOR
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_SETTING
        m_setpoint = setpoint;

        // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_SETTING
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES
        requires(Robot.motorizedArm);

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES
    }

    // Called just before this Command runs the first time
 // Called just before this Command runs the first time
 	protected void initialize() {
 		/*
 		pid = new PIDController(20, 0, 0, new PIDSource() {
 			PIDSourceType m_sourceType = PIDSourceType.kDisplacement;

 			public double pidGet() {
 				return Robot.motorizedArm.getEncoder();
 			}

 			@Override
 			public void setPIDSourceType(PIDSourceType pidSource) {
 				m_sourceType = pidSource;
 			}

 			@Override
 			public PIDSourceType getPIDSourceType() {
 				return m_sourceType;
 			}
 		}, new PIDOutput() {
 			public void pidWrite(double d) {
 				Robot.motorizedArm.setMotors(d);
 			}
 		});
 		pid.setAbsoluteTolerance(0.01);
 		pid.setSetpoint(m_setpoint);
 		pid.setOutputRange(-0.3, 0.3);
 		
 		// Get everything in a safe starting state.
 		Robot.motorizedArm.stop();
 		pid.reset();
 		pid.enable();
 		*/
 		Robot.motorizedArm.setSetpoint(m_setpoint);
 	}

 	// Called repeatedly when this Command is scheduled to run
 	protected void execute() {
 		System.out.println("EncPos: " + Robot.motorizedArm.getEncoder());
 	}

 	// Make this return true when this Command no longer needs to run execute()
 	protected boolean isFinished() {
 		// return pid.onTarget();
 		return true;
 	}

 	// Called once after isFinished returns true
 	protected void end() {
 		/*
 		// Stop PID and the motors
 		pid.free();
 		Robot.motorizedArm.stop();
 		*/
 	}

 	// Called when another command which requires one or more of the same
 	// subsystems is scheduled to run
 	protected void interrupted() {
 		end();
 	}
}
