package org.usfirst.frc2992.Robot.commands;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class GearPos3 extends CommandGroup {
	
    
    public GearPos3() {
    	addSequential(new GearUpTo2());
    	addSequential(new GearUpTo3());
		addSequential(new GearDownTo4());
		addSequential(new GearDownTo3());
    
    }

}
