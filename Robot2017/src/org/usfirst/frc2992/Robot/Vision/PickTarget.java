package org.usfirst.frc2992.Robot.Vision;

public class PickTarget {
	
	Vision.Target type;
	Vision vision;

	public PickTarget(Vision.Target type){
		this.type = type;
		vision = new Vision();
	}
	
	public void Init(Vision.Target type){
		this.type = type;
	}
	
	public void Run(){
	}
}
