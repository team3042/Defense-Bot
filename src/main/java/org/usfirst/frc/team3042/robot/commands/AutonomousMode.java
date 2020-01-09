package org.usfirst.frc.team3042.robot.commands;

import org.usfirst.frc.team3042.lib.Path;

import edu.wpi.first.wpilibj.command.CommandGroup;

/** AutonomousMode ******************************************************
 * Command Group for the 15 second autonomous period
 */
public class AutonomousMode extends CommandGroup {
  public AutonomousMode() {

    Path driveStraight = new Path();
    driveStraight.addStraight(24, 24);

    //addSequential(new Shoot_PowerCell());
    //addSequential(new Shoot_PowerCell());
    //addSequential(new Shoot_PowerCell());
    addSequential(new DrivetrainAuton_Drive(driveStraight));
  }
}