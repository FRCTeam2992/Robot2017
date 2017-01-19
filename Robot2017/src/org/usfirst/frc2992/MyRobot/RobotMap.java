// RobotBuilder Version: 2.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.


package org.usfirst.frc2992.MyRobot;

// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS
import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.TalonSRX;

// END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

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

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS

    public static void init() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
        driveTrainL2Motor = new TalonSRX(1);
        LiveWindow.addActuator("DriveTrain", "L2Motor", (TalonSRX) driveTrainL2Motor);
        
        driveTrainL1Motor = new TalonSRX(0);
        LiveWindow.addActuator("DriveTrain", "L1Motor", (TalonSRX) driveTrainL1Motor);
        
        driveTrainL3Motor = new TalonSRX(2);
        LiveWindow.addActuator("DriveTrain", "L3Motor", (TalonSRX) driveTrainL3Motor);
        
        driveTrainR1Motor = new TalonSRX(3);
        LiveWindow.addActuator("DriveTrain", "R1Motor", (TalonSRX) driveTrainR1Motor);
        
        driveTrainR2Motor = new TalonSRX(4);
        LiveWindow.addActuator("DriveTrain", "R2Motor", (TalonSRX) driveTrainR2Motor);
        
        driveTrainR3Motor = new TalonSRX(5);
        LiveWindow.addActuator("DriveTrain", "R3Motor", (TalonSRX) driveTrainR3Motor);
        
        driveTrainLeftDriveEncoder = new Encoder(0, 1, false, EncodingType.k4X);
        LiveWindow.addSensor("DriveTrain", "LeftDriveEncoder", driveTrainLeftDriveEncoder);
        driveTrainLeftDriveEncoder.setDistancePerPulse(1.0);
        driveTrainLeftDriveEncoder.setPIDSourceType(PIDSourceType.kRate);
        driveTrainRightDriveEncoder = new Encoder(2, 3, false, EncodingType.k4X);
        LiveWindow.addSensor("DriveTrain", "RightDriveEncoder", driveTrainRightDriveEncoder);
        driveTrainRightDriveEncoder.setDistancePerPulse(1.0);
        driveTrainRightDriveEncoder.setPIDSourceType(PIDSourceType.kRate);
        driveTrainDriveShfitHL = new Solenoid(0, 0);
        LiveWindow.addActuator("DriveTrain", "DriveShfitHL", driveTrainDriveShfitHL);
        
        climberClimbAMotor = new Talon(6);
        LiveWindow.addActuator("Climber", "ClimbAMotor", (Talon) climberClimbAMotor);
        
        climberClimbBMotor = new Talon(7);
        LiveWindow.addActuator("Climber", "ClimbBMotor", (Talon) climberClimbBMotor);
        
        climberClimbColorSensor = new DigitalInput(5);
        LiveWindow.addSensor("Climber", "ClimbColorSensor", climberClimbColorSensor);
        
        intakeFeedLeftMotor = new Talon(8);
        LiveWindow.addActuator("Intake", "FeedLeftMotor", (Talon) intakeFeedLeftMotor);
        
        intakeFeedRightMotor = new Talon(9);
        LiveWindow.addActuator("Intake", "FeedRightMotor", (Talon) intakeFeedRightMotor);
        
        gearSystemLeftGearHold = new Servo(10);
        LiveWindow.addActuator("GearSystem", "LeftGearHold", gearSystemLeftGearHold);
        
        gearSystemRightGearHold = new Servo(11);
        LiveWindow.addActuator("GearSystem", "RightGearHold", gearSystemRightGearHold);
        
        gearSystemGearLoadedSensor = new DigitalInput(4);
        LiveWindow.addSensor("GearSystem", "GearLoadedSensor", gearSystemGearLoadedSensor);
        
        gearSystemRampUpDown = new Solenoid(0, 1);
        LiveWindow.addActuator("GearSystem", "RampUpDown", gearSystemRampUpDown);
        
        gearSystemDoorInOut = new Solenoid(0, 2);
        LiveWindow.addActuator("GearSystem", "DoorInOut", gearSystemDoorInOut);
        
        gearSystemGearSlideUpDown = new Solenoid(0, 3);
        LiveWindow.addActuator("GearSystem", "GearSlideUpDown", gearSystemGearSlideUpDown);
        

        // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
    }
}
