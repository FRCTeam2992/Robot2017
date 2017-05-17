package Autonomous;

import org.usfirst.frc2992.Robot.commands.*;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

public class AutoMidGearFAST extends CommandGroup{

	public AutoMidGearFAST(){
		//addParallel(new ShiftAuto());
		addSequential(new WaitCommand(2));
		addSequential(new GearPos4());
		addSequential(new DriveFast(90.5, 4, true, 0));
	}
}
