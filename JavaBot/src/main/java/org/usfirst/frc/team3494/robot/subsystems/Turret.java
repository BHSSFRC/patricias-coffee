package org.usfirst.frc.team3494.robot.subsystems;

import org.usfirst.frc.team3494.robot.DriveDirections;
import org.usfirst.frc.team3494.robot.UnitTypes;
import org.usfirst.frc.team3494.robot.RobotMap;
import org.usfirst.frc.team3494.robot.commands.turret.Shoot;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Turret subsystem. Contains all methods for controlling the robot's turret.
 * 
 * @since 0.0.0
 */
public class Turret extends Subsystem {
	private Victor shooter_top = new Victor(RobotMap.shooterTop);
	private Victor shooter_bot = new Victor(RobotMap.shooterBottom);
	private Victor turret_con = new Victor(RobotMap.turretMotor);
	private Encoder turret_enc;
	// Put methods for controlling this subsystem
	// here. Call these from Commands.

	public Turret() {
		super("Turret");
		turret_enc = new Encoder(RobotMap.turretEncoder_A, RobotMap.turretEncoder_B);
		turret_enc.setDistancePerPulse(1 / 420);
		turret_enc.reset();
	}

	@Override
	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
		setDefaultCommand(new Shoot());
	}

	/**
	 * Run the shooter.
	 * 
	 * @param speed
	 *            The speed to run the shooter at. This can be backwards,
	 *            but...why?
	 */
	public void shoot(double speed) {
		shooter_top.set(speed);
		shooter_bot.set(speed);
	}

	/**
	 * Turn the turret.
	 * 
	 * @param dir
	 *            The direction to turn in. Only use
	 *            {@link DriveDirections#LEFT} or {@link DriveDirections#RIGHT}.
	 *            Any other value will stop the turret.
	 */
	public void turnTurret(DriveDirections dir) {
		if (dir.equals(DriveDirections.LEFT)) {
			turret_con.set(-0.5);
		} else if (dir.equals(DriveDirections.RIGHT)) {
			turret_con.set(0.5);
		} else {
			turret_con.stopMotor();
		}
	}

	/**
	 * Turns the turret at a given speed.
	 * 
	 * @param power
	 *            The power to turn the turret at. This should be
	 *            <em>positive</em>, regardless of the direction to turn in.
	 * @param dir
	 *            The direction to turn in. This should be either
	 *            {@link DriveDirections#LEFT} or {@link DriveDirections#RIGHT}.
	 *            Anything else will stop the turret.
	 */
	public void preciseTurret(double power, DriveDirections dir) {
		if (dir.equals(DriveDirections.LEFT)) {
			turret_con.set(-power);
		} else if (dir.equals(DriveDirections.RIGHT)) {
			turret_con.set(power);
		} else {
			turret_con.stopMotor();
		}
	}

	public Encoder getTurret_enc() {
		return turret_enc;
	}

	public void resetTurret_enc() {
		turret_enc.reset();
	}

	/**
	 * Gets the distance from zero of the tiny little gear on the output shaft.
	 * 
	 * @param get
	 *            The unit the returned data should be in. Doesn't take pixels,
	 *            because...why?
	 * @see org.usfirst.frc.team3494.robot.UnitTypes
	 * @return The distance from the encoder's zero position, as a double.
	 */
	public double getTurretEncDistance(UnitTypes get) {
		if (get.equals(UnitTypes.RAWCOUNT)) {
			return turret_enc.get();
		} else if (get.equals(UnitTypes.MILLIMETERS)) {
			// distance = wheel circumfrence * wheel rotations = (pi * d) * # of
			// counts/N * 360, assuming encoder on output shaft
			// if there is a gearbox in the way, distance =
			double pi = Math.PI;
			int N = 420; // Pulses per revolution :flag_fr:
			double d = 18.34; // diameter of gear on output shaft
			return ((pi * d) * (turret_enc.get() / N) * 360);
		} else {
			// i don't know how you got here but you should probably leave
			return 0;
		}
	}

}