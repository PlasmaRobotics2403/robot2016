package org.usfirst.frc.team2403.robot.joystick;

import edu.wpi.first.wpilibj.DriverStation;

public class PlasmaTrigger {
	
	private static final double triggerThreshold = .1;
	
	private final int joystickPort;
	private final byte triggerNumByte;
	
	boolean isHeld = false;
	
	private DriverStation driveStation;

	/**
	 * Constructor for plasma trigger
	 * 
	 * @param triggerNum - 
	 * @param joystickPort
	 */
	public PlasmaTrigger(int triggerNum, int joystickPort) {
		this.joystickPort = joystickPort;
		this.triggerNumByte = (byte)triggerNum;
	}
	
	public boolean isPressed(){
		return driveStation.getStickAxis(joystickPort, triggerNumByte) > triggerThreshold;
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
	
	public double getTrueAxis(){
		return driveStation.getStickAxis(joystickPort, triggerNumByte);
	}
	
	public double getFilteredAxis(){
		if(Math.abs(getTrueAxis()) > triggerThreshold){
			return getTrueAxis();
		}
		else{
			return 0;
		}
	}

}
