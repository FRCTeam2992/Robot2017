package Autonomous;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

public class TestStuff extends CommandGroup {
	
	public TestStuff(){
		
		addSequential(new DriveForward(150, 4));
		addSequential(new DriveStop());
    	
    	
		
	}
	
	

}
