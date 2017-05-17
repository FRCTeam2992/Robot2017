package org.usfirst.frc2992.Robot.subsystems;

import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.SpeedController;

import java.util.ArrayList;

public class RotatePID implements PIDOutput {

	private ArrayList<SpeedController> lDrive;
	private ArrayList<SpeedController> rDrive;

	public RotatePID (ArrayList<SpeedController> lMotors, ArrayList<SpeedController> rMotors) {
		lDrive = lMotors;
		rDrive = rMotors;
	}
		
	@Override
	public void pidWrite(double output) {
			
			
		for (SpeedController m : lDrive) {
			m.set(output);
		}
		for(SpeedController m : rDrive){
			m.set(-output);;
		}

	}

}
