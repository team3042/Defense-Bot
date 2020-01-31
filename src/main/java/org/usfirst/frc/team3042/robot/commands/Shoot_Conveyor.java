package org.usfirst.frc.team3042.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SendableRegistry;

import org.usfirst.frc.team3042.lib.Log;
import org.usfirst.frc.team3042.robot.Robot;
import org.usfirst.frc.team3042.robot.RobotMap;
import org.usfirst.frc.team3042.robot.subsystems.Limelight;
import org.usfirst.frc.team3042.robot.subsystems.Turret;

/** Shoot_Conveyor *******************************************************
 * Command for correcting the reported angle of error with the turret
 */
public class Shoot_Conveyor extends Command {
    /** Configuration Constants ***********************************************/


    /** Instance Variables ****************************************************/
    UpperConveyor upperconveyor = Robot.uppperconveyor;
    LowerConveyor lowerconveyor = Robot.lowerconveyor;
    Log log = new Log(LOG_LEVEL, SendableRegistry.getName(conveyor));
      
    double error;
    //double derivative; //Derivative is the difference of the current error and the previous error
    double integral = 0; //Integral is the sum of all errors
    double previousError;
    
    /** Turret Continous ***************************************************
     * Required subsystems will cancel commands when this command is run.
     */
    public Shoot_Conveyor() {
      log.add("Constructor", Log.Level.TRACE);
      
      requires(upperconveyor);
      requires(lowerconveyor);
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
      conveyor.stop();
    }

    /** interrupted ***********************************************************
     * Called when another command which requires one or more of the same
     * subsystems is scheduled to run
     */
    protected void interrupted() {
      log.add("Interrupted", Log.Level.TRACE);
      conveyor.stop();
    }
}