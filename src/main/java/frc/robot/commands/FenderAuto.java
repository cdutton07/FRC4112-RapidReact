// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class FenderAuto extends Command {
  Timer timer = new Timer();
  public FenderAuto() {
    requires(Robot.hood);
    requires(Robot.shooter);
    requires(Robot.driveTrain);
    requires(Robot.intake);
  }

  @Override
  protected void initialize() {
    timer.reset();
    timer.start();
  }

  @Override
  protected void execute() {
    if(timer.get() < 1.2 ) {
      Robot.driveTrain.DT_COORDINATOR.inplaceRotate(0.6);
      Robot.hood.raiseHood(4.75);
      Robot.intake.startIntake(0.35, 0.45);

    }
    /*else if((timer.get()) > 1.25 && (timer.get() < 2.25)) {
      Robot.driveTrain.DT_COORDINATOR.driveSwerveDrive(0, 0, 0);
      Robot.intake.startIntake(0, 0);
      Robot.hood.raiseHood(4.75);
      Robot.driveTrain.DT_COORDINATOR.inplaceRotate(0.6);
    } 
    else if((timer.get()) > 2.25 && (timer.get() < 3.0)) {
      Robot.hood.raiseHood(4.75);
      Robot.driveTrain.DT_COORDINATOR.inplaceRotate(0);
      Robot.driveTrain.DT_COORDINATOR.driveSwerveDrive(0, -0.6, 0);
    }
    else if((timer.get() > 3.0) && (timer.get() < 5.5)) {
      Robot.hood.raiseHood(4.75);
      Robot.shooter.tShooter(0.85);
      Robot.intake.startRollers(0, 0);
      Robot.driveTrain.DT_COORDINATOR.driveSwerveDrive(0, -0.6, 0);
    }
    */
  }

  @Override
  protected boolean isFinished() {
    if(timer.get() > 1.2) {
      return true;
    }
    else {
      return false;
    }
  }

  @Override
  protected void end() {
    Robot.hood.tHood(0);
    Robot.shooter.tShooter(0);
    Robot.intake.startIntake(0, 0);
    Robot.intake.startRollers(0, 0);
    Robot.driveTrain.DT_COORDINATOR.driveSwerveDrive(0, 0, 0);
  }

  @Override
  protected void interrupted() {
    end();
  }
}
