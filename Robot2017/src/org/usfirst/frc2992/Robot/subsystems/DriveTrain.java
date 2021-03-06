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
import org.usfirst.frc2992.Robot.Robot;
import org.usfirst.frc2992.Robot.Robot.RobotState;
import org.usfirst.frc2992.Robot.RobotMap;
import org.usfirst.frc2992.Robot.commands.*;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.command.Subsystem;


/**
 *
 */
public class DriveTrain extends Subsystem {

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
	private final mhRobotDrive drivelib = Robot.drivelib;
    private final Encoder leftDriveEncoder = RobotMap.driveTrainLeftDriveEncoder;
    private final Encoder rightDriveEncoder = RobotMap.driveTrainRightDriveEncoder; 
    public double constant = 0.05;
    public double motorOffset = 0;
   
    public final AHRS navx = RobotMap.navx;
    public final AnalogGyro gyro = RobotMap.gyro;

    
    public PIDController lDistPID, rDistPID;
    
    DrivePID lDistance, rDistance;

	double correctionConstant = .005;
    final double dkp = .03;
    final double dki = 0;
    final double dkd = .12;
    final double dkf = 1/72/200;
    
    double gkp = .03; 
    double gkd = .0;
    double gError = 0.0;
    
    double nKp = 1;
    double nKd = .15; // 0 on real bot
    double mKp = 1;
    double mKd = 1/72; // 0 on real bot
    double errorT = 0;
    double prevErrorT = 0;
    double errorM = 0;
    double prevErrorM = 0;
    double prevLE = 0;
    double prevRE = 0;
    
    RotatePID turn;
    RotatePID rTurn;
    
    public PIDController turnPID;
    PIDController rTurnPID;
    
    final double rkp = .03;
    final double rki = .01;
    final double rkd = .056;
    
    boolean isJoySwitched = true;
    


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
    	lDistPID = new PIDController(dkp, dki, dkd, dkf, leftDriveEncoder, lDistance);
    	lDistPID.setOutputRange(-0.5, 0.5);
    	lDistPID.setInputRange(-240.0, 240.0);
    	lDistPID.setPercentTolerance(1.0);
    	lDistPID.disable();

    	
    	rDistance = new DrivePID(RobotMap.rightmotors);
    	rDistPID = new PIDController(dkp, dki, dkd, dkf, rightDriveEncoder, rDistance);
    	rDistPID.setOutputRange(-0.5, 0.5);
    	rDistPID.setInputRange(-240.0, 240.0);
    	rDistPID.setPercentTolerance(1.0);
    	rDistPID.disable();

		turn = new RotatePID(this.drivelib.leftDriveMotors, this.drivelib.rightDriveMotors);
    	
