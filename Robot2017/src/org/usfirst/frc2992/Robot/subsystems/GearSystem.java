// RobotBuilder Version: 2.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.


package org.usfirst.frc2992.Robot.subsystems;

import org.usfirst.frc2992.Robot.RobotMap;
import org.usfirst.frc2992.Robot.commands.*;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.Solenoid;

import edu.wpi.first.wpilibj.command.Subsystem;


/**
 *
 */
public class GearSystem extends Subsystem {

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    private final Servo leftGearHold = RobotMap.gearSystemLeftGearHold;
    private final Servo rightGearHold = RobotMap.gearSystemRightGearHold;
    private final DigitalInput gearLoadedSensor = RobotMap.gearSystemGearLoadedSensor;
    private final Solenoid feedRampUpDown = RobotMap.gearSystemRampUpDown;
    private final Solenoid jumpRampInOut = RobotMap.gearSystemDoorInOut;
    private final Solenoid deckUpDown = RobotMap.gearSystemGearSlideUpDown;
    private final Solenoid gearTest = RobotMap.gearSystemTest;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS


    
    int state;
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND


        // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND

        // Set the default command for a subsystem here.
        // setDefaultCommand(new MySpecialCommand());
    	setDefaultCommand(new GearPos3());
    }
    
    /**
     * gear from pos 2 to pos 1
     */
    public void gear2to1(){// gear pos 1
    	state = 1;
    	feedRampUpDown.set(true);
    	//jumpRampInOut.set(false);
    	//deckUpDown.set(true);
    	leftGearHold.setAngle(50);
    	rightGearHold.setAngle(120);
    	//System.out.println("1");
    }
    
    /**
     * gear from pos 1 to pos 2
     */
    public void gear1to2(){// gear pos 2
    	state = 2;
    	feedRampUpDown.set(false);
    	//jumpRampInOut.set(false);
    	//deckUpDown.set(true);
    	leftGearHold.setAngle(170);
    	rightGearHold.setAngle(0);
    	
    	
    	//System.out.println("2");	
    }
    
    /**
     * gear pos 3
     */
    public void gear2to3(){// gear pos 3
    	state = 3;
    	//feedRampUpDown.set(false);
    	//jumpRampInOut.set(false);
    	deckUpDown.set(false);
    	//leftGearHold.setAngle(170);
    	//rightGearHold.setAngle(0);
    	//System.out.println("3");
    }
    
    public void gear3to2(){// gear pos 3
    	state = 2;
    	//feedRampUpDown.set(false);
    	//jumpRampInOut.set(false);
    	deckUpDown.set(true);
    	//leftGearHold.setAngle(170);
    	//rightGearHold.setAngle(0);
    	//System.out.println("3");
    }
    
    /**
     * gear pos 4
     */
    public void gear3to4(){// gear pos 4
    	state = 4;
    	//feedRampUpDown.set(false);
    	jumpRampInOut.set(true);
    	//deckUpDown.set(false);
    	//leftGearHold.setAngle(170);
    	//rightGearHold.setAngle(0);
    	//System.out.println("4");
    }
    
    public void gear4to3(){// gear pos 4
    	state = 3;
    	//feedRampUpDown.set(false);
    	jumpRampInOut.set(false);
    	//deckUpDown.set(false);
    	//leftGearHold.setAngle(170);
    	//rightGearHold.setAngle(0);
    	//System.out.println("4");
    }
    
    /**
     * gear pos 5
     */
    public void gear4to5(){// gear pos 5
    	state = 5;
    	feedRampUpDown.set(true);
    	//jumpRampInOut.set(true);
    	//deckUpDown.set(false);
    	leftGearHold.setAngle(50);
    	rightGearHold.setAngle(120);
    	//System.out.println("5");
    }
    
    public void gear5to4(){// gear pos 5
    	state = 4;
    	feedRampUpDown.set(false);
    	//jumpRampInOut.set(true);
    	//deckUpDown.set(false);
    	leftGearHold.setAngle(170);
    	rightGearHold.setAngle(0);
    	//System.out.println("5");
    }
    
    public int getState(){
    	return state;
    }
    
    
}

