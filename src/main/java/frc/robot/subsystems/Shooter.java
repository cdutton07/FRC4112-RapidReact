// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

/** Add your docs here. */
public class Shooter extends Subsystem {
  public static TalonFX shootLeft;
  public static TalonFX shootRight;
  public static PIDController shootControllerL;
  public static PIDController shootControllerR;
  double kP, kI, kD;

  public Shooter() {
    shootLeft = new TalonFX(RobotMap.SHOOT_M1);
    shootRight = new TalonFX(RobotMap.SHOOT_M2);
    kP = 0.01;
    kI = 0.02;
    kD = 0.01;
    shootControllerL = new PIDController(kP, kI, kD);
    shootControllerR = new PIDController(kP, kI, kD);
  }

  public void startLeftShoot(double speed) {
    shootControllerL.reset();
    double currentSpeed = shootLeft.getMotorOutputPercent();
    shootControllerL.setSetpoint(speed);
    shootLeft.set(ControlMode.PercentOutput, MathUtil.clamp(shootControllerL.calculate(currentSpeed, speed), -1, 1));
  }

  public void startRightShoot(double speed) {
    shootControllerR.reset();
    double currentSpeed =  shootRight.getMotorOutputPercent();
    shootControllerR.setSetpoint(speed);
    shootRight.set(ControlMode.PercentOutput, MathUtil.clamp(shootControllerR.calculate(currentSpeed, speed), -1, 1));
  }

  public void tRight(double speed) {
    shootRight.set(ControlMode.PercentOutput, speed);
  }

  public void tLeft(double speed) {
    shootLeft.set(ControlMode.PercentOutput, speed);
  }

  public void startShoot(double speed) {
    startLeftShoot(speed);
    startRightShoot(-speed);
  }

  public void tShooter(double speed) {
    tLeft(speed);
    tRight(-speed);
  }

  @Override
  public void initDefaultCommand() {
    
  }
}