    	if(Robot.state == RobotState.Test){
    		gkp = .05; // for drive straight gyro correction
    		
        	turnPID = new PIDController(rkp, rki, rkd, gyro, turn);//change to navx for real robot
        	turnPID.setOutputRange(-0.35, 0.35);
        	turnPID.setInputRange(-180.0, 180.0);
        	turnPID.setContinuous();
        	turnPID.setAbsoluteTolerance(3);
        	turnPID.disable();
        	
    	} else if(Robot.state == RobotState.Robot){
    		
        	turnPID = new PIDController(rkp, rki, rkd, navx, turn);//change to navx for real robot
        	turnPID.setOutputRange(-0.35, 0.35);
        	turnPID.setInputRange(-180.0, 180.0);
        	turnPID.setContinuous();
        	turnPID.setAbsoluteTolerance(3);
        	turnPID.disable();
        	
    	}
    }

    	
    	
    
    
    public void tankDrive(mhJoystick leftjoy, mhJoystick rightjoy){
    	lDistPID.disable();
    	rDistPID.disable();
    	turnPID.disable();
    	//drivelib.tankDrive(-leftjoy.smoothGetY(), -rightjoy.smoothGetY());
    	
    	// Modified to use left joystick trigger to run in reverse.  Forward is running with the climber side first, backwards for gear delivery
    	boolean direction = leftjoy.getRawButton(1);  // Press left trigger to drive in reverse
    	if (direction) {
        	newDrive((-leftjoy.smoothGetY()- -rightjoy.smoothGetY())/2, (-leftjoy.smoothGetY() + -rightjoy.smoothGetY())/2);    		
    	} else {
        	newDrive((rightjoy.smoothGetY()- leftjoy.smoothGetY())/2, (rightjoy.smoothGetY() + leftjoy.smoothGetY())/2);
    	}
    	
    }
    
    public void tankDriveReverse(mhJoystick leftjoy, mhJoystick rightjoy){
    	drivelib.tankDriveRev(CurrentCheck(-leftjoy.smoothGetY()), CurrentCheck(-rightjoy.smoothGetY()));
    }
    
    public void SmartDriveDist(double distance){
    	turnPID.disable();
    	//drivelib.smartDrive(distance, 0, lDistPID, rDistPID);
    	lDistPID.reset();
    	rDistPID.reset();
    	lDistPID.setSetpoint(distance);
    	rDistPID.setSetpoint(distance);
    	lDistPID.enable();
    	rDistPID.enable();
    }
    
    public void SmartDriveGyro(double heading, double power){
    	power = Math.abs(power);
    	double gyroError = calcGyroError(heading);
    	double pGyro = gError - gyroError;
    	System.out.println("GyroError:  " + gyroError);
    	lDistPID.setOutputRange(-power - gkp*gyroError + gkd *pGyro, power - gkp*gyroError + gkd *pGyro);
    	rDistPID.setOutputRange(-power + gkp*gyroError - gkd *pGyro, power + gkp*gyroError - gkd *pGyro);
    	System.out.println("Left power:  " + (power-gkp*gyroError - gkd *pGyro));
    	System.out.println("Right power:  " + (power + gkp*gyroError + gkd *pGyro));
    	gError = gyroError;
    }
    
    public void SmartDriveGyroFast(double heading, double power){
    	double gkpFast = .09;
    	power = Math.abs(power);
    	double gyroError = calcGyroError(heading);
    	System.out.println("GyroError:  " + gyroError);
    	lDistPID.setOutputRange(-power - gkpFast*gyroError, power - gkpFast*gyroError);
    	rDistPID.setOutputRange(-power + gkpFast*gyroError, power + gkpFast*gyroError);
    	System.out.println("Left power:  " + (power-gkpFast*gyroError));
    	System.out.println("Right power:  " + (power + gkpFast*gyroError));
    }
    
    public void SmartDriveRot(double degrees){
    	lDistPID.disable();
    	rDistPID.disable();
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
    	double error = 0;
    	if(Robot.state == RobotState.Robot){
    		error = scaleAngle(navx.getYaw() - heading);
    	}
    	if(Robot.state == RobotState.Test){
    		error = scaleAngle(gyro.getAngle() - heading);
    	}
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
    	turnPID.disable();
    	//drivelib.arcadeDrive(-rightJoy.smoothGetY(), leftJoy.smoothGetX());

    	newDrive(leftJoy.smoothGetX(), -rightJoy.smoothGetY());
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
	
    
    public void setJoySwitched(boolean current){
    	isJoySwitched = current;
    }
    
    public boolean isJoySwitched(){
    	return isJoySwitched;
    }
  
    /*
     * 
     * new WIP arcade drive. currently a lot smoother than default WPILIB
     * also allows for much easier correction of both arcade and tank drive at the same time
     */
    public void newDrive(double horizontal, double vertical){
    	
    	calcVector(horizontal, vertical);
    	errorT = horizontal;
    	errorM = vertical;

    	double counter = Robot.currentlimit.getCount();
    	double correction = 0/*Math.pow(counter,2) * correctionConstant*/;
    	// @TODO tweak implementation of the counter. The counter works fine, change to a multiplier
    	
    	
    	
    	double left = Math.max(Math.min(mKp*errorM + mKd * (RobotMap.driveTrainLeftDriveEncoder.getRate()-prevLE) + nKp*errorT + nKd * (errorT-prevErrorT), 1), -1);
    	double right = Math.max(Math.min(mKp*errorM + mKd * (RobotMap.driveTrainRightDriveEncoder.getRate()-prevRE) - nKp*errorT - nKd * (errorT-prevErrorT), 1), -1);

    	double leftSign = left/Math.abs(left);
    	double rightSign = right/Math.abs(right);
    	drivelib.tankDrive(left-correction*leftSign, right-correction*rightSign);
    	prevErrorT = errorT;
    	prevErrorM = errorM;
    	prevLE = RobotMap.driveTrainLeftDriveEncoder.getRate();
    	prevRE = RobotMap.driveTrainRightDriveEncoder.getRate();
    }
    
    
    // do we need? doesn't seem like it
    private double[] calcVector(double horizontal, double vertical){
    	double vector[] = new double[2];
    	
    	vector[0] = 2* Math.atan2(horizontal, vertical)/Math.PI;
    	vector[1] = (vertical/Math.abs(vertical))*Math.sqrt(Math.pow(horizontal, 2) + Math.pow(vertical, 2));
    	if(Math.abs(vector[1]) > 1.0){
    		vector[1]/=Math.abs(vector[1]);
    	}
    	return vector;
    }
}
