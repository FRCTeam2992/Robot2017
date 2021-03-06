// RobotBuilder Version: 2.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.


package org.usfirst.frc2992.Robot;

import org.usfirst.frc.frc2992.MyRobot.lib.mhJoystick;
import org.usfirst.frc2992.Robot.commands.*;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.*;


/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
    //// CREATING BUTTONS
    // One type of button is a joystick button which is any button on a joystick.
    // You create one by telling it which joystick it's on and which button
    // number it is.
    // Joystick stick = new Joystick(port);
    // Button button = new JoystickButton(stick, buttonNumber);

    // There are a few additional built in buttons you can use. Additionally,
    // by subclassing Button you can create custom triggers and bind those to
    // commands the same as any other Button.

    //// TRIGGERING COMMANDS WITH BUTTONS
    // Once you have a button, it's trivial to bind it to a button in one of
    // three ways:

    // Start the command when the button is pressed and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenPressed(new ExampleCommand());

    // Run the command while the button is being held down and interrupt it once
    // the button is released.
    // button.whileHeld(new ExampleCommand());

    // Start the command when the button is released  and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenReleased(new ExampleCommand());


    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    public mhJoystick leftJoy;
    public mhJoystick rightJoy;
    
    public JoystickButton shiftUp;
    public JoystickButton shiftAuto;
  
    public JoystickButton intakeForwardOff;
    public JoystickButton intakeBackwardOff;
   
    public JoystickButton climbOnOff;
  
    public JoystickButton rampUpAuto;
    public JoystickButton rampDownAuto;
   
    public JoystickButton deliverNot;
    
    public JoystickButton dropGear;
    
    public JoystickButton testGear;
    public JoystickButton testGearStop;
    
    public JoystickButton secretWeapon;
    
    public JoystickButton FrontDrive;
    public JoystickButton RevDrive;
    
    public Joystick buttonbox;
    
    public JoystickButton TestGear1;
    public JoystickButton TestGear2;
    public JoystickButton TestGear3;
    public JoystickButton TestGear4;
    public JoystickButton TestGear5;
    
    public JoystickButton Gear1;
    public JoystickButton Gear2;
    public JoystickButton Gear3;
    public JoystickButton Gear4;
    public JoystickButton Gear5;
    
    public JoystickButton autoA;
	public JoystickButton autoB;
	public JoystickButton autoC;
    

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS

    public OI() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS


    	rightJoy = new mhJoystick(0);
        leftJoy = new mhJoystick(1);
        buttonbox = new Joystick(2);
        
        //FrontDrive = new JoystickButton(rightJoy, 6);
        //FrontDrive.whenPressed(new DriveSticks());
        //RevDrive = new JoystickButton(rightJoy, 5);
        //RevDrive.whenPressed(new DriveSticksReverse());
        
        shiftUp = new JoystickButton(rightJoy, 1);
        shiftUp.whenPressed(new ShiftUp());
        shiftUp.whenReleased(new ShiftDown());
        //shiftAuto = new JoystickButton(leftJoy, 1);
        //shiftAuto.whenPressed(new ShiftAuto());
        //shiftAuto.whenReleased(new ShiftDown());
        
        climbOnOff = new JoystickButton(buttonbox, 12);
        climbOnOff.whileHeld(new ClimbUp());
        
        intakeBackwardOff = new JoystickButton(buttonbox, 1);
        intakeBackwardOff.whileHeld(new FeedWheelsOutStop());
        intakeForwardOff = new JoystickButton(buttonbox, 2);
        intakeForwardOff.whileHeld(new FeedWheelsInStop());
        
        TestGear1 = new JoystickButton(leftJoy, 7);
        TestGear1.whenPressed(new GearPos1());
        
        TestGear2 = new JoystickButton(leftJoy, 8);
        TestGear2.whenPressed(new GearPos2());
        
        TestGear3 = new JoystickButton(leftJoy, 9);
        TestGear3.whenPressed(new GearPos3());
        
        TestGear4 = new JoystickButton(leftJoy, 10);
        TestGear4.whenPressed(new GearPos4());
        
        TestGear5 = new JoystickButton(leftJoy, 11);
        TestGear5.whenPressed(new GearPos5());
        
        Gear1 = new JoystickButton(buttonbox, 6);
        Gear1.whenPressed(new GearPos1());
        
        Gear2 = new JoystickButton(buttonbox, 5);
        Gear2.whenPressed(new GearPos2());
        
        Gear3 = new JoystickButton(buttonbox, 16);
        Gear3.whenPressed(new GearPos3());
        
        Gear4 = new JoystickButton(buttonbox, 4);
        Gear4.whenPressed(new GearPos4());
        
        Gear5 = new JoystickButton(buttonbox, 9);
        Gear5.whenPressed(new GearPos5());
        
        autoA = new JoystickButton(buttonbox, 13);
        autoB = new JoystickButton(buttonbox, 14);
        autoC = new JoystickButton(buttonbox, 15);

        // SmartDashboard Buttons
        
        SmartDashboard.putData("DriveSticks", new DriveSticks());
        SmartDashboard.putData("ClimbUp", new ClimbUp());
        SmartDashboard.putData("ClimbStop", new ClimbStop());
        SmartDashboard.putData("GearPos1", new GearPos1());
        SmartDashboard.putData("GearPos2", new GearPos2());
        SmartDashboard.putData("Gear Position 3", new GearPos3());
        SmartDashboard.putData("GearPos4", new GearPos4());
        SmartDashboard.putData("GearPos5", new GearPos5());
        SmartDashboard.putData("FeedWheelsInStop", new FeedWheelsInStop());
        SmartDashboard.putData("DriveSticks", new DriveSticks());
        SmartDashboard.putData("DriveSticksReverse", new DriveSticksReverse());
        

        // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
    }

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=FUNCTIONS
    public mhJoystick getLeftJoy() {
        return leftJoy;
    }

    public mhJoystick getRightJoy() {
        return rightJoy;
    }

    public Joystick getButtonBox() {
        return buttonbox;
    }


    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=FUNCTIONS
}
