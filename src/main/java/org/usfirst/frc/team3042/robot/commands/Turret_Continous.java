package org.usfirst.frc.team3042.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SendableRegistry;

import org.usfirst.frc.team3042.lib.Log;
import org.usfirst.frc.team3042.robot.Robot;
import org.usfirst.frc.team3042.robot.RobotMap;
import org.usfirst.frc.team3042.robot.subsystems.Limelight;
import org.usfirst.frc.team3042.robot.subsystems.Turret;

/** Turret Continous *******************************************************
 * Command for correcting the reported angle of error with the turret
 */

public class Turret_Continous extends Command {
	/** Configuration Constants ***********************************************/
	private static final Log.Level LOG_LEVEL = RobotMap.LOG_TURRET;
	private static final double SPEED = RobotMap.TURRET_SPEED;

	/** Instance Variables ****************************************************/
	Turret turret = Robot.turret;
	Limelight limelight = Robot.limelight; 
	Log log = new Log(LOG_LEVEL, SendableRegistry.getName(turret));
	  
	double error;
	double derivative;
	double integral = 0;
	double previousError;
	
	/** Turret Continous ***************************************************
	 * Required subsystems will cancel commands when this command is run.
	 */
	public Turret_Continous() {
		log.add("Constructor", Log.Level.TRACE);
		
		requires(turret);
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
		error = limelight.returnHorizontalError() / 25;
		integral += error * 0.2;
		derivative = (error - previousError) / .02;
		turret.setPower((SPEED * error) + (.1 * integral) + (.1 *derivative));
		previousError = error;
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