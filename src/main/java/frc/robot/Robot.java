
package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.commands.FenderAuto;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Hood;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.Turret;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  public static DriveTrain driveTrain;
  public static Shooter shooter;
  public static Intake intake;
  public static Turret turret;
  public static Hood hood;

  public static OI m_oi;

  Command m_autonomousCommand;
  SendableChooser<Command> m_chooser = new SendableChooser<>();

  /**
   * This function is run when the robot is first started up and should be
   * used for any initialization code.
   */
  @Override
  public void robotInit() {
    driveTrain = new DriveTrain();
    shooter = new Shooter();
    intake = new Intake();
    turret = new Turret();
    hood = new Hood();

    m_oi = new OI();
    m_autonomousCommand = new FenderAuto();
  /**
   * m_chooser.setDefaultOption("Default Auto", new ExampleCommand());
   * m_chooser.addOption("My Auto", new MyAutoCommand());
   * SmartDashboard.putData("Auto mode", m_chooser);
   */
  }

  /**
   * This function is called every robot packet, no matter the mode. Use
   * this for items like diagnostics that you want ran during disabled,
   * autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before
   * LiveWindow and SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {
  }

  /**
   * This function is called once each time the robot enters Disabled mode.
   * You can use it to reset any subsystem information you want to clear when
   * the robot is disabled.
   */
  @Override
  public void disabledInit() {
  }

  @Override
  public void disabledPeriodic() {
    Scheduler.getInstance().run();
  }

  /**
   * This autonomous (along with the chooser code above) shows how to select
   * between different autonomous modes using the dashboard. The sendable
   * chooser code works with the Java SmartDashboard. If you prefer the
   * LabVIEW Dashboard, remove all of the chooser code and uncomment the
   * getString code to get the auto name from the text box below the Gyro
   *
   * <p>You can add additional auto modes by adding additional commands to the
   * chooser code above (like the commented example) or additional comparisons
   * to the switch structure below with additional strings & commands.
   */
  @Override
  public void autonomousInit() {

    /*
     * String autoSelected = SmartDashboard.getString("Auto Selector",
     * "Default"); switch(autoSelected) { case "My Auto": autonomousCommand
     * = new MyAutoCommand(); break; case "Default Auto": default:
     * autonomousCommand = new ExampleCommand(); break; }
     */

    // schedule the autonomous command (example)
    if (m_autonomousCommand != null) {
      m_autonomousCommand.start();
    }
  }

  /**
   * This function is called periodically during autonomous.
   */
  @Override
  public void autonomousPeriodic() {
    Scheduler.getInstance().run();
  }

  @Override
  public void teleopInit() {
    // This makes sure that the autonomous stops running when
    // teleop starts running. If you want the autonomous to
    // continue until interrupted by another command, remove
    // this line or comment it out.
    if (m_autonomousCommand != null) {
      m_autonomousCommand.cancel();
    }
  }

  /**
   * This function is called periodically during operator control.
   */
  @Override
  public void teleopPeriodic() {
    Scheduler.getInstance().run();
    SmartDashboard.putNumber("CANCoder BL", DriveTrain.BL_ANGLE_E.getAbsolutePosition());
    SmartDashboard.putNumber("CANCoder BR", DriveTrain.BR_ANGLE_E.getAbsolutePosition());
    SmartDashboard.putNumber("CANCoder FL", DriveTrain.FL_ANGLE_E.getAbsolutePosition());
    SmartDashboard.putNumber("CANCoder FR", DriveTrain.FR_ANGLE_E.getAbsolutePosition());   
    SmartDashboard.putNumber("FR Angle Motor", DriveTrain.FR_ANGLE_M.getMotorOutputPercent());
    SmartDashboard.putNumber("FL Angle Motor", DriveTrain.FL_ANGLE_M.getMotorOutputPercent());
    SmartDashboard.putNumber("BR Angle Motor", DriveTrain.BR_ANGLE_M.getMotorOutputPercent());
    SmartDashboard.putNumber("BL Angle Motor", DriveTrain.BL_ANGLE_M.getMotorOutputPercent());
    SmartDashboard.putNumber("FR Speed Motor", DriveTrain.FR_SPEED_M.getMotorOutputPercent());
    SmartDashboard.putNumber("FL Speed Motor", DriveTrain.FL_SPEED_M.getMotorOutputPercent());
    SmartDashboard.putNumber("BR Speed Motor", DriveTrain.BR_SPEED_M.getMotorOutputPercent());
    SmartDashboard.putNumber("BL Speed Motor", DriveTrain.BL_SPEED_M.getMotorOutputPercent());
    SmartDashboard.putNumber("Hood Encoder", Hood.hEncoder.getPosition());
    SmartDashboard.putNumber("Turret Encoder", Turret.tEncoder.getPosition());
    //SmartDashboard.putNumber("Target Pitch", camera.getLatestResult().getBestTarget().getPitch());
    //SmartDashboard.putNumber("Target Skew", camera.getLatestResult().getBestTarget().getPitch());
    //SmartDashboard.putNumber("Target Area", camera.getLatestResult().getBestTarget().getArea());
  }

  /**
   * This function is called periodically during test mode.
   */
  @Override
  public void testPeriodic() {
  }
}
