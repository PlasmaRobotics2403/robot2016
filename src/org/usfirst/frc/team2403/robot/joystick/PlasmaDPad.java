package org.usfirst.frc.team2403.robot.joystick;

import edu.wpi.first.wpilibj.*;

public class PlasmaDPad {

	private final int joystickPort;
	
	private DriverStation driveStation;
	
	/**
	 * Constructor for Plasma D-Pad
	 * 
	 * @param joystickPort - Port of joystick that d-pad is connected to
	 * 
	 * @author Nic A
	 */
	public PlasmaDPad(int joystickPort) {
		this.joystickPort = joystickPort;
		this.driveStation = DriverStation.getInstance();
	}
	
	/**
	 * Gets angle of d-pad
	 * 
	 * @return value from 0-360 degrees based on angle
	 * 
	 * @author Nic A
	 */
	public int getPOV(){
		return driveStation.getStickPOV(joystickPort, 0);
	}

}
