package org.usfirst.frc.team2403.robot;

import edu.wpi.first.wpilibj.*;

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
		
	}
	
	
	
	
}
