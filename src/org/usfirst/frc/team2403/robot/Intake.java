package org.usfirst.frc.team2403.robot;

import org.usfirst.frc.team2403.robot.joystick.*;
import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.CANTalon.FeedbackDevice;
import edu.wpi.first.wpilibj.CANTalon.TalonControlMode;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Intake {
	
	private CANTalon lift;
	private CANTalon roller;
	private IntakePositions position;
	
	private enum IntakePositions{
		ALL_DOWN(0), PICKUP_BALL(1), ALL_UP(2);
		
		final int id;
		
		IntakePositions(int id){
			this.id = id;
		}
		
		int getID(){
			return id;
		}
	}
	
	/**
	 * Constructor for intake object
	 * 
	 * @param liftID - Talon ID of lift motor
	 * @param rollerID - Talon ID of roller motor
	 * @author Nic A
	 */
	public Intake(int liftID, int rollerID) {
		lift = new CANTalon(liftID);
		roller = new CANTalon(rollerID);
		position = IntakePositions.ALL_DOWN;
		
		lift.changeControlMode(TalonControlMode.PercentVbus);
		lift.configNominalOutputVoltage(0, 0);
		lift.configPeakOutputVoltage(12, -12);
		lift.setFeedbackDevice(FeedbackDevice.CtreMagEncoder_Relative);
		lift.setPosition(0);
	}
	
	public void display(){
		SmartDashboard.putNumber("Intake", lift.getPosition());
	}
	
	/**
	 * Runs rollers based on two button inputs
	 * @param in - Button that makes rollers pull ball in
	 * @param out - Button that makes rollers push ball out
	 * @author Nic A
	 */
	public void runRollers(PlasmaButton in, PlasmaButton out){
		if(in.isPressed()){
			roller.set(Constants.ROLLER_SPEED);
		}
		else if(out.isPressed()){
			roller.set(-Constants.ROLLER_SPEED);
		}
		else{
			roller.set(Constants.ROLLER_STOPPED);
		}
	}
	
	/**
	 * Runs lift based on two trigger inputs
	 * @param up - Trigger that makes lift go up
	 * @param out - Trigger that makes lift go down
	 * @author Nic A
	 */
	public void runLift(PlasmaTrigger up, PlasmaTrigger down){
		if(up.isPressed()){
			lift.changeControlMode(TalonControlMode.PercentVbus);
			lift.enableForwardSoftLimit(false);
			lift.enableReverseSoftLimit(false);
			lift.set(up.getFilteredAxis() * Constants.MAX_LIFT_SPEED);
		}
		else if(down.isPressed()){
			lift.changeControlMode(TalonControlMode.PercentVbus);
			lift.enableForwardSoftLimit(false);
			lift.enableReverseSoftLimit(false);
			lift.set(-down.getFilteredAxis() * Constants.MAX_LIFT_SPEED);
		}
		else{
			lift.changeControlMode(TalonControlMode.Speed);
			lift.setForwardSoftLimit(lift.getPosition());
			lift.setReverseSoftLimit(lift.getPosition());
			lift.enableForwardSoftLimit(true);
			lift.enableReverseSoftLimit(true);
			lift.set(Constants.LIFT_STOPPED);
		}
	}
	
}
