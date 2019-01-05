/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;

import frc.robot.RobotMap;

public class LiftSubsystem extends Subsystem {

  //Declaration of a motor and potentiometer
  Victor liftMotor;
  AnalogInput potentiometer; //mostly used for actuators

  //Constructor for initializiation
  public LiftSubsystem(){
    liftMotor = new Victor(RobotMap.liftMotor);
    potentiometer = new AnalogInput(RobotMap.potChannel);
  }

  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  //If the voltage of the potentiometer is below the maximum limit, raise the lift.
  public void raiseLift(){
    if(potentiometer.getVoltage() < RobotMap.topPotVoltage)
      liftMotor.set(.5);
  }

  //If the voltage of the potentiometer is above the minimum limit, lower the lift.
  public void lowerLift(){
    if(potentiometer.getVoltage() > RobotMap.bottomPotVoltage)
      liftMotor.set(-.5);
  }

  public void stopLift(){
    liftMotor.set(0);
  }

  public double getLiftSpeed(){
    return liftMotor.getSpeed();
  }

  //Because LiftSubsystem doesn't have a specific command that needs to always be scheduled to run (like driving),
  //it doesn't have a default command.
  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}
