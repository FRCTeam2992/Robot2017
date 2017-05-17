package Autonomous;

import org.usfirst.frc2992.Robot.commands.*;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

public class AutoRightGearRED extends CommandGroup {
	public AutoRightGearRED(){
		addSequential(new WaitCommand(.5));
		addSequential(new DriveForward(89.1+5, 4, true, 0));
		addSequential(new DriveTurn(-60, 3));
		addParallel(new GearPos4());
		addSequential(new DriveForward(24+5, 3, true, -60));
		
		//addSequential(new DriveForward(18.5, 3, true, -60));
		//addSequential(new GearPos5());
		//addSequential(new DriveForward(-36, 3, true, -60));
		//addSequential(new GearPos2());
		//addSequential(new DriveTurn(0, 3));
		//addSequential(new DriveForward(48, 4, true, 0));
	}
}
