package org.usfirst.frc.team2403.robot.controllers;

import edu.wpi.first.wpilibj.DriverStation;

public class PlasmaTrigger {
		
	private final int joystickPort;
	private final byte triggerNumByte;
	
	boolean isHeld = false;
	
	private DriverStation driveStation;

	/**
	 * Constructor for plasma trigger
	 * 
	 * @param triggerNum - ID number of trigger
	 * @param joystickPort - Port of joystick trigger is on
	 * 
	 * @author Nic A
	 */
	public PlasmaTrigger(int triggerNum, int joystickPort) {
		this.joystickPort = joystickPort;
		this.triggerNumByte = (byte)triggerNum;
		driveStation = DriverStation.getInstance();
	}
	
	/**
	 * Checks if trigger has been pressed past threshold
	 * 
	 * @return true if trigger value > threshold
	 * 
	 * @author Nic A
	 */
	public boolean isPressed(){
		return driveStation.getStickAxis(joystickPort, triggerNumByte) > JoystickConstants.triggerThreshold;
	}
	
	/**
	 * Checks if trigger has been toggled from off to on
	 * 
	 * @return True once when trigger goes from being off to being pressed
	 * 
	 * @author Nic A
	 */
	public boolean isOffToOn(){
		if(!isHeld && isPressed()){
			isHeld = true;
			return true;
		}
		else{
			isHeld = isPressed();
			return false;
		}
	}
	
	
	/**
	 * Checks if trigger has been toggled from on to off
	 * 
	 * @return True once when trigger goes from being pressed to off
	 * 
	 * @author Nic A
	 */
	public boolean isOnToOff(){
		if(isHeld && !isPressed()){
			isHeld = false;
			return true;
		}
		else{
			isHeld = isPressed();
			return false;
		}
	}
	
	/**
	 * Returns the raw value from trigger after reversal
	 * 
	 * @return Joystick value
	 * 
	 * @author Nic
	 */
	public double getTrueAxis(){
		return driveStation.getStickAxis(joystickPort, triggerNumByte);
	}
	
	/**
	 * Returns the value of the trigger after reversal and deadband calculations
	 * 
	 * @return Value of trigger axis if greater than threshold, 0 otherwise
	 * 
	 * @Author Nic A
	 */
	public double getFilteredAxis(){
		if(Math.abs(getTrueAxis()) > JoystickConstants.triggerThreshold){
			return getTrueAxis();
		}
		else{
			return 0;
		}
	}

}
