package org.usfirst.frc.frc2992.MyRobot.lib;

import edu.wpi.first.wpilibj.Timer;

public class TimedBoolean {
	
	double time;
	boolean isDone;
	
	Timer timer;
	
	public TimedBoolean(double time){
		this.time = time;
		timer = new Timer();
		timer.start();
	}
	
	
	public boolean check(){
		
		if(timer.get() < time){
			isDone = false;
		}
		
		if(timer.get() >= time){
			isDone = true;
			timer.stop();
		}
		
		return isDone;
		
	}
}
