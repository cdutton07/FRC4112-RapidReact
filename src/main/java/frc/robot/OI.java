
package frc.robot;

import edu.wpi.first.wpilibj.PS4Controller;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import frc.robot.commands.AutoAim;
import frc.robot.commands.FeedBall;
import frc.robot.commands.FenderAuto;
import frc.robot.commands.RHood1;
import frc.robot.commands.RHood2;
import frc.robot.commands.RHood3;
import frc.robot.commands.RHood4;
import frc.robot.commands.ReverseIntake;
import frc.robot.commands.StartIntake;
import frc.robot.commands.StartShooter;


public class OI {

  public PS4Controller driverController = new PS4Controller(RobotMap.DRIVE_CONTROLLER);
  public PS4Controller turretController = new PS4Controller(RobotMap.TURRET_CONTROLLER);

  //Driver Controller Buttons
  public JoystickButton dbuttonRB = new JoystickButton(driverController, RobotMap.RIGHT_BUMPER);
  public JoystickButton dbuttonLB = new JoystickButton(driverController, RobotMap.LEFT_BUMPER);
  public JoystickButton dbuttonRT = new JoystickButton(driverController, RobotMap.RIGHT_TRIGGER);
  public JoystickButton dbuttonLT = new JoystickButton(driverController, RobotMap.LEFT_TRIGGER);
  public JoystickButton dbuttonA = new JoystickButton(driverController, RobotMap.BUTTON_A);
  public JoystickButton dbuttonB = new JoystickButton(driverController, RobotMap.BUTTON_B);
  public JoystickButton dbuttonX = new JoystickButton(driverController, RobotMap.BUTTON_X);
  public JoystickButton dbuttonY = new JoystickButton(driverController, RobotMap.BUTTON_Y);
  
  //Turret Controller Buttons
  public JoystickButton tbuttonRB = new JoystickButton(turretController, RobotMap.RIGHT_BUMPER);
  public JoystickButton tbuttonLB = new JoystickButton(turretController, RobotMap.LEFT_BUMPER);
  public JoystickButton tbuttonRT = new JoystickButton(turretController, RobotMap.RIGHT_TRIGGER);
  public JoystickButton tbuttonLT = new JoystickButton(turretController, RobotMap.LEFT_TRIGGER);
  public JoystickButton tbuttonA = new JoystickButton(turretController, RobotMap.BUTTON_A);
  public JoystickButton tbuttonB = new JoystickButton(turretController, RobotMap.BUTTON_B);
  public JoystickButton tbuttonX = new JoystickButton(turretController, RobotMap.BUTTON_X);
  public JoystickButton tbuttonY = new JoystickButton(turretController, RobotMap.BUTTON_Y);

  public double GetDControlRawAxis(int axis) {
    return driverController.getRawAxis(axis);
  }

  public double GetTControlRawAxis(int axis) {
    return turretController.getRawAxis(axis);
  }

  public OI() {
    tbuttonRT.whileHeld(new AutoAim());
    dbuttonLT.whileHeld(new StartIntake());
    dbuttonRT.whileHeld(new ReverseIntake());
    tbuttonRB.whileHeld(new FeedBall());
    tbuttonX.whileHeld(new RHood1());
    tbuttonY.whileHeld(new RHood2());
    tbuttonB.whileHeld(new RHood3());
    tbuttonA.whileHeld(new RHood4());
    dbuttonA.whenPressed(new FenderAuto());
  }
  
  //// CREATING BUTTONS
  // One type of button is a joystick button which is any button on a
  //// joystick.
  // You create one by telling it which joystick it's on and which button
  // number it is.
  // Joystick stick = new Joystick(port);
  // Button button = new JoystickButton(stick, buttonNumber);

  // There are a few additional built in buttons you can use. Additionally,
  // by subclassing Button you can create custom triggers and bind those to
  // commands the same as any other Button.

  //// TRIGGERING COMMANDS WITH BUTTONS
  // Once you have a button, it's trivial to bind it to a button in one of
  // three ways:

  // Start the command when the button is pressed and let it run the command
  // until it is finished as determined by it's isFinished method.
  // button.whenPressed(new ExampleCommand());

  // Run the command while the button is being held down and interrupt it once
  // the button is released.
  // button.whileHeld(new ExampleCommand());

  // Start the command when the button is released and let it run the command
  // until it is finished as determined by it's isFinished method.
  // button.whenReleased(new ExampleCommand());
}
