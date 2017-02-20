package org.usfirst.frc2992.Robot;

import org.usfirst.frc2992.Robot.Vision.Parameters;

public class Constants {

	public Constants(){
		
		
	}
	
	//overall info
	public static double robotWidth = 24.5; // length between middle of left and right drive bases
	public static double robotLength = 23.5; // length between front and back wheels
	
	public static double fullWidth = 34.5; // width of full robot with bumpers
	public static double fullLength = 38; // length of full robot with bumpers
	
	
	public static double encoderDistPerPulse = (6* 3.14 * 24) / (128 * 60 * 3);// diameter[6] * PI[3.14]  / PPR[128] / Gear Box constant[3] / gear ratio[60:24]
	public static double lowGearMaxSpeed = 6; // fps
	public static double highGearMaxSpeed = 16; // fps
	
	//drive train
	public static double distanceP = 0.03;
	public static double distanceI = 0.00;
	public static double distanceD = 0.12;
	public static double distanceOutputRange = .5; 
	
	public static double rotateP = 0.03;
	public static double rotateI = 0.00;
	public static double rotateD = 0.056;
	public static double rotateOutputRange = .35;
	
	//Intake
	public static double feedConstant = 1;
	public static double FeedInSpeed = 1;
	public static double FeedOutSpeed = -.5;
	public static double FeedOffSpeed = 0;
	
	//Gear System
	public static double leftServoIn = 170;
	public static double leftServoOut = 20;
	
	public static double rightServoIn = 0;
	public static double rightServoOut = 150;
	
	public static double servoMax = 2.1;
	public static double servoDeadbandMax = 1.5;
	public static double servoCenter = 1.5;
	public static double servoDeadbandMin = 1.5;
	public static double servoMin = 0.9;
	
	
	//Climber
	public static double climbUpSpeed = 1;
	public static double climbStopSpeed = 0;
	
	//Autonomous
	public static double baseLineDist = 93.3;
	public static double angleBeforeVisCheck = -45;//degrees
	
	//Camera
	public static double kHeight = 480;
	public static double kWidth = 640;
	public static double fov = 68.5;
	
	//Vision
	public static double gtHeight = 5;
	public static double gtWidth = 2;
	public static double[] gtHue = {55,100}; 
	public static double[] gtSat = {18,105};
	public static double[] gtVal = {101, 166};
	
	public static double yStandHeight = 11;
	public static double yStandWidth = 11;
	public static double[] yStandHue = {0,60}; 
	public static double[] yStandSat = {100,255};
	public static double[] yStandVal = {108, 255};
	
	public static double ySitHeight = 2;
	public static double ySitWidth = 11;
	public static double[] ySitHue = {0,40}; 
	public static double[] ySitSat = {147,255};
	public static double[] ySitVal = {108, 255};
	
	public static Parameters greenTape = new Parameters(gtHeight, gtWidth, gtHue, gtSat, gtVal);
	public static Parameters yellowGearStanding = new Parameters(yStandHeight, yStandWidth, yStandHue, yStandSat, yStandVal);
	public static Parameters yellowGearSitting = new Parameters(ySitHeight, ySitWidth, ySitHue, ySitSat, ySitVal);
	
	//Motion Profiling
	public static String testPath = "//home//lvuser//path";
	
}
