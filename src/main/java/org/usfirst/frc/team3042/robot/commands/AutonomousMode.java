package org.usfirst.frc.team3042.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/** AutonomousMode ******************************************************
 * Command Group for the 15 second autonomous period
 */
public class AutonomousMode extends CommandGroup {

  public AutonomousMode() {
    addSequential(new Turret_Search());
    addParallel(new Turret_Continous());
    addSequential(new Drivetrain_GyroStraight(60.0, -40.0));
    addSequential(new Turret_Stop());
  }
}