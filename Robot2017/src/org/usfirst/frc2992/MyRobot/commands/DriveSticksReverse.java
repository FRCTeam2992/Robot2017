package org.usfirst.frc2992.MyRobot.commands;

import org.usfirst.frc.frc2992.MyRobot.lib.mhJoystick;
import org.usfirst.frc2992.MyRobot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class DriveSticksReverse extends Command {
	
	public static mhJoystick leftJoy;
	public static mhJoystick rightJoy;
	
	
	
	
	public DriveSticksReverse(){
		
		requires(Robot.driveTrain);
	}
	
	protected void initialize(){
		this.setInterruptible(true);
		
		Robot.driveTrain.motorStop();
	}
	
	protected void execute(){
		leftJoy = Robot.oi.getLeftJoy();
    	rightJoy = Robot.oi.getRightJoy();
    	
    	Robot.driveTrain.tankDriveReverse(leftJoy, rightJoy);
	}
	
	

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}
	
	protected void End(){
		
		Robot.driveTrain.motorStop();
		
	}
	
	protected void Interrupted(){
		Robot.driveTrain.motorStop();
	}

}
