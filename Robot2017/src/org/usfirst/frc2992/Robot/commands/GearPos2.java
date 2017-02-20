package org.usfirst.frc2992.Robot.commands;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class GearPos2 extends CommandGroup {
	
    
    public GearPos2() {
    	addSequential(new GearUpTo2());
		addSequential(new GearDownTo4());
		addSequential(new GearDownTo3());
		addSequential(new GearDownTo2());
    
    }

}
