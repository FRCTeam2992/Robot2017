package org.usfirst.frc2992.Robot;

import org.opencv.core.Mat;

import edu.wpi.cscore.*;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class CameraFeeds {

	int[] kRes = {320, 240}; // default to 320x240
	int kFPS = 30; // default to 30 fps
	int width = kRes[0];
	int height = kRes[1];
	
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
	
	public synchronized void init(){
		Thread t = new Thread(() -> {
        
			boolean allowCam1 = false;
			if(SmartDashboard.getNumber("kWidth", kRes[0]) != width || SmartDashboard.getNumber("kHeight", kRes[1]) != height){
	    		width = (int) SmartDashboard.getNumber("kWidth", kRes[0]);
	    		height = (int)SmartDashboard.getNumber("kHeight", kRes[1]);
	    	}
			UsbCamera camera1 = CameraServer.getInstance().startAutomaticCapture(0);
			camera1.setResolution(width, height);
			camera1.setFPS(kFPS);
			//UsbCamera camera2 = CameraServer.getInstance().startAutomaticCapture(1);
			//camera2.setResolution(kRes[0], kRes[1]);
			//camera2.setFPS(kFPS);
    
			CvSink cvSink1 = CameraServer.getInstance().getVideo(camera1);
			//CvSink cvSink2 = CameraServer.getInstance().getVideo(camera2);
			CvSource outputStream = CameraServer.getInstance().putVideo("Switcher", width, height);
    
			Mat image = new Mat();
    
			while(!Thread.interrupted()) {
       
				if(Robot.oi.getRightJoy().getRawButton(6)) { // figure out which button is optimal
					allowCam1 = !allowCam1;
				}
       
				if(allowCam1){
					//cvSink2.setEnabled(false);
					cvSink1.setEnabled(true);
					cvSink1.grabFrameNoTimeout(image);
				} else{
					cvSink1.setEnabled(false);
					//cvSink2.setEnabled(true);
					//cvSink2.grabFrameNoTimeout(image);    
				}
        
				outputStream.putFrame(image);
			}
    
		});
	
		t.start();
	}
}
