package Autonomous;

import org.usfirst.frc2992.Robot.commands.*;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

public class AutoLeftGearRED extends CommandGroup {
	public AutoLeftGearRED(){
		addSequential(new WaitCommand(.5));
		addSequential(new DriveForward(85.963+16, 4, true, 0));
		addSequential(new DriveTurn(60, 2));
		addSequential(new GearPos4());
		addSequential(new DriveForward(49.074+9, 3, true, 60));
		//addSequential(new DriveForward(10.5+2, 2, true, 57));
		//addSequential(new GearPos5());
		//addSequential(new DriveForward(-36, 3, true, 57));
		//addSequential(new GearPos2());
		//addSequential(new DriveTurn(0, 3));
		//addSequential(new DriveForward(48, 4, true, 0));
	}
}
