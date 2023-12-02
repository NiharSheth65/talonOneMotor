// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class DriveSubsystem extends SubsystemBase {

  private TalonSRX leftFront;
  private TalonSRX leftBack;

  private TalonSRX rightFront;
  private TalonSRX rightBack;

  /** Creates a new DriveSubsystem. */
  public DriveSubsystem() {

    leftFront = new TalonSRX(0);
    leftBack = new TalonSRX(1);
    rightFront = new TalonSRX(2);
    rightBack= new TalonSRX(3);

    rightFront.setInverted(true);
    rightBack.setInverted(true);
    leftFront.setInverted(false);
    leftBack.setInverted(false);

    leftBack.follow(leftFront);
    rightBack.follow(rightBack);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void setMotor(double drive, double turn){
    rightFront.set(ControlMode.PercentOutput, drive - turn);
    leftFront.set(ControlMode.PercentOutput, drive + turn);
  }

  public void stop(){
    rightFront.set(ControlMode.PercentOutput, 0);
    leftFront.set(ControlMode.PercentOutput, 0);
  }

}
