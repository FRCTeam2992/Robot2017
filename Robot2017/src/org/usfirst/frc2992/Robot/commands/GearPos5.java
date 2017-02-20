package org.usfirst.frc2992.Robot.commands;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class GearPos5 extends CommandGroup {
	
    
    public GearPos5() {
    	addSequential(new GearUpTo2());
    	addSequential(new GearUpTo3());
    	addSequential(new GearUpTo4());
    	addSequential(new GearUpTo5());
    }

}
