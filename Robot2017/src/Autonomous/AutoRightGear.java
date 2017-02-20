package Autonomous;

import org.usfirst.frc2992.Robot.Robot;
import org.usfirst.frc2992.Robot.commands.*;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

public class AutoRightGear extends CommandGroup {
	public AutoRightGear(){
		addSequential(new DriveForward(114.3 - Robot.constants.fullLength, 2));
		addSequential(new DriveTurn(61.84, 1));
		addSequential(new GearPos4());
		addSequential(new DriveForward(36, 1));
		addSequential(new WaitCommand(0.5));
		addSequential(new GearPos5());
		addSequential(new DriveForward(-36, 1));
	}
}
