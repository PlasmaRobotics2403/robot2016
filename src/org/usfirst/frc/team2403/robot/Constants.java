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
	
	public static final double MAX_SPEED = 0.8;
	public static final double MAX_TURN = 0.6;
	
	/* CATAPULT CONSTANTS */
	
	public static final double RELOAD_SPEED = 20;
	public static final double REST_SPEED = 0;
	
	public static final int RELOAD_PROFILE = 0;
	public static final double[] RELOAD_PID = {.3, .001, 4};
	
	/* VISION TRACKING CONSTANTS */
	
	public static final double PICTURE_WIDTH = 320;
	public static final double PICTURE_HEIGHT = 240;
	
	private Constants() {
		//This prevents java from doing funky stuff
	}

}
