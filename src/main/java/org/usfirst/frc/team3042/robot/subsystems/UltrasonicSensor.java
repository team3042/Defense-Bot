package org.usfirst.frc.team3042.robot.subsystems;

import org.usfirst.frc.team3042.lib.Log;
import org.usfirst.frc.team3042.robot.RobotMap;

import edu.wpi.first.wpilibj.Ultrasonic;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SendableRegistry;

/** Ultrasonic Sensor ****************************************************************
 * Subsystem for the ultrasonic sensor used to determine the location of power cells within the conveyor
 */
public class UltrasonicSensor extends Subsystem {
	/** Configuration Constants ***********************************************/
	private static final Log.Level LOG_LEVEL = RobotMap.LOG_ULTRASONIC_SENSOR;
	private static final double distance = RobotMap.POWER_CELL_DISTANCE;
	private static final int ping = RobotMap.DIO_ULTRASONIC_PING;
	private static final int echo = RobotMap.DIO_ULTRASONIC_ECHO;

	/** Instance Variables ****************************************************/
	Log log = new Log(LOG_LEVEL, SendableRegistry.getName(this));
	Ultrasonic ultra = new Ultrasonic(ping, echo);

	/** Ultrasonic Sensor ******************************************************/
	public UltrasonicSensor() {
		log.add("Constructor", LOG_LEVEL);
		ultra.setAutomaticMode(true);
	}
	
	/** initDefaultCommand ****************************************************
	 * Set the default command for the subsystem.
	 */
	public void initDefaultCommand() {
		setDefaultCommand(null);
	}

	public double getDistance() {
		return ultra.getRangeInches();
	}

	public boolean isPowerCellIn() {
		return ultra.getRangeInches() < distance;
	}
}