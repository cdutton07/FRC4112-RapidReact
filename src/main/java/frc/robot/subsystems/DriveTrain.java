
package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.ctre.phoenix.sensors.CANCoder;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.commands.DriveSDT;
import frc.robot.lib.DriveFunctionCoordinator;
import frc.robot.lib.SwerveDriveWheel;


public class DriveTrain extends Subsystem {

  // Speed Motors
  public static TalonFX FL_SPEED_M;
  public static TalonFX FR_SPEED_M;
  public static TalonFX BL_SPEED_M;
  public static TalonFX BR_SPEED_M;

  // Angle Motors and Encoders
  public static TalonFX FL_ANGLE_M;
  public static TalonFX FR_ANGLE_M;
  public static TalonFX BL_ANGLE_M;
  public static TalonFX BR_ANGLE_M;

  public static CANCoder FL_ANGLE_E;
  public static CANCoder FR_ANGLE_E;
  public static CANCoder BL_ANGLE_E;
  public static CANCoder BR_ANGLE_E;

  // Gyro
  public static AHRS NAVX_GYRO;

  // Swerve Modules
  public SwerveDriveWheel FL_WHEEL;
  public SwerveDriveWheel FR_WHEEL;
  public SwerveDriveWheel BL_WHEEL;
  public SwerveDriveWheel BR_WHEEL;

  // Drive Coordinator
  public DriveFunctionCoordinator DT_COORDINATOR;
  
  public DriveTrain() {
    // Speed Motors
    FL_SPEED_M = new TalonFX(RobotMap.FL_SPEED_MOTOR);
    FR_SPEED_M = new TalonFX(RobotMap.FR_SPEED_MOTOR);
    BL_SPEED_M = new TalonFX(RobotMap.BL_SPEED_MOTOR);
    BR_SPEED_M = new TalonFX(RobotMap.BR_SPEED_MOTOR);

    // Angle Motors and Encoders
    FL_ANGLE_M = new TalonFX(RobotMap.FL_ANGLE_MOTOR);
    FR_ANGLE_M = new TalonFX(RobotMap.FR_ANGLE_MOTOR);
    BL_ANGLE_M = new TalonFX(RobotMap.BL_ANGLE_MOTOR);
    BR_ANGLE_M = new TalonFX(RobotMap.BR_ANGLE_MOTOR);

    FL_ANGLE_E = new CANCoder(RobotMap.FL_ANGLE_ENCODER);
    FR_ANGLE_E = new CANCoder(RobotMap.FR_ANGLE_ENCODER);
    BL_ANGLE_E = new CANCoder(RobotMap.BL_ANGLE_ENCODER);
    BR_ANGLE_E = new CANCoder(RobotMap.BR_ANGLE_ENCODER);

    // Gyro
    NAVX_GYRO = new AHRS(SPI.Port.kMXP);

    // Swerve Modules
    double kWP = 0.01;
    double kWI = 0.02;
    double kWD = 0.0;
    FL_WHEEL = new SwerveDriveWheel(kWP, kWI, kWD, FL_ANGLE_E, FL_ANGLE_M, FL_SPEED_M);
    FR_WHEEL = new SwerveDriveWheel(kWP, kWI, kWD, FR_ANGLE_E, FR_ANGLE_M, FR_SPEED_M);
    BL_WHEEL = new SwerveDriveWheel(kWP, kWI, kWD, BL_ANGLE_E, BL_ANGLE_M, BL_SPEED_M);
    BR_WHEEL = new SwerveDriveWheel(kWP, kWI, kWD, BR_ANGLE_E, BR_ANGLE_M, BR_SPEED_M);

    // Drive Coordinator 
    DT_COORDINATOR = new DriveFunctionCoordinator(BL_WHEEL, BR_WHEEL, FL_WHEEL, FR_WHEEL);
  }
  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new DriveSDT());
  }
}