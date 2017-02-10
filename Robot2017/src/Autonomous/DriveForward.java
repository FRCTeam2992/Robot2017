package Autonomous;


import org.usfirst.frc2992.Robot.Robot;
import org.usfirst.frc.frc2992.MyRobot.lib.TimedBoolean;



import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

public class DriveForward extends Command {
	
	
	
	double mDist;
	double mTime;
	Timer totalTime;
	//TimedBoolean timer;

	public DriveForward(double dist, double time) {
		
		mDist = dist;
		mTime = time;
		
		//totalTime = new Timer();
		//timer = new TimedBoolean(time);
		
		requires(Robot.driveTrain);
		// TODO Auto-generated constructor stub
	}
	
	protected void initialize(){
		this.setInterruptible(true);
		
		//totalTime.reset();
		//totalTime.start();
	}
	
	protected void execute(){
		
		Robot.driveTrain.SmartDriveDist(mDist);
		
	}

	@Override
	protected boolean isFinished() {
		return false;
		/*
		if(totalTime.get() >= mTime){
			
		// TODO Auto-generated method stub
		return true;
		
		}
		else{
			return false;
		}
		*/
		
	}
	
	protected void end(){
		Robot.driveTrain.motorStop();
	}
	
	protected void interuppted(){
		Robot.driveTrain.motorStop();
		
	}

}
