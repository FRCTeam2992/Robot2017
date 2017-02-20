package org.usfirst.frc2992.Robot.commands;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class GearPos1 extends CommandGroup {
	
    
    public GearPos1() {
		addSequential(new GearDownTo4());
		addSequential(new GearDownTo3());
		addSequential(new GearDownTo2());
		addSequential(new GearDownTo1());
    
    }

}
