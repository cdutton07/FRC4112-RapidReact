// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class StartShooter extends Command {
  public StartShooter() {
    requires(Robot.shooter);
  }

  @Override
  protected void initialize() {}

  @Override
  protected void execute() {
    //Robot.shooter.startShoot(1);
    Robot.shooter.tShooter(0.8);
  }

  @Override
  protected boolean isFinished() {
    return false;
  }

  @Override
  protected void end() {
    Robot.shooter.tShooter(0);
  }

  @Override
  protected void interrupted() {
    end();
  }
}
