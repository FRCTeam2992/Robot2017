// RobotBuilder Version: 2.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.


package org.usfirst.frc2992.Robot.subsystems;

import org.usfirst.frc.frc2992.MyRobot.lib.mhJoystick;
import org.usfirst.frc.frc2992.MyRobot.lib.mhRobotDrive;
import org.usfirst.frc2992.Robot.Constants;
import org.usfirst.frc2992.Robot.Robot;
import org.usfirst.frc2992.Robot.RobotMap;
import org.usfirst.frc2992.Robot.commands.*;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


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
    //private final AHRS Gyro;
    public double constant = 0.05;
    public double motorOffset = 0;
   
    public final AHRS navx = RobotMap.navx;

    
    PIDController lDistPID, rDistPID;
    
    DrivePID lDistance, rDistance;
    
    final double dkp = .03;
    final double dki = 0;
    final double dkd = .12;
    
    final double gkp = .03; 
    
    
    RotatePID turn;
    RotatePID rTurn;
    
    PIDController turnPID;
    PIDController rTurnPID;
    
    final double rkp = .03;
    final double rki = 0;
    final double rkd = .056;
    
    boolean isTank = true;
    


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
    
    public DriveTrain(){
    	
    	lDistance = new DrivePID(RobotMap.leftmotors);
    	lDistPID = new PIDController(dkp, dki, dkd, leftDriveEncoder, lDistance);
    	lDistPID.setOutputRange(-0.5, 0.5);
    	lDistPID.setInputRange(-240.0, 240.0);
    	lDistPID.setPercentTolerance(1.0);
    	lDistPID.disable();

    	
    	rDistance = new DrivePID(RobotMap.rightmotors);
    	rDistPID = new PIDController(dkp, dki, dkd, rightDriveEncoder, rDistance);
    	rDistPID.setOutputRange(-0.5, 0.5);
    	rDistPID.setInputRange(-240.0, 240.0);
    	rDistPID.setPercentTolerance(1.0);
    	rDistPID.disable();

    	//check before adding again. robot dislikes
    	turn = new RotatePID(this.drivelib.leftDriveMotors, this.drivelib.rightDriveMotors);
    	turnPID = new PIDController(rkp, rki, rkd, navx, turn);
    	turnPID.setOutputRange(-0.35, 0.35);
    	turnPID.setInputRange(-360.0, 360.0);
    	turnPID.setContinuous();
    	turnPID.setAbsoluteTolerance(3);
    	turnPID.disable();
    	
    }

    	
    	
    
    
    public void tankDrive(mhJoystick leftjoy, mhJoystick rightjoy){
    	drivelib.tankDrive(-leftjoy.smoothGetY(), -rightjoy.smoothGetY());
    	
    	
    }
    
    public void tankDriveReverse(mhJoystick leftjoy, mhJoystick rightjoy){
    	drivelib.tankDriveRev(CurrentCheck(-leftjoy.smoothGetY()), CurrentCheck(-rightjoy.smoothGetY()));
    }
    
    public void SmartDriveDist(double distance){
    	//drivelib.smartDrive(distance, 0, lDistPID, rDistPID);
    	lDistPID.setSetpoint(distance);
    	rDistPID.setSetpoint(distance);
    	lDistPID.enable();
    	rDistPID.enable();
    }
    
    public void SmartDriveGyro(double heading, double power){
    	power = Math.abs(power);
    	lDistPID.setOutputRange(-power - gkp*calcGyroError(heading), power - gkp*calcGyroError(heading));
    	rDistPID.setOutputRange(-power + gkp*calcGyroError(heading), power + gkp*calcGyroError(heading));
    }
    
    public void SmartDriveRot(double degrees){
    	
    	//drivelib.smartDrive(0, degrees, turnPID);
    	turnPID.setSetpoint(degrees);
    	turnPID.enable();
    }
    
    public void SmartDrivePower(double power) {
    	power = Math.abs(power);
    	lDistPID.setOutputRange(-power, power);
    	rDistPID.setOutputRange(-power, power);
    }
    
    private double calcGyroError(double heading){
    	double error = scaleAngle(navx.getAngle() - heading);
    	if(Math.abs(error) >= 1){
    		return error;
    	} else {
    		return 0;
    	}
    }
    private double scaleAngle(double angle){
    	while(angle > 180){
    		angle -= 360;
    	}
    	while(angle < -180){
    		angle += 360;
    	}
    	return angle;
    }
    
    
    public void ArcadeDrive(double move, double rotate){
    	drivelib.arcadeDrive(move, rotate);
    }
    
    public void ArcadeDrive(mhJoystick leftJoy, mhJoystick rightJoy){
    	lDistPID.disable();
    	rDistPID.disable();
    	drivelib.arcadeDrive(-rightJoy.smoothGetY(), leftJoy.smoothGetX());
    }

    public void tankDriveAuto(double left, double right){
    	drivelib.tankDrive(left, right);
    }
    
    public void motorStop(){
    	drivelib.stopMotor();
    	lDistPID.disable();
    	rDistPID.disable();
    	turnPID.disable();
    }
    public void MotorOffset(){
    	motorOffset = Robot.currentlimit.Counter * constant;
    }
    
    public boolean driveDone(String type){
    	if(type == "dist"){
    		if(lDistPID.onTarget() && rDistPID.onTarget()){
    			return true;
    		} else {
    			return false;
    		}
    	} else if(type == "rot"){
    		if(turnPID.onTarget()){
    			return true;
    		} else {
    			return false;
    		}
    	} else {
    		return false;
    	}
	}
    
    //used to update joystick values when high current has been detected
    private double CurrentCheck(double val){
    	if(val > 0){
    		val -= motorOffset;
    	}
    	if (val < 0){
    		val += motorOffset;
    	}
    	return val;
    }
	
    
    public void setTankDrive(boolean current){
    	isTank = current;
    }
    
    public boolean isTankDrive(){
    	return isTank;
    }
  
}

