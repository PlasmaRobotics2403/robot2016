package org.usfirst.frc.team2403.robot;

import org.usfirst.frc.team2403.robot.controllers.ControlPanel;

import edu.wpi.first.wpilibj.AnalogOutput;

public class RGBController {

	
	private AnalogOutput red;
	private AnalogOutput green;
	private AnalogOutput blue;
	private ControlPanel panel;
	
	public RGBController(ControlPanel panel){
		this.panel = panel;
		red = new AnalogOutput(0);
		green = new AnalogOutput(1);
		blue = new AnalogOutput(3);
	}
	
	public void controlRGB(){
		red.setVoltage(5 * panel.axis1.getTrueAxis());
		green.setVoltage(5 * panel.axis2.getTrueAxis());
		blue.setVoltage(5 * panel.axis3.getTrueAxis());
	}
	
	
}
