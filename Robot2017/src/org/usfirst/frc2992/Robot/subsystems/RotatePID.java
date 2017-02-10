	package org.usfirst.frc2992.Robot.subsystems;

	import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.SpeedController;

import java.util.ArrayList;

import org.usfirst.frc.frc2992.MyRobot.lib.mhRobotDrive;
import org.usfirst.frc2992.Robot.*;

	public class RotatePID implements PIDOutput {

		private ArrayList<SpeedController> mDrive;

		public RotatePID (ArrayList<SpeedController> motors) {
			mDrive = motors;
		}
		
		@Override
		public void pidWrite(double output) {
			
			//get gyro angle
			// double gyroCorrection = mGyro.getAngle();
			
			// Ignore gyro correction for now
			for (SpeedController m : mDrive) {
				m.set(output);
			}

		}

}
