package Autonomous;


import org.usfirst.frc2992.Robot.Robot;
import org.usfirst.frc2992.Robot.RobotMap;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class DriveTest extends Command {
	
	
	
	double mDistL, mDistR;
	double mTime;
	Timer totalTime;
	double curPower;
	
	static final double MINPOWER = 0.1;
	static final double MAXPOWER = 0.5;
	static final double POWERRAMP = 0.005;
	
	boolean mGyro;
	double mHeading;
	

	public DriveTest(double distL, double distR, double time, double heading) {
		mGyro = true;
		mHeading = heading;
		mDistL = distL;
		mDistR = distR;
		mTime = time;
		
		totalTime = new Timer();
		//timer = new TimedBoolean(time);
		
		requires(Robot.driveTrain);
		// TODO Auto-generated constructor stub
	}
	/*
	public DriveForward(double dist, double time, boolean useGyro, double heading){
		mGyro = useGyro;
		mHeading = heading;
		mDistL = dist;
		mTime = time;
		
		totalTime = new Timer();
		
		requires(Robot.driveTrain);
		// TODO Auto-generated constructor stub
	}
	*/
	protected void initialize(){
		this.setInterruptible(true);
		
		totalTime.reset();
		totalTime.start();
		RobotMap.driveTrainLeftDriveEncoder.reset();
		RobotMap.driveTrainRightDriveEncoder.reset();

		curPower = MINPOWER;
		Robot.driveTrain.SmartDrivePower(MINPOWER);
		//Robot.driveTrain.SmartDriveDist(mDistL);
		Robot.driveTrain.lDistPID.setSetpoint(mDistL);
		Robot.driveTrain.rDistPID.setSetpoint(mDistR);
		Robot.driveTrain.lDistPID.enable();
		Robot.driveTrain.rDistPID.enable();
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
