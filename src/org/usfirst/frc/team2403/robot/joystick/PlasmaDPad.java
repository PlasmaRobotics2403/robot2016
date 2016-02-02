package org.usfirst.frc.team2403.robot.joystick;

import edu.wpi.first.wpilibj.*;

public class PlasmaDPad {

	private final int joystickPort;
	
	private DriverStation driveStation;
	
	public PlasmaDPad(int joystickPort) {
		this.joystickPort = joystickPort;
		this.driveStation = DriverStation.getInstance();
	}
	
	public int getPOV(){
		return driveStation.getStickPOV(joystickPort, 0);
	}

}
