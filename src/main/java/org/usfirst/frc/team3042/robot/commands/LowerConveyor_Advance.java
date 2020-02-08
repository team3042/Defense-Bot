package org.usfirst.frc.team3042.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SendableRegistry;

import org.usfirst.frc.team3042.lib.Log;
import org.usfirst.frc.team3042.robot.Robot;
import org.usfirst.frc.team3042.robot.RobotMap;
import org.usfirst.frc.team3042.robot.subsystems.LowerConveyor;
import org.usfirst.frc.team3042.robot.subsystems.UltrasonicSensor;

/** Advance Lower Conveyor *******************************************************
 * Advances the Lower Conveyor once it recieves a power cell
 */
public class LowerConveyor_Advance extends Command {
	/** Configuration Constants ***********************************************/
	private static final Log.Level LOG_LEVEL = RobotMap.LOG_COLOR_SENSOR;
	private static final double speed = RobotMap.LOWER_CONVEYOR_POWER;
	
	/** Instance Variables ****************************************************/
	UltrasonicSensor sensor = Robot.ultrasonicsensor;
	LowerConveyor conveyor = Robot.lowerconveyor;
	Log log = new Log(LOG_LEVEL, SendableRegistry.getName(conveyor));
	Timer timer = new Timer();
	
	/** Advance Lower Conveyor ***************************************************
	 * Required subsystems will cancel commands when this command is run.
	 */
	public LowerConveyor_Advance() {
		log.add("Constructor", Log.Level.TRACE);
		
		requires(conveyor);
	}

	/** initialize ************************************************************
	 * Called just before this Command runs the first time
	 */
	protected void initialize() {
		log.add("Initialize", Log.Level.TRACE);
		timer.reset();
	}

	/** execute ***************************************************************
	 * Called repeatedly when this Command is scheduled to run
	 */
	protected void execute() {
		if(sensor.isPowerCellIn()){
			conveyor.setPower(speed);
			timer.start();
		}
		if(timer.get() >= 0.5){
			conveyor.stop();
			timer.stop();
			timer.reset();
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