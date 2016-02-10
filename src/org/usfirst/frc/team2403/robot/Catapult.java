package org.usfirst.frc.team2403.robot;

import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.CANTalon.FeedbackDevice;
import edu.wpi.first.wpilibj.CANTalon.TalonControlMode;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Catapult {
	
	private CANTalon catapult;
	
	public Catapult(int port) {
    	catapult = new CANTalon(port);
    	catapult.setFeedbackDevice(FeedbackDevice.CtreMagEncoder_Absolute);
    	catapult.configNominalOutputVoltage(0, 0);
    	catapult.configPeakOutputVoltage(12, -12);
	}
	
	public void publishData(){
		SmartDashboard.putNumber("Encoder", catapult.getPulseWidthPosition());
		SmartDashboard.putNumber("Speed", catapult.getSpeed());
		SmartDashboard.putNumber("Error", catapult.getError());
	}
	
	public void shoot(double position){
		
		
	}
	
	public void reload(){
		catapult.changeControlMode(TalonControlMode.Speed);
		catapult.setProfile(0);
		catapult.set(50);
	}
	
}
