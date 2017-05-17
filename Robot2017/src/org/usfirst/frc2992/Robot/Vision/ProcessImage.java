package org.usfirst.frc2992.Robot.Vision;

import org.opencv.core.Rect;
import org.usfirst.frc2992.Robot.Constants;
import org.usfirst.frc2992.Robot.Robot;
import org.usfirst.frc2992.Robot.Vision.Vision.Target;

public class ProcessImage {
	
	Rect left, right;
	State num = State.zero;
	Target target;
	double centerX, centerY;
	double[] coordinate = new double[2];
	double angle, distance;
	
	double kAngleView = Constants.fov;
	double tHeight = Constants.kHeight;
	double tWidth = Constants.kWidth;
	
	public enum State{
		zero, one, two;
	}
	public ProcessImage(){
		num = State.zero;
	}
	
	public ProcessImage(Rect img){
		left = img;
		num = State.one;
	}
	
	public ProcessImage(Rect left, Rect right){
		this.left = left;
		this.right = right;
		num = State.two;
	}
	
	public void Init(Rect img){
		left = img;
		num = State.one;
	}
	
	public void Init(Rect left, Rect right){
		this.left = left;
		this.right = right;
		num = State.two;
	}
	
	public void run(){
		target = Robot.vision.getTarget();
		CalcCenter();
		CalcAngle();
		CalcDistance();
	}
	

	public double[] getCenter(){
		return coordinate;
	}
	
	public double getHeading(){
		return angle;
	}
	
	public double getDistance(){
		return distance;
	}
	
	private void CalcCenter(){
		System.out.println("Calculating Center");
		switch(num){
		case one:
			centerX = left.x + (left.width / 2);
			centerY = left.y + (left.height / 2);
			break;
		case two:
			centerX = (left.x + right.x)/2 + (left.width + right.width)/2/2;//take averages, then divide by two to get the center
			centerY = (left.y + right.y)/2 + (left.height + right.height)/2/2;
			break;
		default: 
			centerX = 0;
			centerY = 0;
			break;
		}
		coordinate[0] = centerX;
		coordinate[1] = centerY;
		System.out.println("X: " + coordinate[0] + " Y: " + coordinate[1]);
	}
	
	/**
	 * @TODO needs tweaking
	 */
	private void CalcAngle(){
		System.out.println("Calculating Angle");
		angle = (centerX - tWidth/2) / (tWidth/2); // assumes pixel width is constant -- need to tweak
		/*
		switch(num){
		case one:
			angle = (2* left.height * Math.tan(kAngleView)) / tHeight;
			break;
		case two:
			angle = (2* (left.height + right.height)/2 * Math.tan(kAngleView)) / tHeight;
			break;
		default:
			break;
		}
		*/
		System.out.println("Angle: " + angle);
	}
	
	/**
	 * @TODO needs tweaking, definitely wrong formula currently- was just used for testing purposes
	 */
	private void CalcDistance() {
		System.out.println("Calculating Distance");
		distance = 0;
		switch(target){
		case GreenTape: distance = (Constants.greenTape.getHeight() * tHeight)/(2*(left.height/* + right.height*/)/2*Math.tan(kAngleView));
			break;
		case YellowSittingGear: distance = (Constants.yellowGearSitting.getHeight() * tHeight)/(2*(left.height)*Math.tan(kAngleView));
			break;
		case YellowStandingGear: distance = (Constants.yellowGearStanding.getHeight() * tHeight)/(2*(left.height)*Math.tan(kAngleView));
			break;
		default:
			break;
	}
		System.out.println("Distance: " + distance);
		
	}
}
