package Autonomous;

import org.usfirst.frc2992.Robot.*;
import org.usfirst.frc2992.Robot.commands.*;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

public class AutoLeftGearRED extends CommandGroup {
	public AutoLeftGearRED(){
		addSequential(new DriveForward(68.288+2, 4, true, 0));
		addSequential(new DriveTurn(57, 2));
		addSequential(new DriveForward(65.424 - 15.5, 5, true, 57));
		addSequential(new WaitCommand(0.5));
		addSequential(new DriveTurn(57, 2));
		addSequential(new GearPos4());
		addSequential(new DriveForward(10.5+2, 2, true, 57));
		addSequential(new GearPos5());
		addSequential(new DriveForward(-36, 3, true, 57));
		//addSequential(new GearPos2());
		//addSequential(new DriveTurn(0, 3));
		//addSequential(new DriveForward(48, 4, true, 0));
	}
}
