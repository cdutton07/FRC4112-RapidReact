// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.RobotMap;

public class RotateTurret extends Command {
  public RotateTurret() {
    requires(Robot.turret);
  }

  @Override
  protected void initialize() {}

  @Override
  protected void execute() {
    double speed = deadband(Robot.m_oi.turretController.getRawAxis(RobotMap.LEFT_X_AXIS), 0.3);

    if(speed <= 0 && (Robot.turret.getTPosition() >= -190)) {
      Robot.turret.tRTurret(speed * 0.7);
    }
    else if (speed <= 0 && (Robot.turret.getTPosition() < -190)) {
      Robot.turret.tRTurret(0);
    }
    else if(speed > 0 && (Robot.turret.getTPosition() <= 90)) {
      Robot.turret.tRTurret(speed * 0.7);
    }
    else if(speed > 0 && (Robot.turret.getTPosition() > 90)) {
      Robot.turret.tRTurret(0);
    }

  }

  @Override
  protected boolean isFinished() {
    return false;
  }

  @Override
  protected void end() {
    //Robot.turret.tRTurret(0);
  }

  @Override
  protected void interrupted() {
    end();
  }

  private double deadband(double val, double deadzone)
  {
    if(Math.abs(val) < deadzone) {
      return 0;
    }
    return val;
  }
}
