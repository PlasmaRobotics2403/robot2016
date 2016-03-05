package org.usfirst.frc.team2403.robot.controllers;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

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
	
	public void publishValues(){
		SmartDashboard.putBoolean("A", A.isPressed());
		SmartDashboard.putBoolean("B", B.isPressed());
		SmartDashboard.putBoolean("X", X.isPressed());
		SmartDashboard.putBoolean("Y", Y.isPressed());
		SmartDashboard.putBoolean("LB", LB.isPressed());
		SmartDashboard.putBoolean("RB", RB.isPressed());
		SmartDashboard.putBoolean("BACK", BACK.isPressed());
		SmartDashboard.putBoolean("START", START.isPressed());
		SmartDashboard.putBoolean("L3", L3.isPressed());
		SmartDashboard.putBoolean("R3", R3.isPressed());
		
		SmartDashboard.putBoolean("dpad0", 0 == dPad.getPOV());
		SmartDashboard.putBoolean("dpad45", 45 == dPad.getPOV());
		SmartDashboard.putBoolean("dpad90", 90 == dPad.getPOV());
		SmartDashboard.putBoolean("dpad135", 135 == dPad.getPOV());
		SmartDashboard.putBoolean("dpad180", 180 == dPad.getPOV());
		SmartDashboard.putBoolean("dpad225", 225 == dPad.getPOV());
		SmartDashboard.putBoolean("dpad270", 270 == dPad.getPOV());
		SmartDashboard.putBoolean("dpad315", 315 == dPad.getPOV());
		
		SmartDashboard.putNumber("leftY", LeftY.getFilteredAxis());
		SmartDashboard.putNumber("leftX", LeftX.getFilteredAxis());
		SmartDashboard.putNumber("rightY", RightY.getFilteredAxis());
		SmartDashboard.putNumber("rightX", RightX.getFilteredAxis());
		
		SmartDashboard.putNumber("LT", LT.getFilteredAxis());
		SmartDashboard.putNumber("RT", RT.getFilteredAxis());
	}
	
}
