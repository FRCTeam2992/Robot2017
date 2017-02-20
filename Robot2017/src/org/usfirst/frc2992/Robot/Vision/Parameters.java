package org.usfirst.frc2992.Robot.Vision;

/**
 * @author Jackson
 * storage container for Vision constants
 * 
 */
public class Parameters {
	
	double height;
	double width;
	
	double[] hsvThresholdHue;
	double[] hsvThresholdSaturation;
	double[] hsvThresholdValue;
	
	public Parameters(double h, double w, double[] hue, double[] sat, double[] val){
		this.height = h;
		this.width = w;
		this.hsvThresholdHue = hue;
		this.hsvThresholdSaturation = sat;
		this.hsvThresholdValue = val;
	}
	
	public double getHeight(){
		return height;
	}
	
	public double getWidth(){
		return width;
	}
	
	public double[] getHue(){
		return hsvThresholdHue;
	}
	
	public double[] getSat(){
		return hsvThresholdSaturation;
	}
	
	public double[] getVal(){
		return hsvThresholdValue;
	}
}
