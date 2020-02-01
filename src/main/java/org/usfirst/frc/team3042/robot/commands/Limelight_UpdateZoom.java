package org.usfirst.frc.team3042.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SendableRegistry;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

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
	private static final double AREA_ZOOM_IN = RobotMap.ZOOM_IN_AREA;
	private static final double AREA_ZOOM_OUT = RobotMap.ZOOM_OUT_AREA;
	private static final double AREA_ZOOM_ZOOM_IN = RobotMap.ZOOM_ZOOM_IN_AREA;
	private static final double AREA_ZOOM_ZOOM_OUT = RobotMap.ZOOM_ZOOM_OUT_AREA;
	
	/** Instance Variables ****************************************************/
	Limelight limelight = Robot.limelight;
	Log log = new Log(LOG_LEVEL, SendableRegistry.getName(limelight));
	double area;
	
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
		SmartDashboard.putNumber("pipeline", limelight.returnCurrentPipeline());
		if (area <= AREA_ZOOM_IN && limelight.returnCurrentPipeline() == 0.0 && limelight.returnValidTarget() == 1.0) {
			limelight.pipeline.setNumber(1);
		}		
		else if (area > AREA_ZOOM_OUT && limelight.returnCurrentPipeline() == 1.0 && limelight.returnValidTarget() == 1.0) {
			limelight.pipeline.setNumber(0);
		}	
		else if (area <= AREA_ZOOM_ZOOM_IN && limelight.returnCurrentPipeline() == 1.0 && limelight.returnValidTarget() == 1.0) {
			limelight.pipeline.setNumber(2);
		}		
		else if (area > AREA_ZOOM_ZOOM_OUT && limelight.returnCurrentPipeline() == 2.0 && limelight.returnValidTarget() == 1.0) {
			limelight.pipeline.setNumber(1);
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