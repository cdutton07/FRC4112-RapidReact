// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import org.photonvision.PhotonUtils;

import edu.wpi.first.math.util.Units;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;

public class AutoAim extends Command {
  public AutoAim() {
    requires(Robot.turret);
    requires(Robot.shooter);
  }

  @Override
  protected void initialize() {
    Robot.turret.camera.setDriverMode(false);
  }

  @Override
  protected void execute() {
    var lastResult = Robot.turret.camera.getLatestResult();
    if(lastResult.hasTargets()) {
      double targetDistance = PhotonUtils.calculateDistanceToTargetMeters(Robot.turret.cameraHeight, Robot.turret.targetHeight, Robot.turret.cameraPitch, Units.degreesToRadians(lastResult.getBestTarget().getPitch()));
      double rotateSpeed = -Robot.turret.tController.calculate(lastResult.getBestTarget().getYaw(), 0);
      SmartDashboard.putNumber("Auto Aim Command", -Robot.turret.tController.calculate(lastResult.getBestTarget().getYaw(), 0));
      SmartDashboard.putNumber("Target Distance", PhotonUtils.calculateDistanceToTargetMeters(Robot.turret.cameraHeight, Robot.turret.targetHeight, Robot.turret.cameraPitch, Units.degreesToRadians(lastResult.getBestTarget().getPitch())));
      Robot.turret.tRTurret(rotateSpeed);
      if (targetDistance <= 0.01) {
        Robot.hood.raiseHood(2.8);
        Robot.shooter.tShooter(0.6);
      }
      else if(targetDistance <= 0.125 && targetDistance > 0.01) {
        Robot.hood.raiseHood(3.5);
        Robot.shooter.tShooter(0.65);
      }
      else if(targetDistance > 0.125 && targetDistance <= 0.45) {
        Robot.hood.raiseHood(3.75);
        Robot.shooter.tShooter(0.78);
      }
      else if(targetDistance > 0.45 && targetDistance <= 0.52) {
        Robot.hood.raiseHood(3.75);
        Robot.shooter.tShooter(0.82);;
      }
      else if(targetDistance > 0.52) {
        Robot.hood.raiseHood(4);
        Robot.shooter.tShooter(0.87);
      }
      else {
        Robot.hood.lowerHood();
        Robot.shooter.tShooter(0.85);
      }
      
    }
    else {
      double rotateSpeed = 0;
      Robot.turret.tRTurret(rotateSpeed);
      Robot.shooter.tShooter(0.72);
    }
  }

  @Override
  protected boolean isFinished() {
    return false;
  }

  @Override
  protected void end() {
    Robot.turret.camera.setDriverMode(true);
    Robot.turret.tRTurret(0);
    Robot.shooter.tShooter(0);
  }

  @Override
  protected void interrupted() {
    end();
  }
}
