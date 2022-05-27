// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.RobotMap;

public class DriveSDT extends Command {
  public DriveSDT() {
    requires(Robot.driveTrain);
  }

  @Override
  protected void initialize() {
    Robot.driveTrain.DT_COORDINATOR.driveSwerveDrive(0, 0, 0);
  }

  @Override
  protected void execute() {
    double angle = Math.atan2(Robot.m_oi.driverController.getRawAxis(RobotMap.LEFT_Y_AXIS), Robot.m_oi.driverController.getRawAxis(RobotMap.LEFT_X_AXIS));
    double power = deadband(Robot.m_oi.driverController.getRawAxis(RobotMap.LEFT_Y_AXIS), 0.3);
    double rotation = deadband(Robot.m_oi.driverController.getRawAxis(RobotMap.RIGHT_X_AXIS), 0.2);
    double strafe = deadband(Robot.m_oi.driverController.getRawAxis(RobotMap.LEFT_X_AXIS), 0.2);
    double traDir = deadband(Robot.m_oi.driverController.getRawAxis(RobotMap.LEFT_X_AXIS), 0.35);
    double strDir = deadband(Robot.m_oi.driverController.getRawAxis(RobotMap.RIGHT_Y_AXIS), 0.2);

    if(power == 0 && strafe != 0) {
      Robot.driveTrain.DT_COORDINATOR.strafe(strafe, strDir);
    }
    else if(power > 0 && traDir != 0) {
      Robot.driveTrain.DT_COORDINATOR.translateStrafe(angle, -power, -traDir);
    }
    else if(power < 0 && traDir != 0) {
      Robot.driveTrain.DT_COORDINATOR.translateStrafe(angle, -power, traDir);
    }
    else if(power > 0 && rotation != 0) {
      Robot.driveTrain.DT_COORDINATOR.driveSwerveDrive(-angle, -power, rotation);
    }
    else if(power < 0 && rotation != 0) {
      Robot.driveTrain.DT_COORDINATOR.driveSwerveDrive(-angle, -power, rotation);
    }
    else {
      Robot.driveTrain.DT_COORDINATOR.driveSwerveDrive(angle, -power, -rotation);;
    }
  }

  @Override
  protected boolean isFinished() {
    return false;
  }

  @Override
  protected void end() {
    Robot.driveTrain.DT_COORDINATOR.driveSwerveDrive(0.0, 0.0, 0.0);
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
