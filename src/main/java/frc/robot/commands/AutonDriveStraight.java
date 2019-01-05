/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class AutonDriveStraight extends Command {

  double distance;

  //Constructor of the command with a parameter of a distance
  public AutonDriveStraight(double distance) {
    this.distance = distance;
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(Robot.m_driveSubsystem);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    //Because the method only needs to be called once, it is called once in initialize()
    //The robot drive straight to the specified button
    Robot.m_driveSubsystem.driveStraight(distance);
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    //Print line to ensure that the command actually ran
    System.out.println("Drive Straight is Running");
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    //Because the method is already called, the command is finished at initialize()
    return true;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    //Ensuring the robot stops after the command ends
    Robot.m_driveSubsystem.stopDrive();
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
