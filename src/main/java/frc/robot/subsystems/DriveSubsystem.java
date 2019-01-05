/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.MecanumDrive;
import frc.robot.Robot;
import frc.robot.RobotMap;
import frc.robot.commands.MecanumCommand;

public class DriveSubsystem extends Subsystem {

  /* Declarations:
      Victor - Motors
      SpeedController - Organize motors into groups
      MecanumDrive - Similiar to DifferentialDrive, take in 4 motors for mecanum drive
      Encoder - Measure the distance travelled of a motor by measuring its rotation
      AnalogGyro - Sensor that measures the angle of the robot from a starting position
  */
  Victor frontRightDrive, frontLeftDrive, backLeftDrive, backRightDrive;

  SpeedController leftDrive, rightDrive;

  MecanumDrive mecanum;

  Encoder encoder;
  AnalogGyro gyro;

  // Constructor: For Initialization
  public DriveSubsystem(){
    //Initialize each motor to its corresponding port in RobotMap
    frontRightDrive = new Victor(RobotMap.frontRightDrive);
    frontLeftDrive = new Victor(RobotMap.frontLeftDrive);
    backLeftDrive = new Victor(RobotMap.backLeftDrive);
    backRightDrive = new Victor(RobotMap.backRightDrive);

    //Initialize SpeedController - Organize 4 drive motors into left and right side
    leftDrive = new SpeedControllerGroup(frontLeftDrive, backLeftDrive);
    rightDrive = new SpeedControllerGroup(frontRightDrive, backRightDrive){
      /*
      In a situation where one of the motor may be inverted, instead of manually
      adding a negative sign for every instance the speed is needed, overriding the
      set(speed) method allows us to invert the power of the motor from the beginning.
      */
      @Override
      public void set(double speed) {
          super.set(-speed);
      }
    }; 
    
    //Initialize MecanumDrive with 4 motors
    mecanum = new MecanumDrive(frontLeftDrive, backLeftDrive, frontRightDrive, backRightDrive);

    
    /** From Encoder.class
     * Encoder constructor. Construct a Encoder given a and b channels.
     *
     * <p>The encoder will start counting immediately.
     *
     * @param channelA         The a channel digital input channel.
     * @param channelB         The b channel digital input channel.
     * @param reverseDirection represents the orientation of the encoder and inverts the output values
     *                         if necessary so forward represents positive values.
     * @param encodingType     either k1X, k2X, or k4X to indicate 1X, 2X or 4X decoding. If 4X is
     *                         selected, then an encoder FPGA object is used and the returned counts
     *                         will be 4x the encoder spec'd value since all rising and falling edges
     *                         are counted. If 1X or 2X are selected then a m_counter object will be
     *                         used and the returned value will either exactly match the spec'd count
     *                         or be double (2x) the spec'd count.
     */

    //Ctrl + Click Encoder —> open Encoder.class
    encoder = new Encoder(RobotMap.channelA, RobotMap.channelB, false, Encoder.EncodingType.k4X);
    encoder.setDistancePerPulse(5);

    //Initialize Gyro with its analog input channel from RobotMap
    gyro = new AnalogGyro(RobotMap.gyroChannel);
    gyro.reset();
  }

  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  /*
    Xbox Controller Axis Values
    1: Left Stick X Axis
    2: Left Stick Y Axis
    3: Triggers
    4: Right Stick X Axis
    5: Right Stick Y Axis
  */
  
  //Cartesian Drive - 1 of 2 types of Mecanum Drive, primarily used for teleop, take in Y-Axis, X-Axis, Z-Axis (for 2 joysticks, use X-Axis for the second), and Gyro Angle
  public void mecanumDrive(){
    mecanum.driveCartesian(Robot.m_oi.getController().getRawAxis(2), Robot.m_oi.getController().getRawAxis(1), Robot.m_oi.getController().getRawAxis(4), gyro.getAngle());
  }

  //Drive Straight to a distance using encoders and gyro
  public void driveStraight(double distance) {
    //Yaw is the horizontal angle that is measured by the gyro
    double lastYaw = gyro.getAngle();
    //Multiplier used to calculate with the value returned by the encoder to find the actual distance travelled
    double mult = RobotMap.DistMult;
    //Clearing the encoder of any stored value before measuring new distance
    encoder.reset();
    //While the measured distance by the encoder is less than or equal to the desired distance, keep driving forward
		while (encoder.getDistance() / mult <= distance) {
			System.out.println(encoder.getDistance()+" "+encoder.getDistance()/mult+" "+distance);
      drive(.5);
      Timer.delay(.01);
      //Checking the current yaw/horizontal angle every .01 seconds
      double curYaw = gyro.getAngle();
      //Subtracting the current angle with the last measured angle to find the angle that the robot needs to turn
      //to keep the robot on a straight path
			autoCorrect(curYaw - lastYaw);
			lastYaw = curYaw;
    }
    //When finished, stop drive motors
		stopDrive();
  }

  //Method that ensures the robot continues on a straight-ish path
  public void autoCorrect(double degrees) {
		if (degrees < 0) rotateRight(degrees); 
		else rotateLeft(degrees);	
	}
  
  /**
	 * Turns the robot a number of degrees
	 * 
	 * @param degrees
	 * the degrees the robot should turn
	 */
	public void rotateLeft(double degrees) {
		if (gyro.getAngle() < degrees) {
			stopDrive();
		} else {
			rightDrive.set(0.5);
			leftDrive.set(-0.5);
		}
  }
  
	public void rotateRight(double degrees) {
		if (gyro.getAngle() > degrees) {
			stopDrive();
		} else {
			rightDrive.set(-0.5);
			leftDrive.set(0.5);
		}
	}

  //The robot will drive in a "straight" path at the specified speed
  public void drive(double speed){
    leftDrive.set(speed);
    rightDrive.set(speed);
  }

  public void stopDrive(){
    leftDrive.set(0);
    rightDrive.set(0);
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
    setDefaultCommand(new MecanumCommand());
  }
}
