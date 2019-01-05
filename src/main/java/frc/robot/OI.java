/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import frc.robot.commands.MoveLift;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
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

  /*
    XBoxController Mapping:
    
    The buttons on the controller follow this mapping
    1: A
    2: B
    3: X
    4: Y
    5: Left Bumper
    6: Right Bumper
    7: Back
    8: Start
    9: Left Joystick
    10: Right Joystick

    The axis on the controller follow this mapping
    (all output is between -1 and 1)

    1: Left Stick X Axis
    -Left:Negative ; Right: Positive
    2: Left Stick Y Axis
    -Up: Negative ; Down: Positive
    3: Triggers
    -Left: Positive ; Right: Negative
    4: Right Stick X Axis
    -Left: Negative ; Right: Positive
    5: Right Stick Y Axis
    -Up: Negative ; Down: Positive
    6: Directional Pad (Not recommended, buggy)
  */

  //Declaration of XBox Controller and Buttons
  XboxController controller;
  Button lift;
  Button lower;

  //Initializiation of Controller and Buttons
  public void init(){
    controller = new XboxController(RobotMap.controller);
    //1 corresponds with button A (see mapping above)
    lift = new JoystickButton(controller, 1);
    lower = new JoystickButton(controller, 2);

    //When A button is pressed, raise the lift up for 10 seconds
    lift.whenPressed(new MoveLift(10, true));
    //When B button is pressed, lower the lift down for 10 seconds
    lower.whenPressed(new MoveLift(10, false));
  }

  //Accessor Method for the Controller
  public XboxController getController(){
    return controller;
  }
}
