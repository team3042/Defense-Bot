package org.usfirst.frc.team3042.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import org.usfirst.frc.team3042.lib.Log;
import org.usfirst.frc.team3042.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SendableRegistry;

/** Upper Conveyor ****************************************************************
 * Subsystem for the Upper Conveyor
 */
public class UpperConveyor extends Subsystem {
	/** Configuration Constants ***********************************************/
  	private static final Log.Level LOG_LEVEL = RobotMap.LOG_UPPER_CONVEYOR;
	private static final int CAN_UPPERCONVEYOR = RobotMap.CAN_UPPER_CONVEYOR;
	private static final int CAN_UPPERCONVEYOR2 = RobotMap.CAN_UPPER_CONVEYOR2;
	private static final boolean REVERSE_MOTOR = RobotMap.REVERSE_UPPER_CONVEYOR;
  	private static final boolean REVERSE_MOTOR2 = RobotMap.REVERSE_UPPER_CONVEYOR2;
  	private static final NeutralMode BRAKE_MODE = RobotMap.UPPER_CONVEYOR_BRAKE_MODE;

	/** Instance Variables ****************************************************/
  	Log log = new Log(LOG_LEVEL, SendableRegistry.getName(this));
	  TalonSRX motor = new TalonSRX(CAN_UPPERCONVEYOR);
	  TalonSRX motor2 = new TalonSRX(CAN_UPPERCONVEYOR2);

	/** Upper Conveyor ******************************************************/
	public UpperConveyor() {
		log.add("Constructor", LOG_LEVEL);
		
		initMotor(motor, REVERSE_MOTOR);    
		initMotor(motor2, REVERSE_MOTOR2);   
  	}

  	private void initMotor(TalonSRX motor, boolean reverse) {
		motor.setNeutralMode(BRAKE_MODE);
		motor.setInverted(reverse); 	// affects percent Vbus mode
  	}
  
 	/** Methods for setting the motors in Percent Vbus mode ********************/
	public void setPower(double Power) {
		Power = safetyCheck(Power);
				
		motor.set(ControlMode.PercentOutput, Power);	
		motor2.set(ControlMode.PercentOutput, Power);	
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