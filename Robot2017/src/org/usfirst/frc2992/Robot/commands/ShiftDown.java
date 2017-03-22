package org.usfirst.frc2992.Robot.commands;

import org.usfirst.frc2992.Robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class ShiftDown extends Command{
	
	public ShiftDown(){
		requires(Robot.shift);
	}
	
	protected void initialize(){
		this.setInterruptible(true);
	}
	
	protected void execute(){
		Robot.shift.LowGear();
	}
	
	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}
	
	protected void end(){
		
	}

	protected void interrupted(){
		
	}
}
