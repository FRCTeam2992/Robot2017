package org.usfirst.frc2992.Robot.subsystems;

import org.usfirst.frc2992.Robot.RobotMap;
import org.usfirst.frc2992.Robot.commands.*;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Shifter extends Subsystem{

	Solenoid shifter = RobotMap.driveTrainDriveShfitHL;
	shiftMode sMode;
	boolean isHigh = false;
	boolean isShifting = false;
	
	public Shifter(shiftMode s){
		sMode = s;
	}
	
	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
		setDefaultCommand(new ShiftDown());
	}
	
	public enum shiftMode{
		normal, smooth, aggressive;
	}
	
	public void LowGear(){
		shifter.set(true);
		isHigh = false;
	}
	
	public void HighGear(){
		shifter.set(false);
		isHigh = true;
	}

	public void setMode(shiftMode s){
		sMode = s;
	}
	
	public shiftMode getMode(){
		return sMode;
	}
	
	public boolean isHighGear(){
		return isHigh;
	}
	
	public void setShifting(boolean shift){
		isShifting = shift;
	}
	
	public boolean isShifting(){
		return isShifting;
	}
	
	public double calcSpeed(double left, double right){ // averages left and right speeds for auto-shifting
		double speed = 0;
		if(left*right >0){
			speed = (left + right) /2;
		} else {
			speed = 0;
		}
		return speed;
	}
}
