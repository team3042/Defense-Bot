package org.usfirst.frc.team3042.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SendableRegistry;

import org.usfirst.frc.team3042.lib.Log;
import org.usfirst.frc.team3042.robot.Robot;
import org.usfirst.frc.team3042.robot.RobotMap;
import org.usfirst.frc.team3042.robot.subsystems.ExampleSubsystem;

/** ExampleCommand ************************************************************
 * A template for commands.
 */
public class ExampleCommand extends Command {
	/** Configuration Constants ***********************************************/
	private static final Log.Level LOG_LEVEL = RobotMap.LOG_EXAMPLE_SUBSYSTEM;
	
	/** Instance Variables ****************************************************/
	ExampleSubsystem exampleSubsystem = Robot.exampleSubsystem;
	Log log = new Log(LOG_LEVEL, SendableRegistry.getName(exampleSubsystem));
	
	/** ExampleCommand ********************************************************
	 * Required subsystems will cancel commands when this command is run.
	 */
	public ExampleCommand() {
		log.add("Constructor", Log.Level.TRACE);
		
		requires(exampleSubsystem);
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