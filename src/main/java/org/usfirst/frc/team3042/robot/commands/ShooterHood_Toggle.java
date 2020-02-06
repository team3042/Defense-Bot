package org.usfirst.frc.team3042.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SendableRegistry;

import org.usfirst.frc.team3042.lib.Log;
import org.usfirst.frc.team3042.robot.Robot;
import org.usfirst.frc.team3042.robot.RobotMap;
import org.usfirst.frc.team3042.robot.subsystems.Limelight;
import org.usfirst.frc.team3042.robot.subsystems.ShooterHood;

/** Shooter Hood Toggle *******************************************************
 * Command for toggling the vertical angle of the shooter hood and the Limelight zoom mode
 */
public class ShooterHood_Toggle extends Command {
	/** Configuration Constants ***********************************************/
	private static final Log.Level LOG_LEVEL = RobotMap.LOG_SHOOTER;
	
	/** Instance Variables ****************************************************/
	ShooterHood shooterHood = Robot.shooterhood;
	Limelight limelight = Robot.limelight;
	Log log = new Log(LOG_LEVEL, SendableRegistry.getName(shooterHood));
	
	/** Shooter ***************************************************
	 * Required subsystems will cancel commands when this command is run.
	 */
	public ShooterHood_Toggle() {
		log.add("Constructor", Log.Level.TRACE);
		
		requires(shooterHood);
	}

	/** initialize ************************************************************
	 * Called just before this Command runs the first time
	 */
	protected void initialize() {
		log.add("Initialize", Log.Level.TRACE);
		limelight.pipeline.setNumber(1);
	}

	/** execute ***************************************************************
	 * Called repeatedly when this Command is scheduled to run
	 */
	protected void execute() {

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
		limelight.pipeline.setNumber(0);
	}

	/** interrupted ***********************************************************
	 * Called when another command which requires one or more of the same
	 * subsystems is scheduled to run
	 */
	protected void interrupted() {
		log.add("Interrupted", Log.Level.TRACE);
	}
}