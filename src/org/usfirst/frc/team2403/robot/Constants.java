package org.usfirst.frc.team2403.robot;

public class Constants {

	/* PORT CONSTANTS */
	
	public static final int JOYSTICK1_PORT = 0;
	
	public static final int TALON_L_PORT = 1;
	public static final int TALON_L_SLAVE_PORT = 2;
	public static final int TALON_R_PORT = 4;
	public static final int TALON_R_SLAVE_PORT = 3;
	
	public static final int TALON_CATAPULT_PORT = 8;
	
	public static final int TALON_LIFT_PORT = 6;
	public static final int TALON_ROLLER_PORT = 5;
	
	/* DRIVETRAIN CONSTANTS */
	
	public static final double MAX_SPEED = 1;
	public static final double MAX_TURN = 1;
	
	/* CATAPULT CONSTANTS */
	
	public static final double DEGREES_PER_TICK = -0.0127840909; // -1 * 360 / (4096 * 5 * (22/16))
	
	public static final double RELOAD_SPEED = 20;
	public static final double REST_SPEED = 0;
	
	public static final int RELOAD_PROFILE = 0;
	public static final double[] RELOAD_PID = {.3, .001, 4};
	
	/* VISION TRACKING CONSTANTS */
	
	public static final double PICTURE_WIDTH = 320;
	public static final double PICTURE_HEIGHT = 240;
	
	/* INTAKE CONSTANTS */
	
	public static final double ROLLER_SPEED = .75;
	public static final double ROLLER_STOPPED = 0;
	public static final double MAX_LIFT_SPEED = .5;
	public static final double LIFT_STOPPED = 0;
	
	public static final double ALL_UP_POS = 0;
	public static final double ALL_DOWN_POS = .500;
	public static final double LOAD_TO_SHOOT_POS = 0.115;
	public static final double PICKUP_BALL_POS = 0.430;
	
	private Constants() {
		//This prevents java from doing funky stuff
	}

}
