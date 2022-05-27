// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.commands.LowerHood;

/** Add your docs here. */
public class Hood extends Subsystem {
  public static CANSparkMax Hood;
  public static RelativeEncoder hEncoder;

  public Hood() {
    //Initialize all objects
    Hood = new CANSparkMax(RobotMap.ADJUST_HOOD, MotorType.kBrushless);
    hEncoder = Hood.getEncoder();
  }


  public void raiseHood(double pos) {
    double currentPos = hEncoder.getPosition();
    if(currentPos < pos) {
      Hood.set(0.05);
    }
    else if(currentPos > pos) {
      Hood.set(-0.005);
    }
    else if(currentPos >= pos) {
      Hood
      .set(0.0025);
    }
  }

  public void lowerHood() {
    double currentPos = hEncoder.getPosition();
    if(currentPos > 0) {
      Hood.set(-0.05);
    }
    else {
      Hood.set(0);
    }
  }

  public void tHood(double speed) {
    Hood.set(speed);
  }

  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new LowerHood());
  }
}
