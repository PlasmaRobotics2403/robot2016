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
    	//catapult.reverseSensor(true);
    	isReadyToFire = true;
    	home = -catapult.getPulseWidthPosition();
    	state = ShootingState.WAIT_FOR_INPUT;
	}
	
	/**
	 * Publishes various values to smart dashboard for debugging purposes
	 * 
	 * @author Nic A
	 */
	public void publishData(){
		SmartDashboard.putNumber("Encoder", -catapult.getPulseWidthPosition());
		SmartDashboard.putNumber("Speed", catapult.getSpeed());
		SmartDashboard.putNumber("Error", catapult.getError());
		SmartDashboard.putNumber("Home", home);
		SmartDashboard.putBoolean("Ready to fire", isReadyToFire);
	}
	
	/**
	 * Extends shooter outward to shoot ball
	 * 
	 * @param speed - Motor speed of shoot (0 to 1)
	 * @param distance - Distance that motor rotates in encoder counts
	 * @return True if shoot is completed, false otherwise
	 * 
	 * @author Nic A
	 */
	public boolean shoot(double speed, double distance){
		catapult.changeControlMode(TalonControlMode.PercentVbus);
		catapult.setForwardSoftLimit((distance + home)/4096);
		if(isReadyToFire){
			isReadyToFire = false;
			home = -catapult.getPulseWidthPosition();
		}
		if(-catapult.getPulseWidthPosition() < distance + home){
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
	 * @param distance - Distance that motor rotates in encoder counts to shoot
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
		}
	}
	
}
