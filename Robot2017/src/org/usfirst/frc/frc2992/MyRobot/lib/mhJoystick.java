package org.usfirst.frc.frc2992.MyRobot.lib;

import edu.wpi.first.wpilibj.Joystick;

public class mhJoystick extends Joystick{

	public mhJoystick(int port) {
		super(port);
		// TODO Auto-generated constructor stub
	};
	
	public mhJoystick(int port, int numAxisTypes, int numButtonTypes){
		super(port, numAxisTypes, numButtonTypes);
	}
	
	/*
	 * get x axis with smoothing
	 */
	
	public double smoothGetX(){
		double val = super.getX();
		return smoothPowerCurve(deadzone(val, .1));
	}
	
	/*
	 * get y axis with smoothing
	 */
	public double smoothGetY(){
		double val = super.getY();
		return smoothPowerCurve(deadzone(val, .1));
	}
	/*
	 * x axis with sine smoothing
	 */
	public double sinGetX(){
		double val = super.getX();
		return sinPowerCurve(deadzone(val, .1));
	}
	/*
	 * y axis with sine smoothing
	 */
	public double sinGetY(){
		double val = super.getY();
		return sinPowerCurve(deadzone(val, .1));
	}
	
	
	/**
	 *  This does the cubic smoothing equation on joystick value.
	 * Assumes you have already done any deadzone processing.
	 * 
	 * @param x  joystick input
	 * @return  smoothed value
	 */
	protected double smoothPowerCurve (double x) {
        
        if (x > 0 || x< 0){
            return (x*x*x);
        }else{
        	return 0;
        }
    }
	
	/**
	 * in progress sine smoothing for joysticks.
	 * assumes deadzone code is already added
	 * 
	 * @param x
	 * @return smoothed value
	 */
	protected double sinPowerCurve (double x){
		double input;
		double output;
		
		if (x > 1){
			x = 1;
		}
		if (x < -1){
			x = -1;
		}

		input = x * Math.PI - Math.PI;
		output = Math.sin(input);
		if (x > 0){
			return output + 1;
		} else if (x < 0 ){
			return output * -1 -1;
		} else{
			return 0;
		}
	}
	
	/**
	 * adds deadzone to joysticks
	 * 
	 * @param rawStick Raw value from joystick read -1.0 to 1.0
	 * @param dz	Deadzone value to use 0 to 0.999
     * @return		Value after deadzone processing
	 */
	
	 protected double deadzone(double rawStick, double dz) {
	        double stick;

	        // Force limit to -1.0 to 1.0
	        if (rawStick > 1.0) {
	            stick = 1.0;
	        } else if (rawStick < -1.0) {
	            stick = -1.0;
	        } else {
	            stick = rawStick;
	        }

	        // Check if value is inside the dead zone
	        if (stick >= 0.0){
	            if (Math.abs(stick) >= dz) 
	                return (stick - dz)/(1 -  dz);
	            else 
	                return 0.0;
	            
	        }
	        else {
	            if (Math.abs(stick) >= dz) 
	                return (stick + dz)/(1 - dz);
	            else 
	                return 0.0;
	            
	        }
	 }
}