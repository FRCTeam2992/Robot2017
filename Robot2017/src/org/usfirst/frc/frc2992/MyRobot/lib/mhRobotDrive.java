package org.usfirst.frc.frc2992.MyRobot.lib;

import java.util.ArrayList;

import org.usfirst.frc2992.MyRobot.Constants;
import org.usfirst.frc2992.MyRobot.RobotMap;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.MotorSafety;
import edu.wpi.first.wpilibj.MotorSafetyHelper;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.SpeedController;

public class mhRobotDrive implements MotorSafety{

	//array to hold the motor controllers
	public ArrayList<SpeedController> leftDriveMotors;
	public ArrayList<SpeedController> rightDriveMotors;
	
	//Safety for robot.
	protected MotorSafetyHelper safetyHelper;
	
	
	public mhRobotDrive(ArrayList<SpeedController> leftMotors, ArrayList<SpeedController> rightMotors){
		leftDriveMotors = leftMotors;
		rightDriveMotors = rightMotors;
	
		
		
		
	    setupMotorSafety();		
		
    }
	
	
	
	/**
    *
    * Tank drive method. Takes in left and right tank drive joystick input
    * values and drives the left and right motors. Input values should be -1.0
    * (reverse) to 1.0 (forward).
    *
    * @param leftJoystickValue
    * @param rightJoystickValue
    */
   public void tankDrive(double leftJoystickValue, double rightJoystickValue){

       double leftspeed = leftJoystickValue;
       double rightspeed = rightJoystickValue;


       // Set the drive motors
       setSpeed(leftDriveMotors, -leftspeed);   // Left motors are reversed
       setSpeed(rightDriveMotors, rightspeed);
       
       // Smartdashboard update
       // SmartDashboard.putNumber("Left Motor Speed", leftspeed);
       // SmartDashboard.putNumber("Right Motor Speed", rightspeed);
       
       if (!safetyHelper.isSafetyEnabled()) {
           safetyHelper.setSafetyEnabled(true);
    	   
       }

       //make sure .feed() remains. This is mandatory for safety to work
       safetyHelper.feed();
       
   }
   /**
    * Reverse tank drive method. Used to completely switch the front of the robot drive.
    * 
    * @param leftJoystickValue
    * @param rightJoystickValue
    */
   public void tankDriveRev(double leftJoystickValue, double rightJoystickValue){
	   
	   tankDrive(-rightJoystickValue, -leftJoystickValue);
       
   }
   
   public void arcadeDrive(mhJoystick joystick){
	   double moveValue = joystick.smoothGetY();
	   double rotateValue = joystick.smoothGetX();
	   
	   double leftMotorSpeed;
	   double rightMotorSpeed;

	    if (moveValue > 0.0) {
	      if (rotateValue > 0.0) {
	        leftMotorSpeed = moveValue - rotateValue;
	        rightMotorSpeed = Math.max(moveValue, rotateValue);
	      } else {
	        leftMotorSpeed = Math.max(moveValue, -rotateValue);
	        rightMotorSpeed = moveValue + rotateValue;
	      }
	    } else {
	      if (rotateValue > 0.0) {
	        leftMotorSpeed = -Math.max(-moveValue, rotateValue);
	        rightMotorSpeed = moveValue + rotateValue;
	      } else {
	        leftMotorSpeed = moveValue - rotateValue;
	        rightMotorSpeed = -Math.max(-moveValue, -rotateValue);
	      }
	    }

	    tankDrive(leftMotorSpeed, rightMotorSpeed);
   }
   /**
    * used to directly set the motor powers. Generally used for autonomous with no PID.
    * 
    * @param leftspeed
    * @param rightspeed
    */
   
   public void autoDrive(double leftspeed, double rightspeed){
	   
	   setSpeed(leftDriveMotors, -leftspeed);
	   setSpeed(rightDriveMotors, rightspeed);
	   
	   if (!safetyHelper.isSafetyEnabled()) {
           safetyHelper.setSafetyEnabled(true);
       }

       //make sure .feed() remains. This is mandatory for safety to work
       safetyHelper.feed();
   }
   /*
   public void tuneDrive(double[] speeds){
	   setSmartSpeed(leftDriveMotors, speeds);
	   setSmartSpeed(rightDriveMotors, speeds);
	   
	   if (!safetyHelper.isSafetyEnabled()) {
           safetyHelper.setSafetyEnabled(true);
       }
	   safetyHelper.feed();
   }
   */
   /**
    * method used for smart rotation-based driving. Will be used sparingly
    * 
    * @param x -- Joystick to return twist from
    * @param side -- left or right. side is outside of rotation
    */
   public void twistDrive(mhJoystick x, String side){
	   double twist = x.getTwist();
	   double rotate = .1; // amount to multiply a side of motors by to create a center of rotation
	   
	   if (side == "left"){
		   setSpeed(leftDriveMotors, twist);
		   setSpeed(rightDriveMotors, rotate * twist); //tweaks needed, will change for every robot due to outside variables
	   } else if (side == "right"){
		   setSpeed(rightDriveMotors, twist);
		   setSpeed(leftDriveMotors, rotate * twist); //tweaks needed, will change for every robot due to outside variables
	   } else {
		   stopMotor();
	   }
	   safetyHelper.feed();
   }
   
