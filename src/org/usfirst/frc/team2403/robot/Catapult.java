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
		SmartDashboard.putNumber("Encoder", catapult.getPulseWidthPosition());

		SmartDashboard.putNumber("Degrees", getDegrees());
		SmartDashboard.putNumber("Speed", catapult.getSpeed());
		SmartDashboard.putNumber("Error", catapult.getError());
		//SmartDashboard.putNumber("WTF", catapult.getReverseSoftLimit());
		SmartDashboard.putNumber("Home", home);
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
			home = getDegrees();
			limit = toEncoderTicks(home + distance);
			catapult.setReverseSoftLimit(toEncoderTicks(home + distance)/4096);
			catapult.setForwardSoftLimit(toEncoderTicks(home + distance)/4096);
		}
		if(catapult.getPulseWidthPosition() > limit){
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
		catapult.set(Constants.RELOAD_SPEED);
		if(-catapult.getPulseWidthPosition() < home + 100){
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
	 * @author Nic A
	 */
	public void cycleShoot(PlasmaButton button, double speed, double distance){
		switch(state){
			case WAIT_FOR_INPUT:
				rest();
				if(button.isOffToOn()){
					state = ShootingState.SHOOTING;
				}
				break;
			case SHOOTING:
				if(shoot(speed, distance)){
					state = ShootingState.RELOADING;
				}
				DriverStation.reportError("shoot\n", false);
				break;
			case RELOADING:
				if(reload()){
					state = ShootingState.RELOADED;
				}
				DriverStation.reportError("reload\n", false);
				break;
			case RELOADED:
				rest();
				if(!button.isPressed()){
					state = ShootingState.WAIT_FOR_INPUT;
				}
				break;
		}
	}
	
	private double getDegrees() {
		return catapult.getPulseWidthPosition() * Constants.DEGREES_PER_TICK;
	}
	
	private double toEncoderTicks(double degrees) {
		return degrees / Constants.DEGREES_PER_TICK;
	}
}
