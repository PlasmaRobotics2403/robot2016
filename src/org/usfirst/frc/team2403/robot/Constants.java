package org.usfirst.frc.team2403.robot;

public class Constants {

	/* PORT CONSTANTS */
	
	public static final int JOYSTICK1_PORT = 0;
	
	public static final int TALON_L_PORT = 1;
	public static final int TALON_L_SLAVE_PORT = 2;
	public static final int TALON_R_PORT = 4;
	public static final int TALON_R_SLAVE_PORT = 5;
	
	public static final int TALON_CATAPULT_PORT = 10;
	
	/* DRIVETRAIN CONSTANTS */
	
	public static final double maxSpeed = 0.4;
	public static final double maxTurn = 0.2;
	
	private Constants() {
		//This prevents java from doing funky stuff
	}

}
