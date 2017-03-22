package Autonomous;


import org.usfirst.frc2992.Robot.Robot;
import org.usfirst.frc2992.Robot.RobotMap;
import org.usfirst.frc.frc2992.MyRobot.lib.TimedBoolean;



import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class DriveForward extends Command {
	
	
	
	double mDist;
	double mTime;
	Timer totalTime;
	double curPower;
	
	static final double MINPOWER = 0.3;
	static final double MAXPOWER = 0.6;
	static final double POWERRAMP = 0.015;
	
	boolean mGyro;
	double mHeading;
	
	//TimedBoolean timer;

	public DriveForward(double dist, double time) {
		mGyro = false;
		mHeading = 0;
		mDist = dist;
		mTime = time;
		
		totalTime = new Timer();
		//timer = new TimedBoolean(time);
		
		requires(Robot.driveTrain);
		// TODO Auto-generated constructor stub
	}
	
	public DriveForward(double dist, double time, boolean useGyro, double heading){
		mGyro = useGyro;
		mHeading = heading;
		mDist = dist;
		mTime = time;
		
		totalTime = new Timer();
		//timer = new TimedBoolean(time);
		
		requires(Robot.driveTrain);
		// TODO Auto-generated constructor stub
	}
	
	protected void initialize(){
		this.setInterruptible(true);
		
		totalTime.reset();
		totalTime.start();
		RobotMap.driveTrainLeftDriveEncoder.reset();
		RobotMap.driveTrainRightDriveEncoder.reset();

		curPower = MINPOWER;
		Robot.driveTrain.SmartDrivePower(MINPOWER);
		Robot.driveTrain.SmartDriveDist(mDist);
	}
	
	protected void execute(){
		curPower = Math.min(MAXPOWER,  curPower+POWERRAMP);
		SmartDashboard.putNumber("power", curPower);
		if(mGyro){
			Robot.driveTrain.SmartDriveGyro(mHeading, curPower);
		} else {
			Robot.driveTrain.SmartDrivePower(curPower);
		}
	}

	@Override
	protected boolean isFinished() {
		if(totalTime.get() > mTime || Robot.driveTrain.driveDone("dist")){
    		return true; 
    	} else{
    		return false;
    	}
		
	}
	
	protected void end(){
		Robot.driveTrain.motorStop();
	}
	
	protected void interuppted(){
		Robot.driveTrain.motorStop();
		
	}

}
