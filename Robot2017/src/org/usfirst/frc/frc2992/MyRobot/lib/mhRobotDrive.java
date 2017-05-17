package org.usfirst.frc.frc2992.MyRobot.lib;

import java.util.ArrayList;

import edu.wpi.first.wpilibj.MotorSafety;
import edu.wpi.first.wpilibj.MotorSafetyHelper;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Timer;

/**
 * @author Jackson, team 2992
 * 
 * Last Updated as of 2/16/2017
 * 
 * This is a collection of drive code made by team 2992 over the years.
 * 
 * This was designed as an ease of use file to allow better control with more than 2 motors on each side of the drive train(we had 3 on each at the year of conception).
 * Initially crafted in 2014, the code was organized in 2016 and further work began. 
 * Currently resides solely in our robot code, but feel free to reuse, subject to WPILib copyrights
 * This allows for easier or stronger implementation of specific functions, even if the functions can be achieved via robotDrive in WPILib libraries
 *
 * Major features include:
 * -ability to resize commands to the size of your drive train as well as seamlessly function with more than 4 drive motors
 * -built in troubleshooting for more advanced commands. outputs with System.out.println()
 * -competition ready
 */
public class mhRobotDrive implements MotorSafety{

	//array to hold the motor controllers
	public ArrayList<SpeedController> leftDriveMotors;
	public ArrayList<SpeedController> rightDriveMotors;
	
	//Safety for robot.
	protected MotorSafetyHelper safetyHelper;
	
	double kWait = 20;//milliseconds for when delays are needed
	
	public mhRobotDrive(ArrayList<SpeedController> leftMotors, ArrayList<SpeedController> rightMotors){
		leftDriveMotors = leftMotors;
		rightDriveMotors = rightMotors;
	
		
		
		
	    setupMotorSafety();		
		
    }
	
	public void setMotors(ArrayList<SpeedController> leftMotors, ArrayList<SpeedController> rightMotors){
		leftDriveMotors = leftMotors;
		rightDriveMotors = rightMotors;
	}
	
	public ArrayList<SpeedController> getLeftMotors(){
		return leftDriveMotors;
	}
	
