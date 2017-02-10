package Autonomous;

import org.usfirst.frc2992.Robot.commands.*;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

public class GearCycle extends CommandGroup{
	double delay = 8;
	public GearCycle(){
		addSequential(new GearPos1(delay));
		addSequential(new WaitCommand(delay));
		addSequential(new GearPos2(delay));
		addSequential(new WaitCommand(delay));
		addSequential(new GearPos2_5(delay));
		addSequential(new WaitCommand(delay));
		addSequential(new GearPos3(delay));
		addSequential(new WaitCommand(delay));
		addSequential(new GearPos4(delay));
		addSequential(new WaitCommand(delay));
		//going back
		addSequential(new GearPos3(delay));
		addSequential(new WaitCommand(delay));
		addSequential(new GearPos2_5(delay));
		addSequential(new WaitCommand(delay));
		addSequential(new GearPos2(delay));
		addSequential(new WaitCommand(delay));
	}
}
