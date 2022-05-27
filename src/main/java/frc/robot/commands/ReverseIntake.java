// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class ReverseIntake extends Command {
  public ReverseIntake() {
    requires(Robot.intake);
  }

  @Override
  protected void initialize() {}

  @Override
  protected void execute() {
    Robot.intake.startIntake(-0.35, -0.45);
    Robot.intake.sRollers.set(-0.25);
  }

  @Override
  protected boolean isFinished() {
    return false;
  }

  @Override
  protected void end() {
    Robot.intake.startIntake(0, 0);
    Robot.intake.sRollers.set(0);
  }

  @Override
  protected void interrupted() {
    end();
  }
}
