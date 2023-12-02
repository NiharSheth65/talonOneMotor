// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.math.filter.SlewRateLimiter;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveSubsystem;

public class DefaultDriveCommand extends CommandBase {

  private DriveSubsystem DRIVE_SUBSYSTEM;   
  private Joystick joystick; 

  double motorDrive = 0; 
  double motorTurn = 0; 

  SlewRateLimiter drive_Limiter = new SlewRateLimiter(1); 

  /** Creates a new DefaultDriveCommand. */
  public DefaultDriveCommand(DriveSubsystem drive, Joystick joy) {

    // Use addRequirements() here to declare subsystem dependencies.
    this.DRIVE_SUBSYSTEM= drive; 
    this.joystick = joy; 

    addRequirements(DRIVE_SUBSYSTEM);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {  
      motorDrive = 0; 
      motorTurn = 0; 
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {


    motorDrive = -joystick.getRawAxis(1);
    motorTurn = -joystick.getRawAxis(4);


    if(Math.abs(joystick.getRawAxis(1)) < 0.1){
      motorDrive = 0; 
    }
    else{
      motorDrive = joystick.getRawAxis(1);  
    }

    // turn 
    if(Math.abs(joystick.getRawAxis(4)) < 0.1){
      motorTurn = 0; 
    }

    else{
      motorTurn = joystick.getRawAxis(4); 
    }

    DRIVE_SUBSYSTEM.setMotor(drive_Limiter.calculate(motorDrive), motorTurn);

  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    DRIVE_SUBSYSTEM.stop();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
