// RobotBuilder Version: 2.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.


package org.usfirst.frc2992.MyRobot.subsystems;

import org.usfirst.frc.frc2992.MyRobot.lib.mhJoystick;
import org.usfirst.frc.frc2992.MyRobot.lib.mhRobotDrive;
import org.usfirst.frc2992.MyRobot.Robot;
import org.usfirst.frc2992.MyRobot.RobotMap;
import org.usfirst.frc2992.MyRobot.commands.*;
import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;


/**
 *
 */
public class DriveTrain extends Subsystem {

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
	private final mhRobotDrive drivelib = Robot.drivelib;
    private final SpeedController l2Motor = RobotMap.driveTrainL2Motor;
    private final SpeedController l1Motor = RobotMap.driveTrainL1Motor;
    private final SpeedController l3Motor = RobotMap.driveTrainL3Motor;
    private final SpeedController r1Motor = RobotMap.driveTrainR1Motor;
    private final SpeedController r2Motor = RobotMap.driveTrainR2Motor;
    private final SpeedController r3Motor = RobotMap.driveTrainR3Motor;
    private final Encoder leftDriveEncoder = RobotMap.driveTrainLeftDriveEncoder;
    private final Encoder rightDriveEncoder = RobotMap.driveTrainRightDriveEncoder;
    private final Solenoid driveShfitHL = RobotMap.driveTrainDriveShfitHL;
    public double constant = 0.05;
    public double motorOffset = 0;
    


    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS


    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
    	
    	setDefaultCommand(new DriveSticks());
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND


        // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND

        // Set the default command for a subsystem here.
        // setDefaultCommand(new MySpecialCommand());
    }
    
    public void tankDrive(mhJoystick leftjoy, mhJoystick rightjoy){
    	if(leftjoy.smoothGetY()>0 && rightjoy.smoothGetY()>0){
    		drivelib.tankDrive(leftjoy.smoothGetY()-motorOffset, rightjoy.smoothGetY()-motorOffset);
    	}else if(leftjoy.smoothGetY()<0 && rightjoy.smoothGetY()<0){
    		drivelib.tankDrive(leftjoy.smoothGetY()+motorOffset, rightjoy.smoothGetY()+motorOffset);
    	}else if(leftjoy.smoothGetY()<0 && rightjoy.smoothGetY()>0){
    		drivelib.tankDrive(leftjoy.smoothGetY()+motorOffset, rightjoy.smoothGetY()-motorOffset);
    	}else if(leftjoy.smoothGetY()>0 && rightjoy.smoothGetY()<0){
    		drivelib.tankDrive(leftjoy.smoothGetY()-motorOffset, rightjoy.smoothGetY()+motorOffset);
    	} else{ drivelib.tankDrive(leftjoy.smoothGetY(), rightjoy.smoothGetY());
    	}
    	
    }
    
    public void tankDriveReverse(mhJoystick leftjoy, mhJoystick rightjoy){
    	if(leftjoy.smoothGetY()>0 && rightjoy.smoothGetY()>0){
    		drivelib.tankDriveRev(leftjoy.smoothGetY()-motorOffset, rightjoy.smoothGetY()-motorOffset);
    	}else if(leftjoy.smoothGetY()<0 && rightjoy.smoothGetY()<0){
    		drivelib.tankDriveRev(leftjoy.smoothGetY()+motorOffset, rightjoy.smoothGetY()+motorOffset);
    	}else if(leftjoy.smoothGetY()<0 && rightjoy.smoothGetY()>0){
    		drivelib.tankDriveRev(leftjoy.smoothGetY()+motorOffset, rightjoy.smoothGetY()-motorOffset);
    	}else if(leftjoy.smoothGetY()>0 && rightjoy.smoothGetY()<0){
    		drivelib.tankDriveRev(leftjoy.smoothGetY()-motorOffset, rightjoy.smoothGetY()+motorOffset);
    	} else{ drivelib.tankDriveRev(leftjoy.smoothGetY(), rightjoy.smoothGetY());
    	}
    	
    	
    }
    
    public void tankDriveAuto(double left, double right){
    	drivelib.tankDrive(left, right);
    }
    
    public void motorStop(){
    	drivelib.stopMotor();
    }
    public void MotorOffset(){
    	motorOffset = Robot.currentlimit.Counter * constant;
    }
  
    
}

