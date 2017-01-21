package org.usfirst.frc2992.MyRobot;

import java.util.ArrayList;

import edu.wpi.first.wpilibj.SpeedController;
import com.ctre.CANTalon;

public class CurrentLimit {
	
	public double wholeCurrent;
	
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
	
	
	
	public void SenseCurrent(){
		
		motorValues[0] = ((CANTalon) leftmotors.get(0)).getOutputCurrent();
		motorValues[1] = ((CANTalon)leftmotors.get(1)).getOutputCurrent();
		motorValues[2] = ((CANTalon)leftmotors.get(2)).getOutputCurrent();
		motorValues[3] = ((CANTalon)rightmotors.get(0)).getOutputCurrent();
		motorValues[4] = ((CANTalon)rightmotors.get(1)).getOutputCurrent();
		motorValues[5] = ((CANTalon)rightmotors.get(2)).getOutputCurrent();	
	}
	

	public void AddCurrent(){
		for(int i=0;i<=motorValues.length; i++){
			wholeCurrent += motorValues[i];
			
		}
	}
		
	public void LimitCurrent(){
		
		if(wholeCurrent > 120){
			Counter ++;
			
		}
		if(wholeCurrent <= 120){
			Counter = 0;
		}
		
		
		
	}
	
	
	
	

}
