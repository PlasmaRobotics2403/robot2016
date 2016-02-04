package org.usfirst.frc.team2403.robot.joystick;

public class PlasmaJoystick{
	
	public PlasmaButton A;
	public PlasmaButton B;
	public PlasmaButton X;
	public PlasmaButton Y;
	public PlasmaButton LB;
	public PlasmaButton RB;
	public PlasmaButton BACK;
	public PlasmaButton START;
	public PlasmaButton L3;
	public PlasmaButton R3;
	
	public PlasmaAxis LeftX;
	public PlasmaAxis LeftY;
	public PlasmaAxis RightX;
	public PlasmaAxis RightY;
	
	public PlasmaDPad dPad;
	
	public PlasmaTrigger LT;
	public PlasmaTrigger RT;
	
	private final int port;
	
	/**
	 * Constructor of PlasmaJoystick
	 * 
	 * @param port - Port number of joystick
	 * 
	 * @author Nic A
	 */
	public PlasmaJoystick(int port) {
		this.port = port;
		
		A = new PlasmaButton(JoystickConstants.A_ID, port);
		B = new PlasmaButton(JoystickConstants.B_ID, port);
		X = new PlasmaButton(JoystickConstants.X_ID, port);
		Y = new PlasmaButton(JoystickConstants.Y_ID, port);
		LB = new PlasmaButton(JoystickConstants.LB_ID, port);
		RB = new PlasmaButton(JoystickConstants.RB_ID, port);
		BACK = new PlasmaButton(JoystickConstants.BACK_ID, port);
		START = new PlasmaButton(JoystickConstants.START_ID, port);
		L3 = new PlasmaButton(JoystickConstants.L3_ID, port);
		R3 = new PlasmaButton(JoystickConstants.R3_ID, port);
		
		LeftX = new PlasmaAxis(JoystickConstants.LEFTX_ID, port);
		LeftY = new PlasmaAxis(JoystickConstants.LEFTY_ID, port, true);
		RightX = new PlasmaAxis(JoystickConstants.RIGHTX_ID, port);
		RightY = new PlasmaAxis(JoystickConstants.RIGHTY_ID, port, true);
		
		dPad = new PlasmaDPad(JoystickConstants.DPAD_ID, port);
		
		LT = new PlasmaTrigger(JoystickConstants.LT_ID, port);
		RT = new PlasmaTrigger(JoystickConstants.RT_ID, port);
	}
	
	/**
	 * Returns joystick port
	 * 
	 * @return Joystick port
	 * 
	 * @author Nic A
	 */
	public int getPort(){
		return port;
	}
	
	
}
