package org.usfirst.frc.team3042.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SendableRegistry;

import org.usfirst.frc.team3042.lib.Log;
import org.usfirst.frc.team3042.robot.Robot;
import org.usfirst.frc.team3042.robot.RobotMap;
import org.usfirst.frc.team3042.robot.subsystems.ColorSensor;
import org.usfirst.frc.team3042.robot.subsystems.ControlPanelWheel;

/** Control Panel Wheel Position Control *******************************************************
 * 
 */
public class ControlPanelWheel_PositionControl extends Command {
	/** Configuration Constants ***********************************************/
	private static final Log.Level LOG_LEVEL = RobotMap.LOG_CONTROL_PANEL_WHEEL;
	
	/** Instance Variables ****************************************************/
  ControlPanelWheel cpwheel = Robot.cpwheel;
  ColorSensor colorsensor = Robot.colorsensor;
  Log log = new Log(LOG_LEVEL, SendableRegistry.getName(cpwheel));

  Boolean done = false;

  String color;
  String targetColor;
	
	/** Control Panel Wheel Position Control ***************************************************
	 * Required subsystems will cancel commands when this command is run.
	 */
	public ControlPanelWheel_PositionControl() {
		log.add("Constructor", Log.Level.TRACE);
		
		requires(cpwheel);
	}

	/** initialize ************************************************************
	 * Called just before this Command runs the first time
	 */
	protected void initialize() {
	log.add("Initialize", Log.Level.TRACE);
	done = false;
	targetColor = cpwheel.getTargetColor();
    cpwheel.setPower(.2);
	}

	/** execute ***************************************************************
	 * Called repeatedly when this Command is scheduled to run
	 */
	protected void execute() {
    if(colorsensor.returnColor().equals(targetColor)) {
      cpwheel.stop();
      done = true;
    	}
	}
	
	/** isFinished ************************************************************	
	 * Make this return true when this Command no longer needs to run execute()
	 */
	protected boolean isFinished() {
		return done;
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