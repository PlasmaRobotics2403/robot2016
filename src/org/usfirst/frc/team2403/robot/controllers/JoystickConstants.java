package org.usfirst.frc.team2403.robot.controllers;

public class JoystickConstants {

	/* AXIS CONSTANTS */
	
	public static final double deadBand = 0.15;
	
	/* TRIGGER CONSTANTS */
	
	public static final double triggerThreshold = 0.1;
	
	/* ID CONSTANTS */
	
	public static final int A_ID = 1;
	public static final int B_ID = 2;
	public static final int X_ID = 3;
	public static final int Y_ID = 4;
	public static final int LB_ID = 5;
	public static final int RB_ID = 6;
	public static final int BACK_ID = 7;
	public static final int START_ID = 8;
	public static final int L3_ID = 9;
	public static final int R3_ID = 10;
	
	public static final int LEFTX_ID = 0;
	public static final int LEFTY_ID = 1;
	public static final int RIGHTX_ID = 4;
	public static final int RIGHTY_ID = 5;
	
	public static final int DPAD_ID = 0;
	
	public static final int LT_ID = 2;
	public static final int RT_ID = 3;
	
	private JoystickConstants(){
		//This prevents java from doing funky stuff
	}

}
