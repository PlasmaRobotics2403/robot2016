package org.usfirst.frc.team2403.robot;

import org.usfirst.frc.team2403.robot.joystick.PlasmaButton;
import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.CANTalon.*;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Catapult {
	
	/**
	 * Enum to describe actions of shooter
	 * 
	 * @author Nic A
	 */
	private enum ShootingState{
		WAIT_FOR_INPUT, SHOOTING, RELOADING, RELOADED;
	}
	
	private CANTalon catapult;
	private boolean isReadyToFire;
	private double home;
	private ShootingState state;
	private double limit;
	
	/**
	 * Constructor for catapult object
	 * 
	 * @param port - ID of shooter Talon
	 * 
	 * @author Nic A
	 */
	public Catapult(int port) {
    	catapult = new CANTalon(port);
    	catapult.setFeedbackDevice(FeedbackDevice.CtreMagEncoder_Relative);
    	catapult.configNominalOutputVoltage(0, 0);
    	catapult.configPeakOutputVoltage(12, -12);
    	isReadyToFire = true;
    	catapult.setPosition(0);
    	home = getDegrees();
    	state = ShootingState.WAIT_FOR_INPUT;
    	limit = 0;
	}
	
	/**
	 * Publishes various values to smart dashboard for debugging purposes
	 * 
	 * @author Nic A
	 */
	public void publishData(){
		SmartDashboard.putNumber("Shooter Angle", getDegrees());
		SmartDashboard.putBoolean("Ready to fire", isReadyToFire);
	}
	
	/**
	 * Extends shooter outward to shoot ball
	 * 
	 * @param speed - Motor speed of shoot (0 to 1)
	 * @param distance - Distance that motor rotates in degrees
	 * @return True if shoot is completed, false otherwise
	 * 
	 * @author Nic A
	 */
	public boolean shoot(double speed, double distance){
		catapult.changeControlMode(TalonControlMode.PercentVbus);
		if(isReadyToFire){
			isReadyToFire = false;
			catapult.setPosition(0);
			limit = distance/(Constants.DEGREES_PER_TICK * 4096);
			catapult.setForwardSoftLimit(limit);
			catapult.setReverseSoftLimit(limit);
			catapult.enableForwardSoftLimit(true);
			catapult.enableReverseSoftLimit(true);
		}
		if(catapult.getPosition() > limit){
			catapult.set(-speed);
			return false;
		}
		else{
			
			catapult.set(0);
			return true;
		}
	}
	
	/**
	 * Retracts shooter to reload
	 * @return True when reload is complete, false otherwise
	 * @author Nic A
	 */
	public boolean reload(){
		catapult.changeControlMode(TalonControlMode.Speed);
		catapult.setProfile(Constants.RELOAD_PROFILE);
		catapult.enableForwardSoftLimit(false);
		catapult.enableReverseSoftLimit(false);
		catapult.set(Constants.RELOAD_SPEED);
		if(catapult.getPosition() > -0.05){
			isReadyToFire = true;
			return true;
		}
		else{
			return false;
		}
	}
	
	/**
	 * Stops the shoot motor from moving
	 * @author Nic A
	 */
	public void rest(){
		catapult.changeControlMode(TalonControlMode.PercentVbus);
		catapult.set(Constants.REST_SPEED);
	}
	
	/**
	 * Handles shooting based on button input
	 * @param button - Button that shooter is controlled from
	 * @param speed - Speed of outward shoot
	 * @param distance - Distance that motor rotates in degrees to shoot
	 * @param intake - Intake object used to check if intake is clear of shooter
	 * @author Nic A
	 */
	public void cycleShoot(PlasmaButton button, double speed, double distance, Intake intake){
		switch(state){
			case WAIT_FOR_INPUT:
				rest();
				if(button.isOffToOn() && intake.isClearOfShoot()){
					state = ShootingState.SHOOTING;
				}
				break;
			case SHOOTING:
				if(shoot(speed, distance)){
					state = ShootingState.RELOADING;
				}
				break;
			case RELOADING:
				if(reload()){
					state = ShootingState.RELOADED;
				}
				break;
			case RELOADED:
				rest();
				if(!button.isPressed()){
					state = ShootingState.WAIT_FOR_INPUT;
				}
				break;
			default:
				DriverStation.reportError("error\n", false);
				break;
		}
	}
	
	/**
	 * Checks if catapult is ready to fire
	 * 
	 * @return true if shooter is ready to fire, false otherwise
	 */
	public boolean getIsReadyToFire(){
		return isReadyToFire;
	}
	
	/**
	 * Calculates motor angle in degrees
	 * @return Motor angle in degrees
	 * @author Nic A
	 */
	private double getDegrees() {
		return catapult.getPulseWidthPosition() * Constants.DEGREES_PER_TICK;
	}
	
}
