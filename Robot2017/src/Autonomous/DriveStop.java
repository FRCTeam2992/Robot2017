package Autonomous;

import org.usfirst.frc2992.Robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

public class DriveStop extends Command {

public DriveStop() {
		
		requires(Robot.driveTrain);
		// TODO Auto-generated constructor stub
	}
	
	protected void initialize(){
		this.setInterruptible(true);
		
	}
	
	protected void execute(){
		
		Robot.driveTrain.motorStop();
		
	}
	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}

}
