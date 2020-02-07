package org.usfirst.frc.team3042.robot.subsystems;

import org.usfirst.frc.team3042.lib.Log;
import org.usfirst.frc.team3042.robot.RobotMap;

//import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SendableRegistry;

/** Intake Deploy ****************************************************************
 * Subsystem for deploying the intake
 */
public class IntakeDeploy extends Subsystem {
	/** Configuration Constants ***********************************************/
	private static final Log.Level LOG_LEVEL = RobotMap.LOG_INTAKE_DEPLOY;
	//private static final int ID = RobotMap.INTAKE_DEPLOY_SOLENOID;
	//private static final int ID2 = RobotMap.INTAKE_DEPLOY_SOLENOID2;

	/** Instance Variables ****************************************************/
	Log log = new Log(LOG_LEVEL, SendableRegistry.getName(this));
	//Solenoid intakeDeploySolenoid = new Solenoid(ID);
	//Solenoid intakeDeploySolenoid2 = new Solenoid(ID2);

	/** Intake Deploy ******************************************************/
	public IntakeDeploy() {
    	log.add("Constructor", LOG_LEVEL);
	}

	public void activate(){
		//intakeDeploySolenoid.set(true);
		//intakeDeploySolenoid2.set(true);
	}
	
	/** initDefaultCommand ****************************************************
	 * Set the default command for the subsystem.
	 */
	public void initDefaultCommand() {
		setDefaultCommand(null);
	}
}