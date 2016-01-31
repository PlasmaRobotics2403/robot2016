package org.usfirst.frc.team2403.robot;

import edu.wpi.first.wpilibj.*;

public class PlasmaButton {
	
	
	private final int joystickPort;
	private final byte buttonNumByte;
	
	private DriverStation driveStation;
	
	private boolean isHeld = false;
	
	/**
	 * Constructor for button class
	 * @param buttonName - Button name
	 */
	public PlasmaButton(int buttonNum, int joystickPort) {
		this.joystickPort = joystickPort;
		this.buttonNumByte = (byte)buttonNum;
		driveStation = DriverStation.getInstance();
	}
	
	public boolean isPressed(){
		return driveStation.getStickButton(joystickPort, buttonNumByte);
	}
	
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