   /**
    * used for 4 wheel swerve drive
    * 
    * @param joy -- joystick to control with
    * @param LF -- left front drive motor
    * @param LR -- left rear drive motor
    * @param RF -- right front drive motor
    * @param RR -- right rear drive motor
    * @param tLF -- left front swerve motor, uses PID
    * @param tLR -- left rear swerve motor, uses PID
    * @param tRF -- right front swerve motor, uses PID
    * @param tRR -- right rear swerve motor, uses PID
    */
   public void swerveDrive(mhJoystick joy, SpeedController LF, SpeedController LR, SpeedController RF, SpeedController RR, 
		   PIDController tLF, PIDController tLR, PIDController tRF, PIDController tRR){
	   
	   double x = joy.smoothGetX();
	   double y = joy.smoothGetY();
	   double rotate = joy.getTwist();
	   double magnitude = Math.sqrt(x*x + y*y)/Math.sqrt(2);// magnitude of joystick. divide by square root of 2 to scale to [-1,1]
	   double turn90 = 1; // scalar to convert x to the value for PID controller 90 degrees -- needs tweaking
	   
	   LF.set(magnitude);
	   LR.set(magnitude);
	   RF.set(magnitude);
	   RR.set(magnitude);
	   
	   tLF.enable();
	   tLR.enable();
	   tRF.enable();
	   tRR.enable();
	   
	   if(Math.abs(x) < .01 && Math.abs(y) < .01){// origin
		   tLF.setSetpoint(0);// 0 is forward
		   tLR.setSetpoint(0);
		   tRF.setSetpoint(0);
		   tRR.setSetpoint(0);
	   } else if(y >= 0){// Quadrants 1 & 2 & x-axis
		   tLF.setSetpoint(turn90 * (x + rotate));
		   tLR.setSetpoint(turn90 * (x + rotate));
		   tRF.setSetpoint(turn90 * (x + rotate));
		   tRR.setSetpoint(turn90 * (x + rotate));
	   }  else if(y == 0){// Quadrants 3 & 4
		   tLF.setSetpoint(-1 * turn90 * (x + rotate));
		   tLR.setSetpoint(-1 * turn90 * (x + rotate));
		   tRF.setSetpoint(-1 * turn90 * (x + rotate));
		   tRR.setSetpoint(-1 * turn90 * (x + rotate));
	   } 
	   safetyHelper.feed();
   }
   
   /**
    * PID control for static wheels
    * 
    * @param Distance -- amount of distance to move forward
    * @param Degrees -- amount of radians to rotate
    * @param lDistPID -- PID Controller for left side motors
    * @param rDistPID -- PID Controller for right side motors
    */
   public void smartDrive(double Distance, double Degrees, PIDController lDistPID, PIDController rDistPID){
   	
		double autoDist = Distance;
		double autoDegr = Degrees;
		
   	
   	if(Math.abs(Distance) > 0.0 && Math.abs(Degrees) <= 0.01){
   		System.out.println("Driving forward");
   		lDistPID.reset();
   		rDistPID.reset();
   		lDistPID.setSetpoint(-Distance); // Left side is reversed
   		rDistPID.setSetpoint(Distance);
   		System.out.println("PID target set to: " + Distance);
   		lDistPID.enable();
   		rDistPID.enable();
   		
   	}else if(Math.abs(Degrees) > 0 && Math.abs(Distance) <= 0.01){
   		System.out.println("Turning");
   		lDistPID.reset();
   		rDistPID.reset();
   		lDistPID.setSetpoint(-Degrees); // Left side is reversed
   		rDistPID.setSetpoint(-Degrees);
   		System.out.println("PID rotation set to: " + Degrees);
   		lDistPID.enable();
   		rDistPID.enable();
   		
   		
   	}else{
   		System.out.println("Stopping");
   		lDistPID.disable();
   		rDistPID.disable();
   		stopMotor();
   	}
   }
   

   /**
    * in progress Smart Motor Control.
    * 
    * @param speeds -- array of motor speeds. allows for precise and modifiable speed control
    * @param motors -- array of motors to set the speed of
    */
   /*
   private void setSmartSpeed(SpeedController[] motors, double[] speeds){
	   for (int i=0; i <= speeds.length; i++){
		   setSpeed(motors, speeds[i]);
		   //motors[].set(speeds[i]);
	   }
   }
   */
   
   /**
    *
    * Internal utility method to actually set the drive motors directly.
    * Private so should not be used outside this class file.
    *
    * @param motors -- an array of motors to set the speed of
    * @param speed -- speed to set the motors to
    *
    */
   
	private void setSpeed(ArrayList<SpeedController> Motors, double speed) {
		for (SpeedController Motor: Motors) {
	        Motor.set(speed);
		}
    }
	
	
	/*
	 * following methods are for motor safety. No need to touch
	 */
	
	public void setExpiration(double timeout) {
        safetyHelper.setExpiration(timeout);
    }

    public double getExpiration() {
        return safetyHelper.getExpiration();
    }

    public boolean isAlive() {
        return safetyHelper.isAlive();
    }

    public boolean isSafetyEnabled() {
        return safetyHelper.isSafetyEnabled();
    }

    public void setSafetyEnabled(boolean enabled) {
        safetyHelper.setSafetyEnabled(enabled);
    }
    
    private void setupMotorSafety() {
        safetyHelper = new MotorSafetyHelper(this);
        safetyHelper.setExpiration(0.1);
        //SafetyHelper.setSafetyEnabled(true);
    }
	
    
    /*
     * turns off all motors.
     */
	@Override
	public void stopMotor() {
		// TODO Auto-generated method stub
		for (SpeedController rMotor: rightDriveMotors) {
	        rMotor.set(0);
		}
		for (SpeedController lMotor: leftDriveMotors){
			lMotor.set(0);
		}
		safetyHelper.setSafetyEnabled(false);
	}

	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return null;
	}

	
}