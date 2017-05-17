package org.usfirst.frc2992.Robot;

import java.util.ArrayList;
import java.util.TimerTask;

import org.usfirst.frc.frc2992.MyRobot.MotionProfiling.*;
import org.usfirst.frc2992.Robot.subsystems.DrivePID;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.SpeedController;

public class FollowPath extends TimerTask implements FollowerOutput {
	P_LoopFollower plf;
	ArrayList<SpeedController> lMotor;
	ArrayList<SpeedController> rMotor;
	PIDController lPid, rPid, rotPid;
	DrivePID leftOutput, rightOutput;
	double leftP, rightP, leftD, rightD, heading, angleOffset;
	RobotSegmentGroup rsg;
	public Encoder leftEnc,rightEnc;
	final double kPR = .1;
	double kPD;
	
	Status state;
	
	public enum Status{
		Robot, Test;
	}

	public FollowPath(RobotSegmentGroup rsg){
		this.rsg = rsg;
		plf = new P_LoopFollower(rsg, this);
		leftEnc = RobotMap.driveTrainLeftDriveEncoder;
		rightEnc = RobotMap.driveTrainRightDriveEncoder;
	}
	
	public void PID_LoopInit(ArrayList<SpeedController> lMotor, ArrayList<SpeedController> rMotor){
		state = Status.Robot;
		this.lMotor = lMotor;
		this.rMotor = rMotor;
		

		leftOutput = new DrivePID(lMotor);
		rightOutput = new DrivePID(rMotor);
		
		kPD = plf.kP_distance = .1;
		
		
		lPid = new PIDController(kPD, 0.0,0.0, leftP, leftEnc, leftOutput);
		lPid.setOutputRange(-1, 1);
		lPid.setInputRange(-10, 10);
		leftEnc.setPIDSourceType(PIDSourceType.kRate);
		
		rPid = new PIDController(kPD, 0.0, 0.0, rightP, rightEnc, rightOutput);
		rPid.setInputRange(-10, 10);
		rPid.setOutputRange(-1, 1);
		rightEnc.setPIDSourceType(PIDSourceType.kRate);
		
		angleOffset = 0;
		
		rPid.enable();
		lPid.enable();
		
	}
	
	public void PID_LoopInit(){
		state = Status.Test;
		
		
		
		
	}
	public void enable(){
		plf.enable();
	}
	
	public synchronized void run(){
		if(plf.isRunning()){
		if(state == Status.Robot){
			angleOffset = rsg.robot.s.get(plf.getCycle()).h - RobotMap.navx.getAngle();
			
			lPid.setSetpoint(leftP - kPR * angleOffset);
			rPid.setSetpoint(rightP + kPR * angleOffset);
		
		}
		if(state == Status.Test){
			heading = rsg.robot.s.get(plf.getCycle()).h;
			leftD = rsg.left.s.get(plf.getCycle()).posit;
			rightD = rsg.right.s.get(plf.getCycle()).posit;
			leftP = rsg.left.s.get(plf.getCycle()).vel;
			rightP = rsg.right.s.get(plf.getCycle()).vel;
			
		}
		
		System.out.println("Left RAW Power: " + rsg.left.s.get(plf.getCycle()).vel);
		System.out.println("Right RAW Power: " + rsg.right.s.get(plf.getCycle()).vel);
		System.out.println("left power: " + leftP);
		System.out.println("right power: " + rightP);
		}
	}

	@Override
	public void setLeftPower(double p) {
		// TODO Auto-generated method stub
		leftP = p;
		
	}

	@Override
	public void setRightPower(double p) {
		// TODO Auto-generated method stub
		rightP = p;
		
	}

	@Override
	public double getLeftDistance() {
		// TODO Auto-generated method stub
		return leftD;
	}

	@Override
	public double getRightDistance() {
		// TODO Auto-generated method stub
		return rightD;
	}

	@Override
	public double getHeading() {
		// TODO Auto-generated method stub
		return heading;
	}

	@Override
	public void resetHeading() {
		// TODO Auto-generated method stub
		heading = 0;
		
	}

	@Override
	public void resetDistances() {
		// TODO Auto-generated method stub
		leftD = 0;
		rightD = 0;
	}
	
}
