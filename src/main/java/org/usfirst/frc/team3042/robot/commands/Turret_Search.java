package org.usfirst.frc.team3042.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SendableRegistry;

import org.usfirst.frc.team3042.lib.Log;
import org.usfirst.frc.team3042.robot.Robot;
import org.usfirst.frc.team3042.robot.RobotMap;
import org.usfirst.frc.team3042.robot.subsystems.Limelight;
import org.usfirst.frc.team3042.robot.subsystems.Turret;
import org.usfirst.frc.team3042.robot.subsystems.TurretEncoder;

/** Turret Slow *******************************************************
 * Command to search for the vision target if we don't see it right away in autonomous mode.
 */

public class Turret_Search extends Command {
	/** Configuration Constants ***********************************************/
	private static final Log.Level LOG_LEVEL = RobotMap.LOG_TURRET;
	private static final double UNWRAP_SPEED = RobotMap.TURRET_UNWRAP_SPEED;

	/** Instance Variables ****************************************************/
	Limelight limelight = Robot.limelight;
	Turret turret = Robot.turret;
	TurretEncoder encoder = turret.getEncoder();  
	Log log = new Log(LOG_LEVEL, SendableRegistry.getName(turret));
	
	/** Turret Slow ***************************************************
	 * Required subsystems will cancel commands when this command is run.
	 */
	public Turret_Search() {
		log.add("Constructor", Log.Level.TRACE);
		requires(turret);
	}

	/** initialize ************************************************************
	 * Called just before this Command runs the first time
	 */
	protected void initialize() {
		log.add("Initialize", Log.Level.TRACE);
		if (limelight.returnValidTarget() == 0) {
			turret.setPower(UNWRAP_SPEED);
		}
	}

	/** execute ***************************************************************
	 * Called repeatedly when this Command is scheduled to run
	 */
	protected void execute() {
		if (encoder.getPosition() >= 180) {
			turret.setPower(-1 * UNWRAP_SPEED);
		}
		if (encoder.getPosition() <= -180) {
			turret.setPower(UNWRAP_SPEED);
		}
	}
	
	/** isFinished ************************************************************	
	 * Make this return true when this Command no longer needs to run execute()
	 */
	protected boolean isFinished() {
		return limelight.returnValidTarget() == 1;
	}
	
	/** end *******************************************************************
	 * Called once after isFinished returns true
	 */
	protected void end() {
		log.add("End", Log.Level.TRACE);
		turret.stop();
	}

	/** interrupted ***********************************************************
	 * Called when another command which requires one or more of the same
	 * subsystems is scheduled to run
	 */
	protected void interrupted() {
		log.add("Interrupted", Log.Level.TRACE);
		turret.stop();
	}
}