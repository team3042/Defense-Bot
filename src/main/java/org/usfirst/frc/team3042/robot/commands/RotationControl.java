package org.usfirst.frc.team3042.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SendableRegistry;

import org.usfirst.frc.team3042.lib.Log;
import org.usfirst.frc.team3042.robot.Robot;
import org.usfirst.frc.team3042.robot.RobotMap;
import org.usfirst.frc.team3042.robot.subsystems.CPWheelEncoder;
import org.usfirst.frc.team3042.robot.subsystems.ControlPanelWheel;

/** Rotation Control *******************************************************
 * Command for rotating the control panel 3-5 rotations
 */
public class RotationControl extends Command {
	/** Configuration Constants ***********************************************/
	private static final Log.Level LOG_LEVEL = RobotMap.LOG_CONTROL_PANEL_WHEEL;
	private static final int REVOLUTIONS = RobotMap.CPWHEEL_REVOLUTIONS;
	
	/** Instance Variables ****************************************************/
	ControlPanelWheel cpwheel = Robot.cpwheel;
	CPWheelEncoder encoder = cpwheel.getEncoder();  
  	Log log = new Log(LOG_LEVEL, SendableRegistry.getName(cpwheel));
	
	/** Rotation Control ***************************************************
	 * Required subsystems will cancel commands when this command is run.
	 */
	public RotationControl() {
		log.add("Constructor", Log.Level.TRACE);
		
		requires(cpwheel);
	}

	/** initialize ************************************************************
	 * Called just before this Command runs the first time
	 */
	protected void initialize() {
		log.add("Initialize", Log.Level.TRACE);
		encoder.reset();
    	cpwheel.setPower(.4);
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
		return encoder.getPosition() >= REVOLUTIONS;
	}
	
	/** end *******************************************************************
	 * Called once after isFinished returns true
	 */
	protected void end() {
		log.add("End", Log.Level.TRACE);
		cpwheel.stop();
	}

	/** interrupted ***********************************************************
	 * Called when another command which requires one or more of the same
	 * subsystems is scheduled to run
	 */
	protected void interrupted() {
		log.add("Interrupted", Log.Level.TRACE);
		cpwheel.stop();
	}
}