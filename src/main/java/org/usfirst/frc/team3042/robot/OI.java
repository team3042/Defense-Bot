package org.usfirst.frc.team3042.robot;

import org.usfirst.frc.team3042.lib.Log;

import org.usfirst.frc.team3042.robot.commands.Drivetrain_Scale_Toggle;

/** OI ************************************************************************
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot. */
public class OI {	
	/** Configuration Constants ***********************************************/
	private static final int USB_GAMEPAD = RobotMap.USB_GAMEPAD;
	private static final int USB_JOY_LEFT = RobotMap.USB_JOYSTICK_LEFT;
	private static final int USB_JOY_RIGHT = RobotMap.USB_JOYSTICK_RIGHT;
	private static final boolean USE_JOYSTICKS = RobotMap.USE_JOYSTICKS;
	private static final double JOYSTICK_DRIVE_SCALE = RobotMap.JOYSTICK_DRIVE_SCALE;
	private static final int GAMEPAD_LEFT_Y_AXIS = Gamepad.LEFT_JOY_Y_AXIS;
	private static final int GAMEPAD_RIGHT_Y_AXIS = Gamepad.RIGHT_JOY_Y_AXIS;
	private static final int JOYSTICK_Y_AXIS = Gamepad.JOY_Y_AXIS;
	private static final double JOYSTICK_DRIVE_SCALE_LOW = RobotMap.JOYSTICK_DRIVE_SCALE_LOW;
	
	/** Instance Variables ****************************************************/
	Log log = new Log(RobotMap.LOG_OI, "OI");
	public Gamepad gamepad, joyLeft, joyRight;
	int driveAxisLeft, driveAxisRight;
	public static double CURRENT_DRIVE_SCALE = JOYSTICK_DRIVE_SCALE;
	public static boolean isHighScale = true;

	/** OI ********************************************************************
	 * Assign commands to the buttons and triggers*/
	public OI() {
		log.add("OI Constructor", Log.Level.TRACE);
		
		gamepad = new Gamepad(USB_GAMEPAD);
		
		/** Setup Driving Controls ********************************************/
		if (USE_JOYSTICKS) {
			joyLeft = new Gamepad(USB_JOY_LEFT);
			joyRight = new Gamepad(USB_JOY_RIGHT);
			driveAxisLeft = JOYSTICK_Y_AXIS;
			driveAxisRight = JOYSTICK_Y_AXIS;
		}
		else {
			joyLeft = gamepad;
			joyRight = gamepad;
			driveAxisLeft = GAMEPAD_LEFT_Y_AXIS;
			driveAxisRight = GAMEPAD_RIGHT_Y_AXIS;
		}
		
		/** Scale the drivetrain speed for fine adjustments *******************/
		joyRight.button1.whenPressed(new Drivetrain_Scale_Toggle());
		joyRight.button1.whenReleased(new Drivetrain_Scale_Toggle());
	}
	
	// (ง ͠° ͟ل͜ ͡°)ง

	/** Access to the driving axes values *************************************
	 * A negative has been added to make pushing forward positive. */
	public double getDriveLeft() {
		double joystickValue = joyLeft.getRawAxis(driveAxisLeft);
		joystickValue = scaleJoystick(joystickValue);
		return joystickValue;
	}
	public double getDriveRight() {
		double joystickValue = joyRight.getRawAxis(driveAxisRight);
		joystickValue = scaleJoystick(joystickValue);
		return joystickValue;
	}
	private double scaleJoystick(double joystickValue) {
		joystickValue *= CURRENT_DRIVE_SCALE;
		joystickValue *= -1.0;
		return joystickValue;
	}
	public void setHighScale(){
    	CURRENT_DRIVE_SCALE = JOYSTICK_DRIVE_SCALE;
    	isHighScale = true;
    }
    public void setLowScale(){
    	CURRENT_DRIVE_SCALE = JOYSTICK_DRIVE_SCALE_LOW;
    	isHighScale = false;
    }
    public void toggleScale(){
    	if (isHighScale) {
    		setLowScale();
    	}
    	else {
    		setHighScale();
		}
	}	
}