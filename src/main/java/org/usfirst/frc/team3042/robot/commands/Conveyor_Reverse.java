package org.usfirst.frc.team3042.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SendableRegistry;

import org.usfirst.frc.team3042.lib.Log;
import org.usfirst.frc.team3042.robot.Robot;
import org.usfirst.frc.team3042.robot.RobotMap;
import org.usfirst.frc.team3042.robot.subsystems.LowerConveyor;
import org.usfirst.frc.team3042.robot.subsystems.UltrasonicSensor;
import org.usfirst.frc.team3042.robot.subsystems.UpperConveyor;

/** Conveyor Reverse *******************************************************
 * Brings the power cells down to the bottom of the conveyor after shooting 
 */ 
public class Conveyor_Reverse extends Command {
	/** Configuration Constants ***********************************************/
	private static final Log.Level LOG_LEVEL = RobotMap.LOG_COLOR_SENSOR;
	private static final double lowerSpeed = RobotMap.LOWER_CONVEYOR_POWER;
	private static final double upperSpeed = RobotMap.UPPER_CONVEYOR_POWER;
	private static final double duration = RobotMap.CONVEYOR_REVERSE_DURATION;
	
	/** Instance Variables ****************************************************/
	UltrasonicSensor sensor = Robot.ultrasonicsensor;
	LowerConveyor lowerconveyor = Robot.lowerconveyor;
	UpperConveyor upperconveyor = Robot.upperconveyor;
	Log log = new Log(LOG_LEVEL, SendableRegistry.getName(lowerconveyor));
	Timer timer = new Timer();
	
	/** Advance Lower Conveyor ***************************************************
	 * Required subsystems will cancel commands when this command is run.
	 */
	public Conveyor_Reverse() {
		log.add("Constructor", Log.Level.TRACE);
		
		requires(lowerconveyor);
	}

	/** initialize ************************************************************
	 * Called just before this Command runs the first time
	 */
	protected void initialize() {
		log.add("Initialize", Log.Level.TRACE);
		timer.reset();
		lowerconveyor.setPower(-1 * lowerSpeed);
		upperconveyor.setPower(-1 * upperSpeed);
	}

	/** execute ***************************************************************
	 * Called repeatedly when this Command is scheduled to run
	 */
	protected void execute() {
		if (sensor.isPowerCellIn()) {
			upperconveyor.stop();
			lowerconveyor.setPower(lowerSpeed);
			timer.start();
		}
	}
	
	/** isFinished ************************************************************	
	 * Make this return true when this Command no longer needs to run execute()
	 */
	protected boolean isFinished() {
		return timer.get() >= duration;
	}
	
	/** end *******************************************************************
	 * Called once after isFinished returns true
	 */
	protected void end() {
		log.add("End", Log.Level.TRACE);
		lowerconveyor.stop();
		upperconveyor.stop();
		timer.stop();
		timer.reset();
	}

	/** interrupted ***********************************************************
	 * Called when another command which requires one or more of the same
	 * subsystems is scheduled to run
	 */
	protected void interrupted() {
		log.add("Interrupted", Log.Level.TRACE);
		lowerconveyor.stop();
		upperconveyor.stop();
		timer.stop();
		timer.reset();
	}
}