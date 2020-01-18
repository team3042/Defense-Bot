package org.usfirst.frc.team3042.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.smartdashboard.SendableRegistry;

import org.usfirst.frc.team3042.lib.Log;
import org.usfirst.frc.team3042.robot.Robot;
import org.usfirst.frc.team3042.robot.RobotMap;
import org.usfirst.frc.team3042.robot.subsystems.Limelight;

/** Limelight Dashboard *******************************************************
 * Displays the horizontal and vertical angles of error and the area of the vision target reported by the Limelight on the dashboard
 */
public class Limelight_Dashboard extends Command {
	/** Configuration Constants ***********************************************/
	private static final Log.Level LOG_LEVEL = RobotMap.LOG_LIMELIGHT;
	
	/** Instance Variables ****************************************************/
	Limelight limelight = Robot.limelight;
	Log log = new Log(LOG_LEVEL, SendableRegistry.getName(limelight));
	
	/** Limelight Dashboard ***************************************************
	 * Required subsystems will cancel commands when this command is run.
	 */
	public Limelight_Dashboard() {
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
		SmartDashboard.putNumber("Horizontal Error:", limelight.returnHorizontalError());
		SmartDashboard.putNumber("Vertical Error:", limelight.returnVerticalError());
		SmartDashboard.putNumber("Target Area:", limelight.returnTargetArea());
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