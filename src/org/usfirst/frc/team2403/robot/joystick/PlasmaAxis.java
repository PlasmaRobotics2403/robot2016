package org.usfirst.frc.team2403.robot.joystick;

import edu.wpi.first.wpilibj.*;

public class PlasmaAxis {
	
	private static final double deadBand = .1;
	
	private final int joystickPort;
	private final byte axisNumByte;
	private final int dirMultiplier;
	
	private DriverStation driveStation;
	
	/**
	 * Constructor for plasma axis
	 * 
	 * @param axisNum - ID number of axis
	 * @param joystickPort - Port of joystick that the axis is on
	 * @param isReverse - True if axis input is reversed 
	 * 
	 * @author Nic A
	 */
	public PlasmaAxis(int axisNum, int joystickPort, boolean isReverse) {
		this.joystickPort = joystickPort;
		this.axisNumByte = (byte)axisNum;
		this.dirMultiplier = (isReverse) ? -1 : 1;
		driveStation = DriverStation.getInstance();
	}
	
	/**
	 * Constructor for plasma axis
	 * 
	 * @param axisNum - ID number of axis
	 * @param joystickPort - Port of joystick that the axis is on
	 * 
	 * @author Nic A
	 */
	public PlasmaAxis(int axisNum, int joystickPort) {
		this(axisNum, joystickPort, false);
	}
	
	/**
	 * Returns the raw value from joystick after reversal
	 * 
	 * @return Joystick value
	 * 
	 * @author Nic A
	 */
	public double getTrueAxis(){
		return driveStation.getStickAxis(joystickPort, axisNumByte) * dirMultiplier;
	}
	
	/**
	 * Returns the value of the joystick after reversal and deadband calculations
	 * 
	 * @return Joystick value after deadband
	 * 
	 * @author Nic A
	 */
	public double getFilteredAxis(){
		if(Math.abs(getTrueAxis()) > deadBand){
			return getTrueAxis();
		}
		else{
			return 0;
		}
	}
	

}
