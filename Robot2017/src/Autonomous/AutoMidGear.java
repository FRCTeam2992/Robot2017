package Autonomous;

import org.usfirst.frc2992.Robot.commands.*;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

public class AutoMidGear extends CommandGroup{

	public AutoMidGear(){
		addSequential(new WaitCommand(2));
		addSequential(new DriveForward(71.5, 4, true, 0));
		addSequential(new GearPos4());
		addSequential(new WaitCommand(.75));
		addSequential(new DriveForward(20, 3, true, 0));
		//addSequential(new GearPos5());
		//addSequential(new DriveForward(-36, 4));
		//addSequential(new GearPos3());
		//addSequential(new DriveTurn(90, 0));
	}
}
