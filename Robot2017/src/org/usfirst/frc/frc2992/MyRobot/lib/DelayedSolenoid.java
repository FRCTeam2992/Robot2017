package org.usfirst.frc.frc2992.MyRobot.lib;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Timer;

public class DelayedSolenoid {

	Solenoid sol_a;
	Solenoid sol_b;
	Timer timer;
	
	double time;
	
	public DelayedSolenoid(Solenoid sol_a, double time){
		this.sol_a = sol_a;
		this.time = time;
		timer = new Timer();
	}
	
	//allow for two solenoids to fire, in the case of symmetry or two actions
	public DelayedSolenoid(Solenoid sol_a, Solenoid sol_b, double time){
		this.sol_a = sol_a;
		this.sol_b = sol_b;
		this.time = time;
		timer = new Timer();
	}
	
	public void enable(){
		timer.start();
	}
	
	public void check(){
		if(timer.get() >= time){
			sol_a.set(true);
			if(sol_b != null){
				sol_b.set(true);
			}
			timer.stop();
		}
	}
}
