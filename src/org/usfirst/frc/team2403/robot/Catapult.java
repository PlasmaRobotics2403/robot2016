package org.usfirst.frc.team2403.robot;

import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.CANTalon.FeedbackDevice;
import edu.wpi.first.wpilibj.CANTalon.TalonControlMode;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Catapult {
	
	private CANTalon catapult;
	private boolean isReadyToFire;
	private double home;
	private int cyclePosition;
	
	public Catapult(int port) {
    	catapult = new CANTalon(port);
    	catapult.setFeedbackDevice(FeedbackDevice.CtreMagEncoder_Relative);
    	catapult.configNominalOutputVoltage(0, 0);
    	catapult.configPeakOutputVoltage(12, -12);
    	isReadyToFire = true;
    	home = catapult.getPulseWidthPosition();
    	cyclePosition = 0;
	}
	
	public void publishData(){
		SmartDashboard.putNumber("Encoder", catapult.getPulseWidthPosition());
		SmartDashboard.putNumber("Speed", catapult.getSpeed());
		SmartDashboard.putNumber("Error", catapult.getError());
		SmartDashboard.putNumber("Home", home);
		SmartDashboard.putBoolean("Ready to fire", isReadyToFire);
	}
	
	public boolean shoot(double speed, double distance){
		catapult.changeControlMode(TalonControlMode.PercentVbus);
		catapult.setForwardSoftLimit((distance + home)/4096);
		if(isReadyToFire){
			isReadyToFire = false;
			home = catapult.getPulseWidthPosition();
		}
		if(catapult.getPulseWidthPosition() < distance + home){
			catapult.set(speed);
			return false;
		}
		else{
			catapult.set(0);
			return true;
		}
		
		
	}
	
	public boolean reload(){
		catapult.changeControlMode(TalonControlMode.Speed);
		catapult.setProfile(0);
		catapult.set(-10);
		if(catapult.getPulseWidthPosition() < home + 100){
			isReadyToFire = true;
			return true;
		}
		else{
			return false;
		}
	}
	
	public boolean cycleShoot(double speed, double distance){
		switch(cyclePosition){
			case 0:
				if(shoot(speed, distance)){
					cyclePosition = 1;
				}
				break;
			case 1:
				if(reload()){
					cyclePosition = 0;
					return true;
				}
				break;
		}
		return false;
	}
	
	public void rest(){
		catapult.changeControlMode(TalonControlMode.PercentVbus);
		catapult.set(0);
	}
	
	

}
