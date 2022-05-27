// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import org.photonvision.PhotonCamera;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.util.Units;
import edu.wpi.first.util.net.PortForwarder;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.commands.RotateTurret;

/** Add your docs here. */
public class Turret extends Subsystem {
  public static CANSparkMax Turret;
  public static RelativeEncoder tEncoder;
  public PIDController tController;
  public PhotonCamera camera;
  public double kP, kI, kD;
  public final double cameraHeight, targetHeight, cameraPitch;

  public Turret() {
    //Initialize all objects
    Turret = new CANSparkMax(RobotMap.TURRET, MotorType.kBrushless);
    cameraHeight = Units.inchesToMeters(32.5);
    targetHeight = Units.inchesToMeters(103);
    cameraPitch = Units.degreesToRadians(78);
    camera = new PhotonCamera("photonvision");
    PortForwarder.add(5800, "photonvision.local", 5800); 
    PortForwarder.add(1181, "photonvision.local", 1181); 
    PortForwarder.add(1182, "photonvision.local", 1182); 
    kP = 0.05;
    kI = 0.01;
    kD = 0.0;
    tController = new PIDController(kP, kI, kD);
    tEncoder = Turret.getEncoder();
  }

  public void rotateTurretLeft() {
    double currentPos = tEncoder.getPosition();
    double goalPos = currentPos - 95;
    if(currentPos > goalPos) {
      Turret.set(-0.6);
    }
    else if(currentPos <= goalPos) {
      Turret.set(0);
    }
  }

  public void rotateTurretRight() {
    double currentPos = tEncoder.getPosition();
    double goalPos = currentPos + 95;
    if(currentPos > goalPos) {
      Turret.set(0.6);
    }
    else if(currentPos >= goalPos) {
      Turret.set(0);
    }
  }

  public void tRTurret(double speed) {
    Turret.set(speed);
  }

  public double getTPosition() {
    double pos = tEncoder.getPosition();
    return pos;
  }

  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new RotateTurret());
  }
}
