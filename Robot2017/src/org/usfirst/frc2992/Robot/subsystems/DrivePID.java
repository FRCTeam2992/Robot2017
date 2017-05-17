package org.usfirst.frc2992.Robot.subsystems;

import java.util.ArrayList;

import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.SpeedController;

public class DrivePID implements PIDOutput {

	private ArrayList<SpeedController> mDrive;

	public DrivePID (ArrayList<SpeedController> motors) {
		mDrive = motors;
	}
	
	@Override
	public void pidWrite(double output) {
		
		
		for (SpeedController m : mDrive) {
			m.set(output);
		}

	}

}