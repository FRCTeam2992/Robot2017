package Autonomous;


import org.usfirst.frc.frc2992.MyRobot.lib.mhJoystick;
import org.usfirst.frc2992.Robot.Robot;



import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

public class DriveTurn extends Command {
	
	
	
	double mDegree;
	double mTime;
	Timer totalTime;

	public DriveTurn(double degree, int time) {
		
		mDegree = degree;
		mTime = time;
		
		totalTime = new Timer();
		
		requires(Robot.driveTrain);
		// TODO Auto-generated constructor stub
	}
	
	public void initialize(){
		this.setInterruptible(true);
		
		totalTime.reset();
		totalTime.start();
	}
	
	protected void execute(){
		
		Robot.driveTrain.SmartDriveRot(mDegree);
		
	}
	 
	@Override
	protected boolean isFinished() {
		
		if(totalTime.get() < mTime){
			
		// TODO Auto-generated method stub
		return false;
		
		}
		else{
			return true;
		}
		
	}
	
	protected void end(){
		Robot.driveTrain.motorStop();
	}
	
	protected void interuppted(){
		
	}

}
