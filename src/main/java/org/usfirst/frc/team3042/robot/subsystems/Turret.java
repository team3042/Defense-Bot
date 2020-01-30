package org.usfirst.frc.team3042.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import org.usfirst.frc.team3042.lib.Log;
import org.usfirst.frc.team3042.robot.RobotMap;
import org.usfirst.frc.team3042.robot.commands.Turret_Continous;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SendableRegistry;

/** Turret ****************************************************************
 * Subsystem for the rotation of the turret
 */
public class Turret extends Subsystem {
  	/** Configuration Constants ***********************************************/
  	private static final Log.Level LOG_LEVEL = RobotMap.LOG_TURRET;
  	private static final int CAN_TURRET = RobotMap.CAN_TURRET;
  	private static final boolean REVERSE_MOTOR = RobotMap.REVERSE_TURRET;
  	private static final NeutralMode BRAKE_MODE = RobotMap.TURRET_BRAKE_MODE;
  	private static final boolean HAS_ENCODER = RobotMap.HAS_TURRET_ENCODER;

	/** Instance Variables ****************************************************/
  	Log log = new Log(LOG_LEVEL, SendableRegistry.getName(this));
  	TalonSRX motor = new TalonSRX(CAN_TURRET);
  	TurretEncoder encoder;

	/** Turret ******************************************************/
	public Turret() {
    	log.add("Constructor", LOG_LEVEL);

    	if (HAS_ENCODER) {
				encoder = new TurretEncoder(motor);
			}
    
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
	
  	/** Command Methods *******************************************************/
  	/** Provide commands access to the encoder ********************************/
	public TurretEncoder getEncoder() {
		return encoder;
  }
}