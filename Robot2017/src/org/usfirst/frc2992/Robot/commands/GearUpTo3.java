package org.usfirst.frc2992.Robot.commands;

import org.usfirst.frc2992.Robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

public class GearUpTo3 extends Command{
	int state;
	Timer timer;
	double maxTime = .4;
	
	public GearUpTo3(){
		requires(Robot.gearSystem);
		this.setInterruptible(false);
		if (timer == null) {
			timer = new Timer();
		}
	}
	
	protected void initialize(){
		state = Robot.gearSystem.getState();
		timer.reset();
		timer.start();
	}
	
	protected void execute(){
		if(state ==2){
			Robot.gearSystem.gear2to3();
		}
	}
	
	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return timer.get() >= maxTime || state !=2;
	}

	protected void interrupted(){
		
	}
	
	protected void end(){
		
	}
}
