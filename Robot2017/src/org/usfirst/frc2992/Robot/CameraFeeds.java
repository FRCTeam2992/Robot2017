package org.usfirst.frc2992.Robot;

import java.util.Timer;
import java.util.TimerTask;


import edu.wpi.cscore.*;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class CameraFeeds extends TimerTask{

	int[] kRes = {Constants.kWidth, Constants.kHeight}; // default to 320x240
	int kFPS = Constants.kFPS; // default to 20 fps
	int width = kRes[0];
	int height = kRes[1];
	
	boolean allowCam1 = false;
	
	CvSink cvSink1, cvSink2;
	public VideoSink outputStream;
	UsbCamera camera1, camera2;
	
	public CameraFeeds(){
		SmartDashboard.putNumber("kWidth", kRes[0]);
    	SmartDashboard.putNumber("kHeight",kRes[1]);
	}
	
	public CameraFeeds(int[] res){
		kRes = res;
		SmartDashboard.putNumber("kWidth", kRes[0]);
    	SmartDashboard.putNumber("kHeight",kRes[1]);
	}
	
	public CameraFeeds(int[] res, int fps){
		kRes = res;
		kFPS = fps;
		SmartDashboard.putNumber("kWidth", kRes[0]);
    	SmartDashboard.putNumber("kHeight",kRes[1]);
	}
	
	public void init(){
        
			
		width = kRes[0];
		height = kRes[1];
		
		
		camera1 = CameraServer.getInstance().startAutomaticCapture(0);
		camera1.setResolution(width, height);
		camera1.setFPS(kFPS);
		camera2 = CameraServer.getInstance().startAutomaticCapture(1);
		camera2.setResolution(width, height);
		camera2.setFPS(kFPS);
		
		
		SmartDashboard.putString("Active Cam", "0");

		cvSink1 = CameraServer.getInstance().getVideo(camera1);
		cvSink2 = CameraServer.getInstance().getVideo(camera2);
		
		cvSink1.setEnabled(true);
		cvSink2.setEnabled(true);
		
		outputStream = CameraServer.getInstance().getServer();
		
		new Timer().scheduleAtFixedRate(this, 0, 20);
	}

	public void run() {
		if(Robot.oi.getRightJoy().getRawButton(6)) { // figure out which button is optimal
			SmartDashboard.putBoolean("CamSwitch", true);
			allowCam1 = true;
		}
		if(Robot.oi.getRightJoy().getRawButton(5)) { // figure out which button is optimal
			SmartDashboard.putBoolean("CamSwitch", false);
			allowCam1 = false;
		}

		//Mat image = new Mat();

		
		
		if(allowCam1){
			SmartDashboard.putString("Active Cam", "1");
			outputStream.setSource(camera1);
		} else{
			SmartDashboard.putString("Active Cam", "2");
			outputStream.setSource(camera2);
			
		}
		
		
	}
}