	public ArrayList<SpeedController> getRightMotors(){
		return rightDriveMotors;
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
       setSpeed(leftDriveMotors, leftspeed);   // Left motors are reversed
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
   
   public void arcadeDrive(double moveValue, double rotateValue){
	   
	   double leftMotorSpeed;
	   double rightMotorSpeed;

	    if (moveValue > 0.0) {
	      if (rotateValue > 0.0) {
	        leftMotorSpeed = Math.max(moveValue, rotateValue);
	        rightMotorSpeed = moveValue - rotateValue;
	      } else {
	        leftMotorSpeed = moveValue + rotateValue;
	        rightMotorSpeed = Math.max(moveValue, -rotateValue);
	      }
	    } else {
	      if (rotateValue > 0.0) {
	        leftMotorSpeed = moveValue + rotateValue;
	        rightMotorSpeed = -Math.max(-moveValue, rotateValue);
	      } else {
	        leftMotorSpeed = -Math.max(-moveValue, -rotateValue);
	        rightMotorSpeed = moveValue - rotateValue;
	      }
	    }

	    tankDrive(leftMotorSpeed, rightMotorSpeed);
   }
   
   /**
    * used to directly set the motor powers. Generally used for autonomous with no PID.
    * 
    * @param leftspeed
    * @param rightspeed
    * @param angleOffset -- offset from a set angle
    * @param kP -- proportional constant
    */
   
   public void autoDrive(double leftspeed, double rightspeed, double angleOffset, double kP){
	   
	   setSpeed(leftDriveMotors, -leftspeed + kP*angleOffset);
	   setSpeed(rightDriveMotors, rightspeed + kP*angleOffset);
	   
	   if (!safetyHelper.isSafetyEnabled()) {
           safetyHelper.setSafetyEnabled(true);
       }

       //make sure .feed() remains. This is mandatory for safety to work
       safetyHelper.feed();
   }
   
   /**
    * basic motion profiling function. values must be determined beforehand
    * defaulting to 20ms delay
    * @param speeds -- array of speed values to set
    */
   public void tuneDrive(double[] speeds){
	   setSmartSpeed(leftDriveMotors, speeds, kWait);
	   setSmartSpeed(rightDriveMotors, speeds, kWait);
	   
	   if (!safetyHelper.isSafetyEnabled()) {
           safetyHelper.setSafetyEnabled(true);
       }
	   safetyHelper.feed();
   }
   
   /**
    * 
    * @param speeds -- array of speed values to set
    * @param wait -- custom delay between values being set in milliseconds
    */
   public void tuneDrive(double[] speeds, double wait){
	   setSmartSpeed(leftDriveMotors, speeds, wait);
	   setSmartSpeed(rightDriveMotors, speeds, wait);
	   
	   if (!safetyHelper.isSafetyEnabled()) {
           safetyHelper.setSafetyEnabled(true);
       }
	   safetyHelper.feed();
   }
   
   
   /**
    * method used for rotation-based driving. Will be used sparingly
    * designed for Tank Drive robots. 
    * note -- untested
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
    * note -- untested
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
    * TODO -- separate into separate distance and rotating methods
    * 
    * @param Distance -- amount of distance to move forward
    * @param Degrees -- amount of radians to rotate
    * @param lDistPID -- PID Controller for left side motors
    * @param rDistPID -- PID Controller for right side motors
    */
   // this is not designed to currently work with both driving and turning. please see motion profile code for the smoother and stronger control
   // this is for simple and straightforward single movements only. 
   //
   public void smartDrive(double Distance, double Degrees, PIDController lDistPID, PIDController rDistPID){
   	
		
   	//drive straight. may need to adjust direction based on robot motor positioning and control
   	if(Math.abs(Distance) > 0.0 && Math.abs(Degrees) <= 0.01){
   		System.out.println("Driving forward");
   		lDistPID.reset();
   		rDistPID.reset();
   		lDistPID.setSetpoint(Distance); // Left side is reversed
   		rDistPID.setSetpoint(Distance);
   		System.out.println("PID target set to: " + Distance);
   		lDistPID.enable();
   		rDistPID.enable();
   		
   	//turn to a specified degree. designed for a single turn
   	}else if(Math.abs(Degrees) > 0 && Math.abs(Distance) <= 0.01){
   		System.out.println("Turning");
   		lDistPID.reset();
   		rDistPID.reset();
   		lDistPID.setSetpoint(Degrees); // Left side is reversed
   		rDistPID.setSetpoint(Degrees);
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
   
   public void smartDriveRotation(double angle, PIDController turnPID){
	   if(Math.abs(angle) >=.001){
		   System.out.println("Turning");
		   turnPID.reset();
		   turnPID.setSetpoint(angle);
		   System.out.println("PID rotation set to: " + angle);
		   turnPID.enable();
	   } else {
		   System.out.println("Stopping. Angle too small.");
		   turnPID.disable();
		   stopMotor();
	   }
   }
   
   

   /**
    * in progress Smart Motor Control.
    * Internal utility method to allow for tighter motion control of motors.
    * No PID implementation as of yet, but allows for easier implementation
    * 
    * @param speeds -- array of motor speeds. allows for precise and modifiable speed control
    * @param delay -- amount of time between cycles in milliseconds
    */
   
   private void setSmartSpeed(ArrayList<SpeedController> motors, double[] speeds, double delay){
	   Timer timer = new Timer();
	   timer.start();
	   System.out.println("Motor Speeds being set");
	   for (int i=0; i <= speeds.length; i++){
		   double old = timer.get()*1000;
		   setSpeed(motors, speeds[i]);
		   while(old >= timer.get()*1000 - delay){
			  //wait
		   }
		   System.out.println("Motor Value " + i + " was set to: " + speeds[i]);
	   }
	   System.out.println("Finished");
   }
   
   
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