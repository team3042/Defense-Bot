package org.usfirst.frc.team3042.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import org.usfirst.frc.team3042.lib.Log;
import org.usfirst.frc.team3042.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SendableRegistry;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/** Control Panel Wheel ****************************************************************
 * Subsystem for the wheel that spins control panel
 */
public class ControlPanelWheel extends Subsystem {
	/** Configuration Constants ***********************************************/
  private static final Log.Level LOG_LEVEL = RobotMap.LOG_CONTROL_PANEL_WHEEL;
  private static final int CAN_CPWHEEL = RobotMap.CAN_CONTROL_PANEL_WHEEL;
  private static final boolean REVERSE_MOTOR = RobotMap.REVERSE_CONTROL_PANEL_WHEEL;
  private static final NeutralMode BRAKE_MODE = RobotMap.CPWHEEL_BRAKE_MODE;
  private static final boolean HAS_ENCODER = RobotMap.HAS_CONTROL_PANEL_WHEEL_ENCODER;

	/** Instance Variables ****************************************************/
  Log log = new Log(LOG_LEVEL, SendableRegistry.getName(this));
  TalonSRX wheelTalon = new TalonSRX(CAN_CPWHEEL);
  private String assignedColor;
  CPWheelEncoder encoder;

	/** Contrtol Panel Wheel ******************************************************/
	public ControlPanelWheel() {
    log.add("Constructor", LOG_LEVEL);
    
    initMotor(wheelTalon, REVERSE_MOTOR);
  }

  private void initMotor(TalonSRX motor, boolean reverse) {
		motor.setNeutralMode(BRAKE_MODE);
		motor.setInverted(reverse); 	// affects percent Vbus mode
  }
  
  /** Methods for setting the motors in Percent Vbus mode ********************/
	public void setPower(double Power) {
		Power = safetyCheck(Power);
				
		wheelTalon.set(ControlMode.PercentOutput, Power);		
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
  public String getTargetColor() {
    assignedColor = SmartDashboard.getString("Color:", "NO ASSIGNED COLOR");
    if(assignedColor.equals("Blue")) {
      return "Red";
    }
    else if(assignedColor.equals("Red")) {
      return "Blue";
    }
    else if(assignedColor.equals("Yellow")) {
      return "Green";
    }
    else if(assignedColor.equals("Green")) {
      return "Yellow";
    }
    else {
      return "UNKNOWN COLOR";
    }
  }
}