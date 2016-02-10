package org.usfirst.frc.team2403.robot;

import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class VisionTracking {

	NetworkTable table;
	double[] defaultValue;
	
	public VisionTracking() {
		table = NetworkTable.getTable("GRIP/Target");
		defaultValue = new double[0];
	}
	
	public void update(){
		double[] areas = table.getNumberArray("area", defaultValue);
		if(areas.length == 1){
			SmartDashboard.putNumber("test", areas[0]);
		}
		else{
			SmartDashboard.putNumber("test", -1.0);
		}
	}

}
