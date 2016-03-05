package org.usfirst.frc.team2403.robot.controllers;

import edu.wpi.first.wpilibj.*;

public class PlasmaButton {
	
	private final int joystickPort;
	private final byte buttonNumByte;
	
	private DriverStation driveStation;
	
	private boolean isHeld = false;
	
	/**
	 * Contructor for the button class
	 * 
	 * @param buttonNum - ID value of the button
	 * @param joystickPort - Joystick port that the button is on
	 * 
	 * @author Nic A
	 */
	public PlasmaButton(int buttonNum, int joystickPort) {
		this.joystickPort = joystickPort;
		this.buttonNumByte = (byte)buttonNum;
		driveStation = DriverStation.getInstance();
	}
	
	/**
	 * Check if button is pressed
	 * 
	 * @return True if button is pressed
	 * 
	 * @author Nic A
	 */
	public boolean isPressed(){
		return driveStation.getStickButton(joystickPort, buttonNumByte);
	}
	
	/**
	 * Checks if button has been switched from off to on
	 * 
	 * @return True once when button is toggled on
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
	 * Checks if button has been switched from on to off
	 * 
	 * @return True once when button is toggled off
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
	

	

}
