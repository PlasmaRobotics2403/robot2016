package org.usfirst.frc.team2403.robot.controller;

import edu.wpi.first.wpilibj.*;

public class PlasmaAxis {
	
	private static final double deadBand = .1;
	
	private final int joystickPort;
	private final byte axisNumByte;
	private final int dirMultiplier;
	
	private DriverStation driveStation;
	
	public PlasmaAxis(int axisNum, int joystickPort, boolean isReverse) {
		this.joystickPort = joystickPort;
		this.axisNumByte = (byte)axisNum;
		this.dirMultiplier = (isReverse) ? -1 : 1;
		driveStation = DriverStation.getInstance();
	}
	
	public PlasmaAxis(int axisNum, int joystickPort) {
		this(axisNum, joystickPort, false);
	}
	
	public double getTrueAxis(){
		return driveStation.getStickAxis(joystickPort, axisNumByte) * dirMultiplier;
	}
	
	public double getFilteredAxis(){
		if(Math.abs(getTrueAxis()) > deadBand){
			return getTrueAxis();
		}
		else{
			return 0;
		}
	}
	

}
