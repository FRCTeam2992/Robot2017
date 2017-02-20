package org.usfirst.frc2992.Robot.commands;

import org.usfirst.frc2992.Robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

public class GearDownTo3 extends Command{
	int state;
	Timer timer;
	double maxTime = .5;
	
	public GearDownTo3(){
		requires(Robot.gearSystem);
		this.setInterruptible(false);
	}
	
	protected void initialize(){
		state = Robot.gearSystem.getState();
		timer.reset();
		timer.start();
	}
	
	protected void execute(){
		if(state ==4){
			Robot.gearSystem.gear4to3();
		}
	}
	
	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return timer.get() >= maxTime || state !=4;
	}

}
