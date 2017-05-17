package org.usfirst.frc2992.Robot;

import org.usfirst.frc2992.Robot.Vision.Parameters;

public class Constants {

	public Constants(){
		
		
	}
	
	//overall info
	public static final double robotWidth = 24.5; // length between middle of left and right drive bases
	public static final double robotLength = 23.5; // length between front and back wheels
	
	public static final double fullWidth = 34.5; // width of full robot with bumpers
	public static final double fullLength = 38; // length of full robot with bumpers
	
	
	public static final double encoderDistPerPulse = (6* 3.14 * 24) / (128 * 60 * 3);// diameter[6] * PI[3.14]  / PPR[128] / Gear Box constant[3] / Gear Ratio[60:24] -- inches
	public static final double lowGearMaxSpeed = 72; // inches per second
	public static final double highGearMaxSpeed = 192; // inches per second
	
	//drive train
	public static final double distanceP = 0.03;
	public static final double distanceI = 0.00;
	public static final double distanceD = 0.12;
	public static final double distanceF = 1/72/200;
	public static final double distanceInputRange = 240; // inches
	public static final double distanceDefaultOutputRange = .5; // power
	public static final double distancePercentTolerance = 1; // percent
	
	public static final double rotateP = 0.03;
	public static final double rotateI = 0.00;
	public static final double rotateD = 0.056;
	public static final double rotateInputRange = 180; // degrees
	public static final double rotateOutputRange = .35; // power
	
	public static final double gyroP = 0.03;
	public static final double gyroI = 0.00;
	public static final double gyroD = 0.00;
	
	//Intake
	public static final double feedConstant = 1;
	public static final double FeedSpeed = -1; // power
	
	//Gear System
	public static final double leftServoIn = 170; // degrees
	public static final double leftServoOut = 20; // degrees
	
	public static final double rightServoIn = 0; // degrees
	public static final double rightServoOut = 150; // degrees
	
	//Climber
	public static final double climbUpSpeed = 1; // power
	public static final double climbStopSpeed = 0; // power
	
	public static final double climbMinimumSpeed = 0.4;
	public static final double climbDefaultSpeed = 0.5;
	public static final double climbDeadzone = 0.45;
	
	//Autonomous
	public static final double driveForwardMinSpeed = 0.30;
	public static final double driveForwardMaxSpeed = 0.60;
	public static final double driveForwardRampRate = 0.01;

	public static final double driveForwardFastMinSpeed = 0.3;
	public static final double driveForwardFastMaxSpeed = 0.85;
	public static final double driveForwardFastRampRate = 0.025;
	
	public static final double driveTurnMinSpeed = 0.1;
	public static final double driveTurnMaxSpeed = 0.7;
	public static final double driveTurnRampRate = 0.005;
	
	//Camera
	public static final int kHeight = 210; // pixels
	public static final int kWidth = 280; // pixels
	public static final int kFPS = 20; // frames per second
	public static final double fov = 68.5; // degrees
	
	//Vision
	public static final double gtHeight = 5; // inches
	public static final double gtWidth = 2; // inches
	public static final double[] gtHue = {6,94}; 
	public static final double[] gtSat = {0,42};
	public static final double[] gtVal = {220, 255};
	
	public static final double yStandHeight = 11; // inches
	public static final double yStandWidth = 11; // inches
	public static final double[] yStandHue = {0,60}; 
	public static final double[] yStandSat = {23,255};
	public static final double[] yStandVal = {108, 255};
	
	public static final double ySitHeight = 2; // inches
	public static final double ySitWidth = 11; // inches
	public static final double[] ySitHue = {0,40}; 
	public static final double[] ySitSat = {147,255};
	public static final double[] ySitVal = {108, 255};
	
	public static final Parameters greenTape = new Parameters(gtHeight, gtWidth, gtHue, gtSat, gtVal);
	public static final Parameters yellowGearStanding = new Parameters(yStandHeight, yStandWidth, yStandHue, yStandSat, yStandVal);
	public static final Parameters yellowGearSitting = new Parameters(ySitHeight, ySitWidth, ySitHue, ySitSat, ySitVal);
	
	//Motion Profiling
	public static final String testPath = "//home//lvuser//path";
	
}
