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

import java.util.ArrayList;

import org.usfirst.frc2992.Robot.Robot.RobotState;

import com.ctre.CANTalon;
import com.ctre.CtreCanMap;
import com.kauailabs.navx.frc.AHRS;
import com.kauailabs.navx.frc.Quaternion;

// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS
import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.TalonSRX;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.VictorSP;
// END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.livewindow.LiveWindowSendable;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    public static SpeedController driveTrainL2Motor;
    public static SpeedController driveTrainL1Motor;
    public static SpeedController driveTrainL3Motor;
    public static SpeedController driveTrainR1Motor;
    public static SpeedController driveTrainR2Motor;
    public static SpeedController driveTrainR3Motor;
    
    public static ArrayList<SpeedController>leftmotors;
    public static ArrayList<SpeedController>rightmotors;
  
    public static Encoder driveTrainLeftDriveEncoder;
    public static Encoder driveTrainRightDriveEncoder;
    public static Solenoid driveTrainDriveShfitHL;
   
    public static SpeedController climberClimbAMotor;
    public static SpeedController climberClimbBMotor;
    public static DigitalInput climberClimbColorSensor;
    
    public static SpeedController intakeFeedLeftMotor;
    public static SpeedController intakeFeedRightMotor;
   
    public static Servo gearSystemLeftGearHold;
    public static Servo gearSystemRightGearHold;
  
    public static DigitalInput gearSystemGearLoadedSensor;
    public static Solenoid gearSystemRampUpDown;
    public static Solenoid gearSystemDoorInOut;
    public static Solenoid gearSystemGearSlideUpDown;
    public static Solenoid gearSystemTest;
    
    public static AHRS navx;
    
    public static Robot.RobotState state;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS

    public static double adjustment = 0;
    
    public static void init() {
    	state = Robot.state;
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
    	
    	//gearSystemTest = new Solenoid(1);
    	
    	navx = new AHRS(SerialPort.Port.kUSB);

    	navx.reset();
    	navx.resetDisplacement();
    	navx.setAngleAdjustment(adjustment);
    	navx.zeroYaw();
    	
    	leftmotors = new ArrayList<SpeedController>();
    	rightmotors = new ArrayList<SpeedController>();
    	
    if(state == RobotState.Robot){
        leftmotors.add(driveTrainL2Motor = new CANTalon(1));
        LiveWindow.addActuator("DriveTrain", "L2Motor", (LiveWindowSendable) driveTrainL2Motor);
        driveTrainL2Motor.setInverted(true);
        
        leftmotors.add(driveTrainL1Motor = new CANTalon(3));
        LiveWindow.addActuator("DriveTrain", "L1Motor", (LiveWindowSendable) driveTrainL1Motor);
        driveTrainL1Motor.setInverted(true);
        
        leftmotors.add(driveTrainL3Motor = new CANTalon(6));
        LiveWindow.addActuator("DriveTrain", "L3Motor", (LiveWindowSendable) driveTrainL3Motor);
        driveTrainL3Motor.setInverted(true);
        
        rightmotors.add(driveTrainR1Motor = new CANTalon(2));
        LiveWindow.addActuator("DriveTrain", "R1Motor", (LiveWindowSendable) driveTrainR1Motor);
        driveTrainR1Motor.setInverted(true);
        
        rightmotors.add(driveTrainR2Motor = new CANTalon(4));
        LiveWindow.addActuator("DriveTrain", "R2Motor", (LiveWindowSendable) driveTrainR2Motor);
        driveTrainR2Motor.setInverted(true);
        
        rightmotors.add(driveTrainR3Motor = new CANTalon(7));
        LiveWindow.addActuator("DriveTrain", "R3Motor", (LiveWindowSendable) driveTrainR3Motor);
        driveTrainR3Motor.setInverted(true);
        
        climberClimbAMotor = new CANTalon(5);
        LiveWindow.addActuator("Climber", "ClimbAMotor", (LiveWindowSendable) climberClimbAMotor);
        
    }
    if(state == RobotState.Test){
        leftmotors.add(driveTrainL2Motor = new Victor(1));
        LiveWindow.addActuator("DriveTrain", "L2Motor", (LiveWindowSendable) driveTrainL2Motor);
        driveTrainL2Motor.setInverted(true);
        
        leftmotors.add(driveTrainL1Motor = new Victor(3));
        LiveWindow.addActuator("DriveTrain", "L1Motor", (LiveWindowSendable) driveTrainL1Motor);
        driveTrainL1Motor.setInverted(true);
        
        leftmotors.add(driveTrainL3Motor = new Victor(6));
        LiveWindow.addActuator("DriveTrain", "L3Motor", (LiveWindowSendable) driveTrainL3Motor);
        driveTrainL3Motor.setInverted(true);
        
        rightmotors.add(driveTrainR1Motor = new Victor(2));
        LiveWindow.addActuator("DriveTrain", "R1Motor", (LiveWindowSendable) driveTrainR1Motor);
        driveTrainR1Motor.setInverted(true);
        
        rightmotors.add(driveTrainR2Motor = new Victor(4));
        LiveWindow.addActuator("DriveTrain", "R2Motor", (LiveWindowSendable) driveTrainR2Motor);
        driveTrainR2Motor.setInverted(true);
        
        rightmotors.add(driveTrainR3Motor = new Victor(7));
        LiveWindow.addActuator("DriveTrain", "R3Motor", (LiveWindowSendable) driveTrainR3Motor);
        driveTrainR3Motor.setInverted(true);
        
        climberClimbAMotor = new Victor(5);
        LiveWindow.addActuator("Climber", "ClimbAMotor", (LiveWindowSendable) climberClimbAMotor);
        
    }
        
        driveTrainLeftDriveEncoder = new Encoder(0, 1, true, EncodingType.k4X);
        LiveWindow.addSensor("DriveTrain", "LeftDriveEncoder", driveTrainLeftDriveEncoder);
        driveTrainLeftDriveEncoder.setDistancePerPulse((6* 3.14 * 24) / (128 * 60 * 3));
        driveTrainLeftDriveEncoder.setPIDSourceType(PIDSourceType.kDisplacement);
        driveTrainRightDriveEncoder = new Encoder(2, 3, true, EncodingType.k4X);
        LiveWindow.addSensor("DriveTrain", "RightDriveEncoder", driveTrainRightDriveEncoder);
        driveTrainRightDriveEncoder.setDistancePerPulse((6* 3.14 * 24) / (128 * 60 * 3));
        driveTrainRightDriveEncoder.setPIDSourceType(PIDSourceType.kDisplacement);
        
        driveTrainDriveShfitHL = new Solenoid(0);
        LiveWindow.addActuator("DriveTrain", "DriveShfitHL", driveTrainDriveShfitHL);
        
       
        climberClimbBMotor = new Talon(12);//change back to 2 // 12 on practice bot
        LiveWindow.addActuator("Climber", "ClimbBMotor", (Talon) climberClimbBMotor);
        
        //climberClimbColorSensor = new DigitalInput(5);
        //LiveWindow.addSensor("Climber", "ClimbColorSensor", climberClimbColorSensor);
        
        intakeFeedLeftMotor = new VictorSP(9);//change back to 0 // 9 on practice bot
        LiveWindow.addActuator("Intake", "FeedLeftMotor", (VictorSP) intakeFeedLeftMotor);
        
        intakeFeedRightMotor = new VictorSP(8);//change back to 1 // 8 on practice bot
        LiveWindow.addActuator("Intake", "FeedRightMotor", (VictorSP) intakeFeedRightMotor);
        
        gearSystemLeftGearHold = new Servo(0);
        gearSystemLeftGearHold.setBounds(2.1, 1.5, 1.5, 1.5, 0.9);
        LiveWindow.addActuator("GearSystem", "LeftGearHold", gearSystemLeftGearHold);
        
        gearSystemRightGearHold = new Servo(11);
        gearSystemRightGearHold.setBounds(2.1, 1.5, 1.5, 1.5, 0.9);
        LiveWindow.addActuator("GearSystem", "RightGearHold", gearSystemRightGearHold);
        
        //gearSystemGearLoadedSensor = new DigitalInput(4);
        //LiveWindow.addSensor("GearSystem", "GearLoadedSensor", gearSystemGearLoadedSensor);
        
        gearSystemRampUpDown = new Solenoid(1);
        LiveWindow.addActuator("GearSystem", "RampUpDown-1", gearSystemRampUpDown);
        
        gearSystemDoorInOut = new Solenoid(2);
        LiveWindow.addActuator("GearSystem", "DoorInOut-2", gearSystemDoorInOut);
        
        gearSystemGearSlideUpDown = new Solenoid(3);
        LiveWindow.addActuator("GearSystem", "GearSlideUpDown-3", gearSystemGearSlideUpDown);
        
       
        

        // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
    }
}