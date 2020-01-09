package org.usfirst.frc.team3042.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutonomousMode extends CommandGroup {
  /**
   * Autonomous Mode
   */
  public AutonomousMode() {

    addSequential(new Drivetrain_GyroStraight(12.0, 24.0));
    
  }
}