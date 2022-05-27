// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

/** Add your docs here. */
public class Intake extends Subsystem {
  public static TalonFX Intake;
  public static CANSparkMax Indexer;
  public static TalonFX intakeDeploy;
  public CANSparkMax sRollers;
  public double kP, kI, kD;

  public Intake() {
    //Initialize all objects
    Intake = new TalonFX(RobotMap.INTAKE);
    Indexer = new CANSparkMax(RobotMap.INDEXER, MotorType.kBrushless);
    intakeDeploy = new TalonFX(RobotMap.INTAKE_DEPLOY);
    sRollers = new CANSparkMax(RobotMap.S_ROLLERS, MotorType.kBrushless);
  }

  public void startIntake(double intSpeed, double indSpeed) {
    Intake.set(ControlMode.PercentOutput, intSpeed);
    Indexer.set(indSpeed);
  }

  public void startRollers(double rSpeed, double iSpeed) {
    sRollers.set(rSpeed);
    Indexer.set(iSpeed);
  }

  public void deployIntake(double revs) {
    intakeDeploy.set(ControlMode.PercentOutput, revs);
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}
