// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class RHood2 extends Command {
  public RHood2() {
    requires(Robot.hood);
  }

  @Override
  protected void initialize() {
  }

  @Override
  protected void execute() {
    Robot.hood.raiseHood(3.75);;
  }

  @Override
  protected boolean isFinished() {
    return false;
  }

  @Override
  protected void end() {
    Robot.hood.tHood(0);
  }

  @Override
  protected void interrupted() {
    end();
  }
}