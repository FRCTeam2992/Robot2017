package org.usfirst.frc2992.Robot;

import java.util.ArrayList;

import edu.wpi.first.wpilibj.SpeedController;
import com.ctre.CANTalon;

public class CurrentLimit {
	
	public static double wholeCurrent =0;
	
	ArrayList<SpeedController>leftmotors;
	ArrayList<SpeedController>rightmotors;
	public double[] motorValues;
	
	public double Counter;
	
	
	public CurrentLimit(){
		
		leftmotors = RobotMap.leftmotors;
		rightmotors = RobotMap.rightmotors;
		
		motorValues = new double[leftmotors.size() + rightmotors.size()];
		
			
	}
	public void Loop(){
		
		SenseCurrent();
		AddCurrent();
		LimitCurrent();
	}
	
	public void AddLoop(){
		SenseCurrent();
		AddCurrent();
	}
	
	
	
	public void SenseCurrent(){
		if(Robot.state	==	Robot.RobotState.Robot){
		motorValues[0] = ((CANTalon)leftmotors.get(0)).getOutputCurrent();
		motorValues[1] = ((CANTalon)leftmotors.get(1)).getOutputCurrent();
		motorValues[2] = ((CANTalon)leftmotors.get(2)).getOutputCurrent();
		motorValues[3] = ((CANTalon)rightmotors.get(0)).getOutputCurrent();
		motorValues[4] = ((CANTalon)rightmotors.get(1)).getOutputCurrent();
		motorValues[5] = ((CANTalon)rightmotors.get(2)).getOutputCurrent();	
		}else{	
			motorValues[0] = Robot.pdp.getVoltage();
		}	
	}
	

	public void AddCurrent(){
		for(int i=0;i<motorValues.length; i++){
			wholeCurrent += motorValues[i];
			
		}
	}
		
	public void LimitCurrent(){
		if(Robot.state	==	Robot.RobotState.Robot){
		if(wholeCurrent > 120){
			Counter ++;
			
		}
		if(wholeCurrent <= 120){
			Counter = 0;
		}	
		}else{	
			if(wholeCurrent < 8){
				Counter ++;
				
			}
			if(wholeCurrent >= 8){
				Counter = 0;
			}
		}
			
	}
	
	public double getCurrent(){
		return wholeCurrent;
	}

	public double getCount(){
		return Counter;
	}
	
}
