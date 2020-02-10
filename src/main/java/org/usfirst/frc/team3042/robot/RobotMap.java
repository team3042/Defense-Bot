package org.usfirst.frc.team3042.robot;

import org.usfirst.frc.team3042.lib.Log;

import com.ctre.phoenix.motorcontrol.NeutralMode;

/** RobotMap ******************************************************************
 * The robot configuration file.
 */
public class RobotMap {
	/** Robot Size Parameters *************************************************
	 * The units of the wheel diameter determine the units of the position 
	 * and speed closed-loop commands. For example, if the diameter is given 
	 * in inches, position will be in inches and speed in inches per second.
	 */
	public static final double WHEEL_DIAMETER = 6.0;
	public static final double ROBOT_WIDTH = 27.0;
	
	/** USB ports *************************************************************/					
	public static final int USB_JOYSTICK_LEFT 	= 0;
	public static final int USB_JOYSTICK_RIGHT 	= 1;
	public static final int USB_GAMEPAD 		= 2;

	/** CAN ID numbers ********************************************************/
	public static final int CAN_LEFT_MOTOR 	= 15;
	public static final int CAN_RIGHT_MOTOR = 2;
	public static final int CAN_LEFT_FOLLOWER = 17;
	public static final int CAN_RIGHT_FOLLOWER = 19;
	
	/** OI Settings ***********************************************************/
	public static final boolean USE_JOYSTICKS = true;
	public static final double JOYSTICK_DRIVE_SCALE = 0.8;
	public static final double JOYSTICK_DRIVE_SCALE_LOW = 0.2;
	public static final double TRIGGER_SPINNER_SCALE = 0.1;
	public static final double JOYSTICK_DEAD_ZONE = 0.0;

	/** Drivetrain Settings *****************************************************/
	public static final boolean HAS_DRIVETRAIN = true;
	public static final boolean HAS_FOLLOWERS = true;
	public static final NeutralMode DRIVETRAIN_BRAKE_MODE = NeutralMode.Brake;
	public static final boolean REVERSE_LEFT_MOTOR  = false;
	public static final boolean REVERSE_RIGHT_MOTOR = true;
	// Maximum Acceleration given in power per second
	public static final double ACCELERATION_MAX = 1.5;
	public static final double kF_DRIVE_LEFT = 0.1817180616740088;
	public static final double kF_DRIVE_RIGHT = 0.16686239968682717;
	/** Drivetrain Encoder Settings **************************************************/
	public static final boolean HAS_ENCODERS = true;
	//Encoder counts per revolution
	//In quadrature mode, actual counts will be 4x this; e.g., 360 -> 1440
	public static final int COUNTS_PER_REVOLUTION = 1440;
	//How often the encoders update on the CAN, in milliseconds
	public static final int ENCODER_FRAME_RATE = 10;
	public static final boolean SENSOR_PHASE_LEFT = false;
	public static final boolean SENSOR_PHASE_RIGHT = false;
	
	/** Drivetrain Autonomous Settings ****************************************/
	public static final boolean HAS_AUTON = HAS_ENCODERS;
	public static final int AUTON_PROFILE = 0;
	public static final double kP_AUTON = 0.0;
	public static final double kI_AUTON = 0.0;
	public static final double kD_AUTON = 0.0;
	public static final int I_ZONE_AUTON = 0;
	//The rate of pushing motion profile points to the talon, in ms
	public static final int AUTON_FRAME_RATE = 10;
	//Parameters for calibrating the F-gain
	public static final double AUTON_CALIBRATE_POWER = 0.5;
	public static final double AUTON_CALIBRATE_TIME = 5.0; //seconds
	public static final int AUTON_COUNT_AVERAGE = 20;
	//Parameters for motion profile driving
	public static final int AUTON_DT_MS = 30;
	public static final double AUTON_DT_SEC = (double)AUTON_DT_MS / 1000.0;
	public static final double AUTON_ACCEL_TIME = 1.0; //time in sec
	public static final double AUTON_SMOOTH_TIME = 0.1; //time in sec
	public static final double AUTON_MAX_ACCEL = 3.0; //rev per sec per sec
	public static final int AUTON_BUFFER_TRIGGER = 10;
	public static final int AUTON_TIMEOUT = 0; // timeout in ms; set to zero
	public static final int AUTON_PIDIDX = 0; // used for cascading PID; set to zero
	public static final int AUTON_HEADING = 0; //unimplemented feature; set to zero
	
	/** Drivetrain Gyro Drive Settings ****************************************/
	public static final double kP_GYRO = 0.0175;
	public static final double kI_GYRO = 0.0;
	public static final double kD_GYRO = 0.0170;
	public static final double ANGLE_TOLERANCE = 2.0;
	public static final double MAX_POWER_GYRO = 0.4;
	public static final double kI_GYRO_INTERVAL = 0.0;

	/** Gyroscope Settings ****************************************************/
	public static final boolean HAS_GYROSCOPE = true;
	public static final double GYROSCOPE_SCALE = 1.0; //Constant for scaling values returned by the gyroscope, leave at 1.0 by default

	/** Logger Settings *******************************************************/
	public static final String 		LOG_FILE_FORMAT = "yyyy-MM-dd-hhmmss";
	public static final String 		LOG_TIME_FORMAT = "hh:mm:ss:SSS";
	public static final String 		LOG_DIRECTORY_PATH = "/home/lvuser/logs/";
	public static final String 		LOG_TIME_ZONE = "America/Chicago";
	public static final boolean 	LOG_TO_CONSOLE 				= true;
	public static final boolean 	LOG_TO_FILE 				= false;
	public static final Log.Level 	LOG_GLOBAL 					= Log.Level.DEBUG;
	public static final Log.Level 	LOG_ROBOT 					= Log.Level.TRACE;
	public static final Log.Level	LOG_OI 						= Log.Level.TRACE;
	public static final Log.Level	LOG_AXIS_TRIGGER 			= Log.Level.ERROR;
	public static final Log.Level	LOG_POV_BUTTON				= Log.Level.ERROR;
	/** Subsystems **/
	public static final Log.Level	LOG_DRIVETRAIN						= Log.Level.TRACE;
	public static final Log.Level	LOG_DRIVETRAIN_FOLLOWERS			= Log.Level.TRACE;
	public static final Log.Level	LOG_DRIVETRAIN_ENCODERS 			= Log.Level.DEBUG;
	public static final Log.Level	LOG_DRIVETRAIN_AUTON				= Log.Level.DEBUG;
	public static final Log.Level	LOG_GYROSCOPE						= Log.Level.DEBUG;
}