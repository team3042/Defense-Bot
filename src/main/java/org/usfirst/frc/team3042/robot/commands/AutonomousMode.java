package org.usfirst.frc.team3042.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/** AutonomousMode ******************************************************
 * Command Group for the 15 second autonomous period
 */
public class AutonomousMode extends CommandGroup {

  public AutonomousMode() {
    addParallel(new Turret_Continous()); //Search for the target and start tracking it
    //addSequential(new Shoot()); //Shoot the three pre-loaded power cells
    addSequential(new Drivetrain_GyroStraight(60.0, -40.0)); //Drive backwards off the initiation line
    addSequential(new Turret_Stop()); //Stop tracking the target and turn off the Limelight's LEDs
  }
}