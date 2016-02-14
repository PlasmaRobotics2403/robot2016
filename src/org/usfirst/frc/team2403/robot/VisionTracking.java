package org.usfirst.frc.team2403.robot;

import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class VisionTracking {

	NetworkTable table;
	double[] defaultValue;
	double[] properties  = {-1, -1, -1, -1, -1, -1};
	String[] keys = {"centerY", "centerX", "width", "solidity", "area", "height"};
	
	public VisionTracking() {
		table = NetworkTable.getTable("GRIP/Target");
		defaultValue = new double[0];
	}
	
	public void update(){
		for(int i = 0; i < keys.length; i++){
			double[] value = table.getNumberArray(keys[i], defaultValue);
			if(value.length == 1){
				SmartDashboard.putNumber(keys[i], value[0]);
				properties[i] = value[0];
			}
			else{
				SmartDashboard.putNumber(keys[i], -1.0);
				properties[i] = -1;
			}
		}
	}
	
	public int keyID(String key){
		for(int i = 0; i < keys.length; i++){
			if(keys[i].equals(key)){
				return i;
			}
		}
		return 0;
	}
	
	public void turnToTarget(DriveTrain drive){
		if(properties[keyID("centerX")] != -1){
			double error = Constants.PICTURE_WIDTH/2 - properties[keyID("centerX")];
			if(error > 10){
				drive.autonTankDrive(-.2 - (error * .001), .2 + (error * .001));
			}
			else if(error < -10){
				drive.autonTankDrive(.2 + (-error * .001), -0.2 - (-error * .001));
			}
			else{
				drive.autonTankDrive(0, 0);
			}
		}
	}

}
