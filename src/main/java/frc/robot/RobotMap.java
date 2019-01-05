/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
  // For example to map the left and right motors, you could define the
  // following variables to use with your drivetrain subsystem.
  // public static int leftMotor = 1;
  // public static int rightMotor = 2;

  // If you are using multiple modules, make sure to define both the port
  // number and the module. For example you with a rangefinder:
  // public static int rangefinderPort = 1;
  // public static int rangefinderModule = 1;

  // This class contains constants that will be used throughout the project.
  // For the purpose of showcasing, all constants are placeholder values.

  //Drive Motors Ports
  public static int frontRightDrive = 0;
  public static int frontLeftDrive = 1;
  public static int backLeftDrive = 2;
  public static int backRightDrive = 3;

  //Lifting Motor Port
  public static int liftMotor = 4;

  //XBox Controller Port
  public static int controller = 0;

  //Encoder Digital Input Channels
  public static int channelA = 0;
  public static int channelB = 1;

  //Analog Input Channels
  public static int gyroChannel = 0;
  public static int potChannel = 1;

  //Distance Multiplier from testing with the encoder
  public static int DistMult = -80;
  
  //Potentiometer Voltages (Limits)
  public static int topPotVoltage = 5;
  public static int bottomPotVoltage = 5;
}
