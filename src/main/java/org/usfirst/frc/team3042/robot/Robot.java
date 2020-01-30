package org.usfirst.frc.team3042.robot;

import org.usfirst.frc.team3042.lib.Log;
import org.usfirst.frc.team3042.robot.commands.AutonomousMode;
import org.usfirst.frc.team3042.robot.subsystems.ColorSensor;
import org.usfirst.frc.team3042.robot.subsystems.ControlPanelWheel;
import org.usfirst.frc.team3042.robot.subsystems.Drivetrain;
import org.usfirst.frc.team3042.robot.subsystems.Gyroscope;
import org.usfirst.frc.team3042.robot.subsystems.Intake;
import org.usfirst.frc.team3042.robot.subsystems.Limelight;
import org.usfirst.frc.team3042.robot.subsystems.Shooter;
import org.usfirst.frc.team3042.robot.subsystems.Turret;
import org.usfirst.frc.team3042.robot.subsystems.UpperConveyor;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.PowerDistributionPanel;

/** Robot *********************************************************************
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends TimedRobot { 
	/** Configuration Constants ***********************************************/
	private static final Log.Level LOG_LEVEL = RobotMap.LOG_ROBOT;
	private static final boolean HAS_DRIVETRAIN = RobotMap.HAS_DRIVETRAIN;
	private static final boolean HAS_GYROSCOPE = RobotMap.HAS_GYROSCOPE;
	private static final boolean HAS_COLOR_SENSOR = RobotMap.HAS_COLOR_SENSOR;
	private static final boolean HAS_LIMELIGHT = RobotMap.HAS_LIMELIGHT;
	private static final boolean HAS_CONTROL_PANEL_WHEEL = RobotMap.HAS_CONTROL_PANEL_WHEEL;
	private static final boolean HAS_TURRET = RobotMap.HAS_TURRET;
	private static final boolean HAS_INTAKE = RobotMap.HAS_INTAKE;
	private static final boolean HAS_SHOOTER = RobotMap.HAS_SHOOTER;
	private static final boolean HAS_UPPER_CONVEYOR = RobotMap.HAS_UPPER_CONVEYOR;

	/** Create Subsystems *****************************************************/
	private Log log = new Log(LOG_LEVEL, "Robot");
	public static final Drivetrain 	drivetrain 				   = (HAS_DRIVETRAIN) 			 ? new Drivetrain() 	: null;
	public static final Gyroscope 	gyroscope 				   = (HAS_GYROSCOPE) 			 ? new Gyroscope() 	: null;
	public static final ColorSensor colorsensor   		  	   = (HAS_COLOR_SENSOR)          ? new ColorSensor() : null;
	public static final ControlPanelWheel cpwheel 			   = (HAS_CONTROL_PANEL_WHEEL)   ? new ControlPanelWheel() : null;
	public static final Limelight limelight      			   = (HAS_LIMELIGHT)             ? new Limelight() : null;
	public static final Turret turret 						   = (HAS_TURRET)				 ? new Turret()	: null;
	public static final Intake intake 						   = (HAS_INTAKE)				 ? new Intake()	: null;
	public static final Shooter shooter						   = (HAS_SHOOTER)				 ? new Shooter()	: null;
	public static final UpperConveyor upperconveyor			   = (HAS_UPPER_CONVEYOR)				 ? new UpperConveyor()	: null;
	public static final PowerDistributionPanel pdp = new PowerDistributionPanel();
	public static OI oi;

	Command autonomousCommand;
	SendableChooser<Command> chooser = new SendableChooser<Command>();

	public String color;

	/** robotInit *************************************************************
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	public void robotInit() {
		log.add("Robot Init", Log.Level.TRACE);

		oi = new OI();
		chooser.setDefaultOption("Default Auto", new AutonomousMode());
		//chooser.addOption("My Auto", new ExampleCommand());
		SmartDashboard.putData("Auto Mode", chooser);

		limelight.pipeline.setNumber(0);
	}

	/** disabledInit **********************************************************
	 * This function is called once each time the robot enters Disabled mode.
	 * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
	 */
	public void disabledInit() {
		log.add("Disabled Init", Log.Level.TRACE);
		limelight.led.setNumber(1);
	}

	/** disabledPeriodic ******************************************************
	 * Called repeatedly while the robot is is disabled mode.
	 */
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	/** autonomousInit ********************************************************
	 * Run once at the start of autonomous mode.
	 */
	public void autonomousInit() {
		limelight.led.setNumber(3);

		log.add("Autonomous Init", Log.Level.TRACE);
		
		autonomousCommand = chooser.getSelected();

		// schedule the autonomous command (example)
		if (autonomousCommand != null)
			autonomousCommand.start();
	}

	/** autonomousPeriodic ****************************************************
	 * This function is called periodically during autonomous
	 */
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
	}
	
	/** teleopInit ************************************************************
	 * This function is called when first entering teleop mode.
	 */
	public void teleopInit() {
		limelight.led.setNumber(3);
		log.add("Teleop Init", Log.Level.TRACE);
		
		// This makes sure that the autonomous stops running when
		// teleop starts running. If you want the autonomous to
		// continue until interrupted by another command, remove
		// this line or comment it out.
		if (autonomousCommand != null)
			autonomousCommand.cancel();
	}

	/** teleopPeriodic ********************************************************
	 * This function is called periodically during operator control
	 */
	public void teleopPeriodic() {
		Scheduler.getInstance().run();

		color = DriverStation.getInstance().getGameSpecificMessage();
		if(color.length() > 0) {
			switch (color.charAt(0)) {
				case 'B' :
				SmartDashboard.putString("Color:", "Blue");
				break;
				case 'G' :
				SmartDashboard.putString("Color:", "Green");
				break;
				case 'R' :
				SmartDashboard.putString("Color:", "Red");
				break;
				case 'Y' :
				SmartDashboard.putString("Color:", "Yellow");
				break;
				default :
				SmartDashboard.putString("Color:", "ERROR");
				break;
			}
		}
	} 

	/** testPeriodic **********************************************************
	 * This function is called periodically during test mode
	 */
	public void testPeriodic() {
	}
}