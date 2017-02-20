package org.usfirst.frc2992.Robot.Vision;

import org.opencv.core.Rect;
import org.opencv.imgproc.Imgproc;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.vision.VisionThread;

public class Vision {
	
	Thread visionThread;
	UsbCamera camera;
	ProcessImage processor = new ProcessImage();
	
	public boolean hasUpdated= false;
	public double distance, angle;
	static Rect largestRectangle, secondRectangle;
	static Rect testRec;
	private final static Object imgLock = new Object();
	
	double[] center;
	
	static Target type = Target.YellowStandingGear;
	
	public enum Target{
		GreenTape, YellowStandingGear, YellowSittingGear;
	}
	
	public Vision(){
		CameraServer.getInstance().startAutomaticCapture();
	}
	
	public synchronized void Init(){
		visionThread = new VisionThread(CameraServer.getInstance().getVideo().getSource(), new GripPipeline(), pipeline -> {
				
			if(hasUpdated == false){
				switch (type) {
				case GreenTape:
					if (!pipeline.findContoursOutput().isEmpty()) {
						largestRectangle = Imgproc.boundingRect(pipeline.findContoursOutput() .get(0));
						secondRectangle = Imgproc.boundingRect(pipeline.findContoursOutput() .get(0));
						for (int i = 1; i < pipeline.findContoursOutput().size(); i++) {
							testRec = Imgproc.boundingRect(pipeline.findContoursOutput() .get(i));
							if (largestRectangle.area() < testRec.area()) {
								secondRectangle = largestRectangle;
								largestRectangle = testRec;
							}
						}
						
						processor.Init(largestRectangle, secondRectangle);
						
						hasUpdated = true;
						center = processor.getCenter();
						distance = processor.getDistance();
						angle = processor.getHeading();
			
					}
					break;
				case YellowStandingGear:
					if (!pipeline.findContoursOutput().isEmpty()) {
						largestRectangle = Imgproc.boundingRect(pipeline.findContoursOutput() .get(0));
						for (int i = 1; i < pipeline.findContoursOutput().size(); i++) {
							testRec = Imgproc.boundingRect(pipeline.findContoursOutput() .get(i));
							if (largestRectangle.area() < testRec.area()) {
								largestRectangle = testRec;
							}
						}
						
						processor.Init(largestRectangle);
						
						hasUpdated = true;
						center = processor.getCenter();
						distance = processor.getDistance();
						angle = processor.getHeading();
			
					}
					break;
				case YellowSittingGear:
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
						
						hasUpdated = true;
						center = processor.getCenter();
						distance = processor.getDistance();
						angle = processor.getHeading();
						
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
	
	public double[] getCenter(){
		return center;
	}
	
	public double getDistance(){
		return distance;
	}
	
	public double getHeading(){
		return angle;
	}
}
