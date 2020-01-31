package org.usfirst.frc.team3042.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import org.usfirst.frc.team3042.lib.Log;
import org.usfirst.frc.team3042.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SendableRegistry;

/** Climbing Winch ****************************************************************
 * Subsystem for the winch that is used to pull the robot up while climbing
 */
public class ClimbingWinch extends Subsystem {
	/** Configuration Constants ***********************************************/
  private static final Log.Level LOG_LEVEL = RobotMap.LOG_CLIMBING_WINCH;
  private static final int CAN_CLIMBING_WINCH = RobotMap.CAN_CLIMBING_WINCH;
  private static final boolean REVERSE_MOTOR = RobotMap.REVERSE_CLIMBING_WINCH;
  private static final NeutralMode BRAKE_MODE = RobotMap.CLIMBING_WINCH_BRAKE_MODE;

	/** Instance Variables ****************************************************/
  Log log = new Log(LOG_LEVEL, SendableRegistry.getName(this));
  TalonSRX motor = new TalonSRX(CAN_CLIMBING_WINCH);

	/** Climbing Winch ******************************************************/
	public ClimbingWinch() {
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