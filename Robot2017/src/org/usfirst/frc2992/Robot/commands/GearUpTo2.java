package org.usfirst.frc2992.Robot.commands;

import org.usfirst.frc2992.Robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

public class GearUpTo2 extends Command{
	int state;
	Timer timer;
	double maxTime = .5;
	
	public GearUpTo2(){
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
		if(state ==1){
			Robot.gearSystem.gear1to2();
		}
	}
	
	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return timer.get() >= maxTime || state !=1;
	}
	
	protected void interrupted(){
		
	}
	
	protected void end(){
		
	}

}
