package org.usfirst.frc2992.Robot.commands;

import org.usfirst.frc2992.Robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class ShiftUp extends Command{
	
	public ShiftUp(){
		requires(Robot.shift);
	}
	
	protected void initialize(){
		this.setInterruptible(true);
	}
	
	protected void execute(){
		Robot.shift.HighGear();
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
