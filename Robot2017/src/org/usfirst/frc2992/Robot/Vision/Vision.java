package org.usfirst.frc2992.Robot.Vision;

import org.opencv.core.Rect;
import org.opencv.imgproc.Imgproc;
import org.usfirst.frc2992.Robot.Constants;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.vision.VisionThread;

/**
 * hasUpdated must be set to false to run. This is done by utilizing Run() and is intended to allow single use processing. 
 * This will automatically stop calculations after every cycle, so in order for consistent updates, you need to set hasUpdated 
 * to false every cycle that is needed.
 * TODO test to ensure that this fully works as intended. may need tweaking for accuracy.
 */
public class Vision {
	
	Thread visionThread;
	UsbCamera camera;
	ProcessImage processor = new ProcessImage();
	
	private boolean hasUpdated= false;
	public double[] kHue, kSat, kVal;
	private double distance, angle;
	private double[] center;
	
	private static Rect largestRectangle, secondRectangle;
	private static Rect testRec;
	private final static Object imgLock = new Object();
	
	
	private static Target type = Target.GreenTape;
	
	public enum Target{
		GreenTape, YellowStandingGear, YellowSittingGear;
	}
	
	public Vision(){
		//CameraServer.getInstance().startAutomaticCapture();
	}
	
	
	public synchronized void Init(){
		visionThread = new VisionThread(CameraServer.getInstance().getVideo().getSource(), new GripPipeline(), pipeline -> {
				
			if(hasUpdated == false){
				switch (type) {
				case GreenTape:
					kHue = Constants.greenTape.getHue();
					kSat = Constants.greenTape.getSat();
					kVal = Constants.greenTape.getVal();
					if (!pipeline.findContoursOutput().isEmpty()) {
						largestRectangle = Imgproc.boundingRect(pipeline.filterContoursOutput() .get(0));
						//secondRectangle = Imgproc.boundingRect(pipeline.filterContoursOutput() .get(0));
						for (int i = 1; i < pipeline.filterContoursOutput().size(); i++) {
							testRec = Imgproc.boundingRect(pipeline.findContoursOutput() .get(i));
							if (largestRectangle.area() < testRec.area()) {
								//secondRectangle = largestRectangle;
								largestRectangle = testRec;
							}
						}
						
						processor.Init(largestRectangle/*, secondRectangle*/);
						processor.run();
						
						center = processor.getCenter();
						distance = processor.getDistance();
						angle = processor.getHeading();
						hasUpdated = true;
						//System.out.println("[Center:" + center + "][Distance: " + distance + "][Angle: " + angle + "]"); 
					}
					break;
				case YellowStandingGear:
					kHue = Constants.yellowGearStanding.getHue();
					kSat = Constants.yellowGearStanding.getSat();
					kVal = Constants.yellowGearStanding.getVal();
					if (!pipeline.findContoursOutput().isEmpty()) {
						largestRectangle = Imgproc.boundingRect(pipeline.findContoursOutput() .get(0));
						for (int i = 1; i < pipeline.findContoursOutput().size(); i++) {
							testRec = Imgproc.boundingRect(pipeline.findContoursOutput() .get(i));
							if (largestRectangle.area() < testRec.area()) {
								largestRectangle = testRec;
							}
						}
						
						processor.Init(largestRectangle);
						processor.run();
						
						center = processor.getCenter();
						distance = processor.getDistance();
						angle = processor.getHeading();
						hasUpdated = true;
			
					}
					break;
				case YellowSittingGear:
					kHue = Constants.yellowGearSitting.getHue();
					kSat = Constants.yellowGearSitting.getSat();
					kVal = Constants.yellowGearSitting.getVal();
					if (!pipeline.findContoursOutput().isEmpty()) {
						largestRectangle = Imgproc.boundingRect(pipeline.findContoursOutput() .get(0));
						for (int i = 1; i < pipeline.findContoursOutput().size(); i++) {
							testRec = Imgproc.boundingRect(pipeline.findContoursOutput() .get(i));
							if (largestRectangle.area() < testRec.area()) {
								largestRectangle = testRec;
							}
						}
						
						processor.Init(largestRectangle);
						processor.run();
						
						center = processor.getCenter();
						distance = processor.getDistance();
						angle = processor.getHeading();
						hasUpdated = true;
						
					}
					break;	
				default: 
					break;
				}
			}
			
			});
			visionThread.start();
	}
	
	public synchronized void Run(){
		hasUpdated = false;
	}
	
	public synchronized void setTarget(Target type){
		Vision.type = type;
	}
	
	public synchronized Vision.Target getTarget(){
		return type;
	}
	
	public synchronized boolean hasUpdated(){
		return hasUpdated;
	}
	
	public synchronized double[] getCenter(){
		return center;
	}
	
	public synchronized double getDistance(){
		return distance;
	}
	
	public synchronized double getHeading(){
		return angle;
	}
}
