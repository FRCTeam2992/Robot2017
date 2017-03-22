package Autonomous;


import org.usfirst.frc2992.Robot.Robot;



import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class DriveTurn extends Command {
	
	
	
	double mDegree;
	double mTime;
	Timer totalTime;
	double power;
	static final double MINPOWER = 0.1;
	static final double MAXPOWER = 0.7;
	static final double POWERRAMP = 0.005;

	public DriveTurn(double degree, double time) {
		
		mDegree = degree;
		mTime = time;
		
		totalTime = new Timer();
		
		requires(Robot.driveTrain);
		// TODO Auto-generated constructor stub
	}
	
	public void initialize(){
		power = MINPOWER;
		this.setInterruptible(true);
		
		totalTime.reset();
		totalTime.start();
		
		Robot.driveTrain.SmartDriveRot(mDegree);
	}
	
	protected void execute(){
		power = Math.min(power += POWERRAMP, MAXPOWER);
		Robot.driveTrain.turnPID.setOutputRange(-power, power);
	}
	 
	@Override
	protected boolean isFinished() {
		if(totalTime.get() > mTime || Robot.driveTrain.driveDone("rot")){
    		return true; 
    	} else{
    		return false;
    	}
		
	}
	
	protected void end(){
		Robot.driveTrain.motorStop();
	}
	
	protected void interuppted(){
		
	}

}
