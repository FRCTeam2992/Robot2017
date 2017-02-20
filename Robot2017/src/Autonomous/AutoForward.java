package Autonomous;

import org.usfirst.frc2992.Robot.commands.*;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

public class AutoForward extends CommandGroup{
	double delay = 8;
	public AutoForward(double dist, double time){
		addSequential(new DriveForward(dist, time));
		addSequential(new DriveStop());
	}
}
