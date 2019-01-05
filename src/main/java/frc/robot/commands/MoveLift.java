/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.TimedCommand;
import frc.robot.Robot;

public class MoveLift extends TimedCommand {

  //The boolean decides whether the lift will be raising or lowering
  boolean isRaising;

  //This command will end based on time (seconds)
  public MoveLift(double timeout, boolean isRaising) {
    super(timeout);
    this.isRaising = isRaising;
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(Robot.m_liftSubsystem);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    //If the boolean is true, raise the lift; else, lower the lift.
    if(isRaising){
      Robot.m_liftSubsystem.raiseLift();
    } else{
      Robot.m_liftSubsystem.lowerLift();
    }
  }

  // Called once after timeout
  @Override
  protected void end() {
    //Ensure the lift will stop when the command ends
    Robot.m_liftSubsystem.stopLift();
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
