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
	
	
	public static double encoderDistPerPulseLow = (6* 3.14 * 24) / (128 * 60 * 3);// diameter[6] * PI[3.14]  / PPR[128] / Gear Box constant[3] / Gear Ratio[60:24] -- inches
	public static double lowGearMaxSpeed = 72; // inches per second
	public static double highGearMaxSpeed = 192; // inches per second
	
	//drive train
	public static double distanceP = 0.03;
	public static double distanceI = 0.00;
	public static double distanceD = 0.12;
	public static double distanceInputRange = 240; // inches
	public static double distanceOutputRange = .5; // power
	
	public static double rotateP = 0.03;
	public static double rotateI = 0.00;
	public static double rotateD = 0.056;
	public static double rotateInputRange = 180; // degrees
	public static double rotateOutputRange = .35; // power
	
	public static double gyroP = 0.03;
	public static double gyroI = 0.00;
	public static double gyroD = 0.00;
	
	//Intake
	public static double feedConstant = 1;
	public static double FeedInSpeed = 1; // power
	public static double FeedOutSpeed = -.5; // power
	public static double FeedOffSpeed = 0; // power
	
	//Gear System
	public static double leftServoIn = 170; // degrees
	public static double leftServoOut = 20; // degrees
	
	public static double rightServoIn = 0; // degrees
	public static double rightServoOut = 150; // degrees
	
	//Climber
	public static double climbUpSpeed = 1; // power
	public static double climbStopSpeed = 0; // power
	
	//Autonomous
	public static double baseLineDist = 93.3; // inches
	
	public static double leftRedAngle = 60; // degrees
	public static double leftRedDeltaZ; // inches
	public static double leftRedDeltaX; // inches
	
	public static double rightRedAngle = -60; // degrees
	public static double rightRedDeltaZ; // inches
	public static double rightRedDeltaX; // inches
	
	public static double leftBlueAngle = -60; // degrees
	public static double leftBlueDeltaZ; // inches
	public static double leftBlueDeltaX; // inches
	
	public static double rightBlueAngle = 60; // degrees
	public static double rightBlueDeltaZ; // inches
	public static double rightBlueDeltaX; // inches
	
	//Camera
	public static double kHeight = 240; // pixels
	public static double kWidth = 320; // pixels
	public static double kFPS = 30; // frames per second
	public static double fov = 68.5; // degrees
	
	//Vision
	public static double gtHeight = 5; // inches
	public static double gtWidth = 2; // inches
	public static double[] gtHue = {6,94}; 
	public static double[] gtSat = {0,42};
	public static double[] gtVal = {220, 255};
	
	public static double yStandHeight = 11; // inches
	public static double yStandWidth = 11; // inches
	public static double[] yStandHue = {0,60}; 
	public static double[] yStandSat = {23,255};
	public static double[] yStandVal = {108, 255};
	
	public static double ySitHeight = 2; // inches
	public static double ySitWidth = 11; // inches
	public static double[] ySitHue = {0,40}; 
	public static double[] ySitSat = {147,255};
	public static double[] ySitVal = {108, 255};
	
	public static Parameters greenTape = new Parameters(gtHeight, gtWidth, gtHue, gtSat, gtVal);
	public static Parameters yellowGearStanding = new Parameters(yStandHeight, yStandWidth, yStandHue, yStandSat, yStandVal);
	public static Parameters yellowGearSitting = new Parameters(ySitHeight, ySitWidth, ySitHue, ySitSat, ySitVal);
	
	//Motion Profiling
	public static String testPath = "//home//lvuser//path";
	
}
