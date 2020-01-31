package org.usfirst.frc.team3042.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import org.usfirst.frc.team3042.lib.Log;
import org.usfirst.frc.team3042.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SendableRegistry;

/** Climbing Hook ****************************************************************
 * Subsystem for the Climbing Hook
 */
public class ClimbingHook extends Subsystem {
	/** Configuration Constants ***********************************************/
  private static final Log.Level LOG_LEVEL = RobotMap.LOG_CLIMBING_HOOK;
  private static final int CAN_CLIMBING_HOOK = RobotMap.CAN_CLIMBING_HOOK;
  private static final boolean REVERSE_MOTOR = RobotMap.REVERSE_CLIMBING_HOOK;
  private static final NeutralMode BRAKE_MODE = RobotMap.CLIMBING_HOOK_BRAKE_MODE;

	/** Instance Variables ****************************************************/
  Log log = new Log(LOG_LEVEL, SendableRegistry.getName(this));
  TalonSRX motor = new TalonSRX(CAN_CLIMBING_HOOK);

	/** Climbing Hook ******************************************************/
	public ClimbingHook() {
    log.add("Constructor", LOG_LEVEL);
    
    initMotor(motor, REVERSE_MOTOR);
  }

  private void initMotor(TalonSRX motor, boolean reverse) {
		motor.setNeutralMode(BRAKE_MODE);
		motor.setInverted(reverse); 	// affects percent Vbus mode
  }
  
  /** Methods for setting the motor in Percent Vbus mode ********************/
	public void setPower(double Power) {
		Power = safetyCheck(Power);
				
		motor.set(ControlMode.PercentOutput, Power);		
	}
	public void stop() {
		setPower(0.0);
	}
	private double safetyCheck(double power) {
		power = Math.min(1.0, power);
		power = Math.max(-1.0, power);
		return power;
	}
	
	/** initDefaultCommand ****************************************************
	 * Set the default command for the subsystem.
	 */
	public void initDefaultCommand() {
		setDefaultCommand(null);
	}
}