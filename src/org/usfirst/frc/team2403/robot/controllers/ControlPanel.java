package org.usfirst.frc.team2403.robot.controllers;

import edu.wpi.first.wpilibj.communication.FRCNetworkCommunicationsLibrary;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class ControlPanel {
	
	public PlasmaButton button1;
	public PlasmaButton button2;
	public PlasmaButton button3;
	public PlasmaButton button4;
	public PlasmaButton button5;
	public PlasmaButton button6;
	public PlasmaButton button7;
	public PlasmaButton button8;
	public PlasmaButton button9;
	
	public PlasmaButton toggleSwitch;
	
	public PlasmaAxis axis1;
	public PlasmaAxis axis2;
	public PlasmaAxis axis3;
	
	private final int port;
	
	public ControlPanel(int port) {
		
		this.port = port;
		
		button1 = new PlasmaButton(ControlPanelConstants.ID_1, port);
		button2 = new PlasmaButton(ControlPanelConstants.ID_2, port);
		button3 = new PlasmaButton(ControlPanelConstants.ID_3, port);
		button4 = new PlasmaButton(ControlPanelConstants.ID_4, port);
		button5 = new PlasmaButton(ControlPanelConstants.ID_5, port);
		button6 = new PlasmaButton(ControlPanelConstants.ID_6, port);
		button7 = new PlasmaButton(ControlPanelConstants.ID_7, port);
		button8 = new PlasmaButton(ControlPanelConstants.ID_8, port);
		button9 = new PlasmaButton(ControlPanelConstants.ID_9, port);
		toggleSwitch = new PlasmaButton(ControlPanelConstants.TOGGLE_ID, port);
		
		axis1 = new PlasmaAxis(ControlPanelConstants.AXIS_1_ID, port);
		axis2 = new PlasmaAxis(ControlPanelConstants.AXIS_2_ID, port);
		axis3 = new PlasmaAxis(ControlPanelConstants.AXIS_3_ID, port);
	}
	
	public int getPort(){
		return port;
	}
	
	int number = 0;
	public void set7Seg(){
		number = (button1.isPressed() ? 1 : number);
		number = (button2.isPressed() ? 2 : number);
		number = (button3.isPressed() ? 3 : number);
		number = (button4.isPressed() ? 4 : number);
		number = (button5.isPressed() ? 5 : number);
		number = (button6.isPressed() ? 6 : number);
		number = (button7.isPressed() ? 7 : number);
		number = (button8.isPressed() ? 8 : number);
		number = (button9.isPressed() ? 9 : number);
		number = (toggleSwitch.isPressed() ? 0 : number);
		FRCNetworkCommunicationsLibrary.HALSetJoystickOutputs((byte)port, ControlPanelConstants.DISPLAY_VALUES[number], (short)0, (short)0);
		
		SmartDashboard.putNumber("Axis 1", axis1.getTrueAxis());
		SmartDashboard.putNumber("Axis 2", axis2.getTrueAxis());
		SmartDashboard.putNumber("Axis 3", axis3.getTrueAxis());
	}

}
