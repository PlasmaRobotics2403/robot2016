package org.usfirst.frc.team2403.robot;

import org.usfirst.frc.team2403.robot.joystick.*;
import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.CANTalon.FeedbackDevice;
import edu.wpi.first.wpilibj.CANTalon.TalonControlMode;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Intake {
	
	private CANTalon lift;
	private CANTalon roller;
	private LiftHeight position;
	
	private enum LiftHeight{
		ALL_UP(Constants.ALL_UP_POS), ALL_DOWN(Constants.ALL_DOWN_POS), LOAD_TO_SHOOT(Constants.LOAD_TO_SHOOT_POS), PICKUP_BALL(Constants.PICKUP_BALL_POS);
		
		private double position;
		
		LiftHeight(double position){
			this.position = position;
		}
		
		double getPosition(){
			return position;
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
		
		lift.changeControlMode(TalonControlMode.Position);
		lift.configNominalOutputVoltage(0, 0);
		lift.configPeakOutputVoltage(2, -2);
		lift.setFeedbackDevice(FeedbackDevice.CtreMagEncoder_Relative);
		lift.setPosition(((double)(lift.getPulseWidthPosition()) / 4096.0) - 0.236);
		position = LiftHeight.ALL_UP;
	}
	
	/**
	 * Publishes various values to smart dashboard for debugging purposes
	 * 
	 * @author Nic A
	 */
	public void publishData(){
		SmartDashboard.putNumber("Intake Position", lift.getPosition() * 360);
		SmartDashboard.putNumber("Abs", (double)(lift.getPulseWidthPosition()) / 4096.0);
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
	 * Controls position of lift: first press goes to primary location and repeated presses alternate between primary and secondary
	 * 
	 * @param up - Button that controls upper level positions 
	 * @param down - Button that controls lower level positions 
	 * @param catapult - Catapult object - used to check if shooter is clear of pickup
	 * @author Nic A
	 */
	public void liftControl(PlasmaButton up, PlasmaButton down, Catapult catapult){
		if(lift.getPosition() > .300){
			lift.configPeakOutputVoltage(3, -3);
		}
		else{
			lift.configPeakOutputVoltage(2, -2);
		}
		if(up.isOffToOn() && catapult.getIsReadyToFire()){
			position = (position == LiftHeight.LOAD_TO_SHOOT) ? LiftHeight.ALL_UP : LiftHeight.LOAD_TO_SHOOT;
		}
		else if(down.isOffToOn()){
			position = (position == LiftHeight.PICKUP_BALL) ? LiftHeight.ALL_DOWN : LiftHeight.PICKUP_BALL;
		}
		lift.set(position.getPosition());
	}
	
	/**
	 * Checks if intake arm is clear of shooter
	 * 
	 * @return true if arm is clear, false otherwise
	 */
	public boolean isClearOfShoot(){
		return (lift.getPosition() > .260 && (position == LiftHeight.ALL_DOWN || position == LiftHeight.PICKUP_BALL));
	}
	
}
