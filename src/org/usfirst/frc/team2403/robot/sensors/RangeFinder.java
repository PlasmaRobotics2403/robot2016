package org.usfirst.frc.team2403.robot.sensors;

import edu.wpi.first.wpilibj.AnalogInput;

public class RangeFinder extends AnalogInput {
	
	public RangeFinder(int port){
		super(port);
	}
	
	public double getRange(){
		return super.getAverageVoltage() * 1024.0; //((double)super.getVoltage()/1024.0) * 0.3937;
	}
	
}
