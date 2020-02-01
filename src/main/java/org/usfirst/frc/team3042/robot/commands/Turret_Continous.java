package org.usfirst.frc.team3042.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SendableRegistry;

import org.usfirst.frc.team3042.lib.Log;
import org.usfirst.frc.team3042.robot.Robot;
import org.usfirst.frc.team3042.robot.RobotMap;
import org.usfirst.frc.team3042.robot.subsystems.Limelight;
import org.usfirst.frc.team3042.robot.subsystems.Turret;
import org.usfirst.frc.team3042.robot.subsystems.TurretEncoder;

/** Turret Continous *******************************************************
 * Command for correcting the reported angle of error with the turret
 */

public class Turret_Continous extends Command {
	/** Configuration Constants ***********************************************/
	private static final Log.Level LOG_LEVEL = RobotMap.LOG_TURRET;
	private static final double kP = RobotMap.kP_TURRET;
	private static final double kI = RobotMap.kI_TURRET;
	private static final double kD = RobotMap.kD_TURRET;

	/** Instance Variables ****************************************************/
	Turret turret = Robot.turret;
	Limelight limelight = Robot.limelight; 
	Log log = new Log(LOG_LEVEL, SendableRegistry.getName(turret));
	TurretEncoder encoder = turret.getEncoder();
	  
	double error;
	double correction;
	double derivative; //Derivative is the difference of the current error and the previous error
	double integral = 0; //Integral is the sum of all errors
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
		encoder.reset();
	}

	/** execute ***************************************************************
	 * Called repeatedly when this Command is scheduled to run
	 */
	protected void execute() {
		error = limelight.returnHorizontalError();
		if(encoder.countsToDegrees(encoder.getPosition()) + error > 180) {
			turret.setPower(-.6);
		}
		else if(encoder.countsToDegrees(encoder.getPosition()) + error < -180) {
			turret.setPower(.6);
		}
		else if(Math.abs(error) >= 0.25) {
			integral += error * 0.2; //Add the current error to the integral
			derivative = (error - previousError) / .02;

			correction = (kP * error) + (kI * integral) + (kD * derivative);
			correction = Math.min(0.4, correction);
			correction = Math.max(-0.4, correction);

			turret.setPower(correction); 
			previousError = error; //set the previous error equal to the current error before starting the loop over and getting a new current error
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