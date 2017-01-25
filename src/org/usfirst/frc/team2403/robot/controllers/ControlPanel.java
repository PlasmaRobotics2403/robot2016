package org.usfirst.frc.team2403.robot.controllers;

import edu.wpi.first.wpilibj.hal.HAL;
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
	
	private int autonMode;
	
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
		
		autonMode = 9;
		
		SmartDashboard.putNumber("auton", autonMode);
	}
	
	public int getPort(){
		return port;
	}
	
	public int getSelectedMode(){
		return autonMode;
	}
	
	public void selectAutonMode(){
		/*
		autonMode = (button1.isPressed() ? 1 : autonMode);
		autonMode = (button2.isPressed() ? 2 : autonMode);
		autonMode = (button3.isPressed() ? 3 : autonMode);
		autonMode = (button4.isPressed() ? 4 : autonMode);
		autonMode = (button5.isPressed() ? 5 : autonMode);
		autonMode = (button6.isPressed() ? 6 : autonMode);
		autonMode = (button7.isPressed() ? 7 : autonMode);
		autonMode = (button8.isPressed() ? 8 : autonMode);
		autonMode = (button9.isPressed() ? 9 : autonMode);
		*/
		//SmartDashboard.putNumber("auton", autonMode);

		autonMode = (int)SmartDashboard.getNumber("auton", -1);
		HAL.setJoystickOutputs((byte)port, ControlPanelConstants.DISPLAY_VALUES[autonMode], (short)0, (short)0);
		//DriverStation.reportError(getSelectedMode() + "", false);
	}
	
	public void lockSelection(){
		HAL.setJoystickOutputs((byte)port, ControlPanelConstants.DISPLAY_VALUES[autonMode] | ControlPanelConstants.DECIMAL, (short)0, (short)0);
	}
	
}
