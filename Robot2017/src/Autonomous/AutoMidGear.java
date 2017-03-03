package Autonomous;

import org.usfirst.frc2992.Robot.commands.*;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutoMidGear extends CommandGroup{

	public AutoMidGear(){
		addSequential(new GearPos3());
		addSequential(new DriveForward(55, 4, false, 0));
		addSequential(new GearPos4());
		addSequential(new DriveForward(30, 2, false, 0));
		addSequential(new GearPos5());
		addSequential(new DriveForward(-40, 4));
		addSequential(new GearPos3());
		//addSequential(new DriveTurn(90, 0));
	}
}
