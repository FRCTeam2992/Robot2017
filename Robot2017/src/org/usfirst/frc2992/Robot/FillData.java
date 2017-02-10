package org.usfirst.frc2992.Robot;

import java.io.File;
import java.io.IOException;

import org.usfirst.frc.frc2992.MyRobot.MotionProfiling.*;

public class FillData {
	RobotSegmentGroup dataPts;
	
	public FillData(File path){
		try {
			dataPts = Util.stringToPath236(Util.readStringFromFile(path));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
