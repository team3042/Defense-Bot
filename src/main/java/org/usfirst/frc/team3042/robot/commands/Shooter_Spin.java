package org.usfirst.frc.team3042.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SendableRegistry;

import org.usfirst.frc.team3042.lib.Log;
import org.usfirst.frc.team3042.robot.Robot;
import org.usfirst.frc.team3042.robot.RobotMap;
import org.usfirst.frc.team3042.robot.subsystems.Limelight;
import org.usfirst.frc.team3042.robot.subsystems.Shooter;

/** Shooter *******************************************************
 * Sets power to the shooter
 */
public class Shooter_Spin extends Command {
	/** Configuration Constants ***********************************************/
	private static final Log.Level LOG_LEVEL = RobotMap.LOG_SHOOTER;
	private static final double POWER = RobotMap.SHOOTER_POWER;
	
	/** Instance Variables ****************************************************/
	Shooter shooter = Robot.shooter;
	Limelight limelight = Robot.limelight;
	Log log = new Log(LOG_LEVEL, SendableRegistry.getName(shooter));
	
	/** Shooter ***************************************************
	 * Required subsystems will cancel commands when this command is run.
	 */
	public Shooter_Spin() {
		log.add("Constructor", Log.Level.TRACE);
		
		requires(shooter);
	}

	/** initialize ************************************************************
	 * Called just before this Command runs the first time
	 */
	protected void initialize() {
		log.add("Initialize", Log.Level.TRACE);
	}

	/** execute ***************************************************************
	 * Called repeatedly when this Command is scheduled to run
	 */
	protected void execute() {
		if (limelight.returnValidTarget() == 1.0) {
			shooter.setPower(POWER);
		}
		else if (limelight.returnValidTarget() == 0) {
			shooter.stop();
		}
	}

	/** isFinished ************************************************************	
	 * Make this return true when this Command no longer needs to run execute()
	 */
	protected boolean isFinished() {
		return false;
	}
	
	/** end *******************************************************************
	 * Called once after isFinished returns true
	 */
	protected void end() {
		log.add("End", Log.Level.TRACE);
		shooter.stop();
	}

	/** interrupted ***********************************************************
	 * Called when another command which requires one or more of the same
	 * subsystems is scheduled to run
	 */
	protected void interrupted() {
		log.add("Interrupted", Log.Level.TRACE);
		shooter.stop();
	}
}