package frc.robot.commands.drive;

import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.subsystems.Drivetrain;

/**
 * Default command for the
 * {@link Drivetrain} subsystem.
 * Drives the Drivetrain by the Xbox controller.
 */
public class Drive extends Command {

    public Drive() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        requires(Robot.driveTrain);
    }

    // Called just before this Command runs the first time
    @Override
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    @Override
    protected void execute() {
        if (Robot.prefs.getBoolean("arcade", false)) {
            Robot.driveTrain.arcadeDrive(-Robot.oi.xbox.getY(Hand.kLeft), Robot.oi.xbox.getX(Hand.kLeft), true);
        } else {
            Robot.driveTrain.TankDrive(-Robot.oi.getFlight_one().getY(), -Robot.oi.getFlight_two().getY());
        }
    }

    // Make this return true when this Command no longer needs to run execute()
    @Override
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    @Override
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    @Override
    protected void interrupted() {
    }
}
