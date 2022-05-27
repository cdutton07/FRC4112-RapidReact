// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.lib;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.ctre.phoenix.sensors.CANCoder;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/** Add your docs here. */
public class SwerveDriveWheel {
    public PIDController angleController;
    public TalonFX speedMotor;
    public TalonFX angleMotor;
    public CANCoder angleEncoder;

    public SwerveDriveWheel(double P, double I, double D, CANCoder angleEncoder, TalonFX angleMotor, TalonFX speedMotor) {
        this.angleEncoder = angleEncoder;
        this.angleMotor = angleMotor;
        this.speedMotor = speedMotor;
        angleController = new PIDController(P, I, D);
    }

    public static double calcCloseAngle(double a, double b) {
        double angle = (b % 360.0) - (a % 360.0);

        if (Math.abs(angle) > 180)
        {
            angle = -(Math.signum(angle) * 360.0) + angle;
        }
        return angle;
    }

    public void setAngle(double setpoint) {
        angleController.reset();
        double currentAngle = angleEncoder.getAbsolutePosition();
        angleController.setSetpoint(currentAngle + calcCloseAngle(currentAngle, setpoint));

        SmartDashboard.putNumber("angleMotor Output", angleController.calculate(angleEncoder.getAbsolutePosition(), setpoint));
        angleMotor.set(ControlMode.PercentOutput, MathUtil.clamp(angleController.calculate(angleEncoder.getAbsolutePosition(), setpoint), -0.2, 0.2));
    }

    public void setSpeed(double speed) {
        SmartDashboard.putNumber("speedMotor Output", speed);
        speedMotor.set(ControlMode.PercentOutput, (speed * 0.7));
    }
}
