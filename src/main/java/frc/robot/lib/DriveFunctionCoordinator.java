// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.lib;

/** Add your docs here. */

public class DriveFunctionCoordinator {
    SwerveDriveWheel BL_WHEEL;
    SwerveDriveWheel BR_WHEEL;
    SwerveDriveWheel FL_WHEEL;
    SwerveDriveWheel FR_WHEEL;
    public static final double kP = 0.8;

    public DriveFunctionCoordinator(SwerveDriveWheel BL_WHEEL, SwerveDriveWheel BR_WHEEL, SwerveDriveWheel FL_WHEEL, SwerveDriveWheel FR_WHEEL) {
        this.BL_WHEEL = BL_WHEEL;
        this.BR_WHEEL = BR_WHEEL;
        this.FL_WHEEL = FL_WHEEL;
        this.FR_WHEEL = FR_WHEEL;
    }

    public void dtSpeed(double speed) {
        BL_WHEEL.setSpeed(speed);
        BR_WHEEL.setSpeed(-speed);
        FL_WHEEL.setSpeed(speed);
        FR_WHEEL.setSpeed(-speed);
    }

    public void translate(double angle, double speed) {
        BL_WHEEL.setAngle(-angle);
        BR_WHEEL.setAngle(-angle);
        FL_WHEEL.setAngle(angle);
        FR_WHEEL.setAngle(-angle);

        BL_WHEEL.setSpeed(speed);
        BR_WHEEL.setSpeed(-speed);
        FL_WHEEL.setSpeed(speed);
        FR_WHEEL.setSpeed(-speed);
    }

    public void inplaceRotate(double speed) {
        BL_WHEEL.setAngle(45.0);
        BR_WHEEL.setAngle(-45.0);
        FL_WHEEL.setAngle(135.0);
        FR_WHEEL.setAngle(-135.0);

        BL_WHEEL.setSpeed(-(speed * kP));
        BR_WHEEL.setSpeed(-(speed * kP));
        FL_WHEEL.setSpeed(speed * kP);
        FR_WHEEL.setSpeed(speed * kP);
    }    

    public void translateTurn(double angle, double translateSpeed, double turnSpeed) {
        double turnAngle = turnSpeed * 50.0;

        // Left Front wheel position
        if (SwerveDriveWheel.calcCloseAngle(angle, 135.0) >= 90.0) {
            FL_WHEEL.setAngle(-(angle + turnAngle));
        }
        else {
            FL_WHEEL.setAngle(-(angle - turnAngle));
        }
        // Left Back wheel position
        if(SwerveDriveWheel.calcCloseAngle(angle, 225.0) > 90.0) {
            BL_WHEEL.setAngle(-(angle + turnAngle));
        }
        else {
            BL_WHEEL.setAngle(-(angle - turnAngle));
        }
        // Right Front wheel position
        if(SwerveDriveWheel.calcCloseAngle(angle, 45.0) > 90.0) {
            FR_WHEEL.setAngle((angle + turnAngle));
        }
        else {
            FR_WHEEL.setAngle((angle - turnAngle));
        }
        // Right Back wheel postion
        if(SwerveDriveWheel.calcCloseAngle(angle, 315.0) >= 90.0) {
            BR_WHEEL.setAngle(-(angle + turnAngle));
        }
        else {
            BR_WHEEL.setAngle(-(angle - turnAngle));
        }

        // Wheel Speeds
        FL_WHEEL.setSpeed(translateSpeed * kP);
        FR_WHEEL.setSpeed(-(translateSpeed *kP));
        BL_WHEEL.setSpeed(translateSpeed * kP);
        BR_WHEEL.setSpeed(-(translateSpeed * kP));
    }



    public void translateStrafe(double angle, double translateSpeed, double turnSpeed) {
        double turnAngle = turnSpeed * 45.0;

        // Left Front wheel position
        if (SwerveDriveWheel.calcCloseAngle(angle, 135.0) >= 90.0) {
            FL_WHEEL.setAngle(-(angle + turnAngle));
        }
        else {
            FL_WHEEL.setAngle(-(angle - turnAngle));
        }
        // Left Back wheel position
        if(SwerveDriveWheel.calcCloseAngle(angle, 225.0) > 90.0) {
            BL_WHEEL.setAngle((angle + turnAngle));
        }
        else {
            BL_WHEEL.setAngle((angle - turnAngle));
        }
        // Right Front wheel position
        if(SwerveDriveWheel.calcCloseAngle(angle, 45.0) > 90.0) {
            FR_WHEEL.setAngle((angle + turnAngle));
        }
        else {
            FR_WHEEL.setAngle((angle - turnAngle));
        }
        // Right Back wheel postion
        if(SwerveDriveWheel.calcCloseAngle(angle, 315.0) >= 90.0) {
            BR_WHEEL.setAngle((angle + turnAngle));
        }
        else {
            BR_WHEEL.setAngle((angle - turnAngle));
        }

        // Wheel Speeds
        FL_WHEEL.setSpeed(translateSpeed *kP);
        FR_WHEEL.setSpeed(-(translateSpeed * kP));
        BL_WHEEL.setSpeed(translateSpeed * kP);
        BR_WHEEL.setSpeed(-(translateSpeed * kP));
    }

    public void strafe(double speed, double turnSpeed) {
        double turnAngle = turnSpeed * 45.0;

        // Left Front wheel position
        if (SwerveDriveWheel.calcCloseAngle(90, 135.0) >= 90.0) {
            FL_WHEEL.setAngle(-(90 + turnAngle));
        }
        else {
            FL_WHEEL.setAngle(-(90 - turnAngle));
        }
        // Left Back wheel position
        if(SwerveDriveWheel.calcCloseAngle(90, 225.0) > 90.0) {
            BL_WHEEL.setAngle((90 + turnAngle));
        }
        else {
            BL_WHEEL.setAngle((90 - turnAngle));
        }
        // Right Front wheel position
        if(SwerveDriveWheel.calcCloseAngle(90, 45.0) > 90.0) {
            FR_WHEEL.setAngle((90 + turnAngle));
        }
        else {
            FR_WHEEL.setAngle((90 - turnAngle));
        }
        // Right Back wheel postion
        if(SwerveDriveWheel.calcCloseAngle(90, 315.0) >= 90.0) {
            BR_WHEEL.setAngle((90 + turnAngle));
        }
        else {
            BR_WHEEL.setAngle((90 - turnAngle));
        }

        FL_WHEEL.setSpeed((speed * kP));
        FR_WHEEL.setSpeed((speed * kP));
        BL_WHEEL.setSpeed(-(speed * kP));
        BR_WHEEL.setSpeed((speed * kP));
    }

    public void driveSwerveDrive(double angle, double translateSpeed, double turnSpeed) {
        if((translateSpeed == 0.0) && (turnSpeed != 0.0)) {
            inplaceRotate(turnSpeed);
        }
        else {
            translateTurn(angle, translateSpeed, turnSpeed);
        }
    }
}
