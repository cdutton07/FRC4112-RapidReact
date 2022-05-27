// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class FeedBall extends Command {
  public FeedBall() {
    requires(Robot.intake);
  }

  @Override
  protected void initialize() {}

  @Override
  protected void execute() {
    Robot.intake.startRollers(0.25, 0.5);
  }

  @Override
  protected boolean isFinished() {
    return false;
  }

  @Override
  protected void end() {
    Robot.intake.startRollers(0, 0);
  }

  @Override
  protected void interrupted() {
    end();
  }
}
