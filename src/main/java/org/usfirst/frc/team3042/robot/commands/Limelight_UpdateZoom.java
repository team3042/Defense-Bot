package org.usfirst.frc.team3042.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SendableRegistry;

import org.usfirst.frc.team3042.lib.Log;
import org.usfirst.frc.team3042.robot.Robot;
import org.usfirst.frc.team3042.robot.RobotMap;
import org.usfirst.frc.team3042.robot.subsystems.Limelight;

/** Limelight Update Zoom *******************************************************
 * Zooms in or out based on how far we are from the vision target
 */
public class Limelight_UpdateZoom extends Command {
	/** Configuration Constants ***********************************************/
	private static final Log.Level LOG_LEVEL = RobotMap.LOG_LIMELIGHT;
	
	/** Instance Variables ****************************************************/
	Limelight limelight = Robot.limelight;
	Log log = new Log(LOG_LEVEL, SendableRegistry.getName(limelight));
	double area;
	Boolean zoom = false;
	
	/** Limelight Update Zoom ***************************************************
	 * Required subsystems will cancel commands when this command is run.
	 */
	public Limelight_UpdateZoom() {
		log.add("Constructor", Log.Level.TRACE);
		
		requires(limelight);
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
		area = limelight.returnTargetArea();

		if (area <= 0.723 && !zoom && limelight.tv.getDouble(0) == 1.0) {
			limelight.pipeline.setNumber(1);
			zoom = true;
		}		
		else if (area > 4.43 && zoom && limelight.tv.getDouble(0) == 1.0) {
			limelight.pipeline.setNumber(0);
			zoom = false;
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
	}

	/** interrupted ***********************************************************
	 * Called when another command which requires one or more of the same
	 * subsystems is scheduled to run
	 */
	protected void interrupted() {
		log.add("Interrupted", Log.Level.TRACE);
	}
}