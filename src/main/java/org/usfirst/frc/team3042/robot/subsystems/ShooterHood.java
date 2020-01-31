package org.usfirst.frc.team3042.robot.subsystems;

import org.usfirst.frc.team3042.lib.Log;
import org.usfirst.frc.team3042.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SendableRegistry;

/** Shooter Hood ****************************************************************
 * Subsystem for adjusting the vertical angle of the shooter hood
 */
public class ShooterHood extends Subsystem {
	/** Configuration Constants ***********************************************/
  private static final Log.Level LOG_LEVEL = RobotMap.LOG_SHOOTER_HOOD;

	/** Instance Variables ****************************************************/
  Log log = new Log(LOG_LEVEL, SendableRegistry.getName(this));

	/** Intake ******************************************************/
	public ShooterHood() {
    log.add("Constructor", LOG_LEVEL);
  }
  
	/** initDefaultCommand ****************************************************
	 * Set the default command for the subsystem.
	 */
	public void initDefaultCommand() {
		setDefaultCommand(null);
	}
}