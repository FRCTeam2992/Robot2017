	package org.usfirst.frc2992.Robot.subsystems;

	import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.SpeedController;

import java.util.ArrayList;

import org.usfirst.frc.frc2992.MyRobot.lib.mhRobotDrive;
import org.usfirst.frc2992.Robot.*;

	public class RotatePID implements PIDOutput {

		private ArrayList<SpeedController> lDrive;
		private ArrayList<SpeedController> rDrive;

		public RotatePID (ArrayList<SpeedController> lMotors, ArrayList<SpeedController> rMotors) {
			lDrive = lMotors;
			rDrive = rMotors;
		}
		
		@Override
		public void pidWrite(double output) {
			
			//get gyro angle
			// double gyroCorrection = mGyro.getAngle();
			
			// Ignore gyro correction for now
			for (SpeedController m : lDrive) {
				m.set(output);
			}
			for(SpeedController m : rDrive){
				m.set(-output);;
			}

		}

}
