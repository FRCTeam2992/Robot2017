package Autonomous;

import org.usfirst.frc2992.Robot.*;
import org.usfirst.frc2992.Robot.commands.*;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

public class AutoRightGearBLUE extends CommandGroup {
	public AutoRightGearBLUE(){
		addSequential(new DriveForward(95.777, 4, true, 0));
		addSequential(new DriveTurn(-60, 3));
		addSequential(new WaitCommand(1.0));
		addSequential(new GearPos4());
		addSequential(new DriveForward(7.847+5, 5, true, -60));
		//addSequential(new DriveForward(10.5+10, 3, true, 60));
		//addSequential(new GearPos5());
		//addSequential(new DriveForward(-36, 3, true, 60));
		//addSequential(new GearPos2());
		//addSequential(new DriveTurn(0, 3));
		//addSequential(new DriveForward(48, 4, true, 0));
	}
}
