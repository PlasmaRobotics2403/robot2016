package org.usfirst.frc.team2403.robot;

import edu.wpi.first.wpilibj.*;

public class PlasmaJoystick extends Joystick {
	
	/**
	 * Enum that converts descriptive name to joystick axis ID
	 * 
	 * @author Nic A 
	 */
	public enum Axis{
		LeftX(0), LeftY(1), LeftT(2), RightT(3), RightX(4), RightY(5);
		
		final int axisNum;
		
		/**
		 * Constructor for axis enum
		 * 
		 * @param axisNum - Axis ID
		 * 
		 * @author Nic A
		 */
		private Axis(int axisNum){
			this.axisNum = axisNum;
		}
		
		/**
		 * Returns integer value of enum
		 * 
		 * @return Value of enum
		 * 
		 * @author Nic A
		 */
		private int valueOf(){
			return axisNum;
		}
		
	}
	
	/**
	 * Constructor of PlasmaJoystick
	 * 
	 * @param port - Port number of joystick
	 * 
	 * @author Nic A
	 */
	public PlasmaJoystick(int port) {
		super(port);
	}
	
	public double getRawAxis(Axis axis){
		return getRawAxis(axis.valueOf());
	}
	
}
