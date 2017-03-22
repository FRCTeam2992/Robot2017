package org.usfirst.frc2992.Robot.commands;

import org.usfirst.frc2992.Robot.Robot;
import org.usfirst.frc2992.Robot.RobotMap;
import org.usfirst.frc2992.Robot.subsystems.Shifter.shiftMode;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.command.Command;

public class ShiftAuto extends Command{

	shiftMode sMode;
	Encoder leftEnc = RobotMap.driveTrainLeftDriveEncoder;
	Encoder rightEnc = RobotMap.driveTrainRightDriveEncoder;
	
	double normalSpeed = 64; // inches per second
	double aggressiveSpeed = 60; // inches per second
	double downSpeed = 36; // inches per second
	
	double counterHigh = 0;
	double counterLow = 0;
	double ticksToShift = 1;
	
	public ShiftAuto(){	
		requires(Robot.shift);
	}
	
	protected void initialize(){
		this.setInterruptible(true);
	}
	
	protected void execute(){
		sMode = Robot.shift.getMode();
		double speed = Math.abs(Robot.shift.calcSpeed(leftEnc.getRate(), rightEnc.getRate()));
			switch (sMode){
				case normal:
					if(speed >= normalSpeed){
						Robot.shift.HighGear();
						if(counterHigh <=ticksToShift){
							Robot.shift.setShifting(true);
						} else {
							Robot.shift.setShifting(false);
						}
						counterHigh++;
						counterLow = 0;
					} else  if(speed <= downSpeed){
						Robot.shift.LowGear();
						if(counterLow <=ticksToShift){
							Robot.shift.setShifting(true);
						} else {
							Robot.shift.setShifting(false);
						}
						counterLow++;
						counterHigh = 0;
					}
					break;
				case smooth:
					break;
				case aggressive:
					if(speed >= aggressiveSpeed){
						Robot.shift.HighGear();
					} else  if(speed <= downSpeed){
						Robot.shift.LowGear();
					}
					break;
				default: Robot.shift.LowGear();
					break;
			}
			
		
	}
	
	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}
	
	protected void end(){
		
	}

	protected void interrupted(){
		Robot.shift.LowGear();
	}
}
