package org.usfirst.frc.team2403.robot.controller;

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
		
		A = new PlasmaButton(1, port);
		B = new PlasmaButton(2, port);
		X = new PlasmaButton(3, port);
		Y = new PlasmaButton(4, port);
		LB = new PlasmaButton(5, port);
		RB = new PlasmaButton(6, port);
		BACK = new PlasmaButton(7, port);
		START = new PlasmaButton(8, port);
		L3 = new PlasmaButton(9, port);
		R3 = new PlasmaButton(10, port);
		
		LeftX = new PlasmaAxis(0, port);
		LeftY = new PlasmaAxis(1, port, true);
		RightX = new PlasmaAxis(4, port);
		RightY = new PlasmaAxis(5, port, true);
		
		dPad = new PlasmaDPad(port);
		
		LT = new PlasmaTrigger(2, port);
		RT = new PlasmaTrigger(3, port);
	}
	
	
	
	
}
