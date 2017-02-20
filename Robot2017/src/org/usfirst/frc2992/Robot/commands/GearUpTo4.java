package org.usfirst.frc2992.Robot.commands;

import org.usfirst.frc2992.Robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

public class GearUpTo4 extends Command{
	int state;
	Timer timer;
	double maxTime = .5;
	
	public GearUpTo4(){
		requires(Robot.gearSystem);
		this.setInterruptible(false);
	}
	
	protected void initialize(){
		state = Robot.gearSystem.getState();
		timer.reset();
		timer.start();
	}
	
	protected void execute(){
		if(state ==3){
			Robot.gearSystem.gear3to4();
		}
	}
	
	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return timer.get() >= maxTime || state !=3;
	}

}
