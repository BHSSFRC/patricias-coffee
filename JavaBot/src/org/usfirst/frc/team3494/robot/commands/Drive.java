package org.usfirst.frc.team3494.robot.commands;

import org.usfirst.frc.team3494.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class Drive extends Command {

	public Drive() {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		requires(Robot.driveTrain);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		double left = Robot.oi.flight_one.getRawAxis(1) * -1;
		double right = Robot.oi.flight_two.getRawAxis(1);
		Robot.driveTrain.TankDrive(left, right);
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return false;
	}

	// Called once after isFinished returns true
	protected void end() {
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
	}
}
