package org.usfirst.frc.team2403.robot;

import org.usfirst.frc.team2403.robot.Intake.LiftHeight;

import com.kauailabs.navx.frc.AHRS;

public class Autonomous {
	
	DriveTrain drive;
	AHRS navX;
	Intake intake;
	int stage;
	
	public Autonomous(DriveTrain drive, Intake intake){
		this.drive = drive;
		this.navX = drive.navX;
		this.intake = intake;
	}
	
	public void initAuton(){
		stage = 0;
		drive.resetEncoders();
		navX.zeroYaw();
	}
	
	public void mode1(){
		if(drive.getDistance() > 20){
			intake.manualRoller(0);
		}
		else{
			intake.manualRoller(.5);
		}
		distanceDrive(205, .7, 0);
		intake.manualLift(LiftHeight.ALL_UP);
	}
	
	public void mode2(){
		if(drive.getDistance() > 20){
			intake.manualRoller(0);
		}
		else{
			intake.manualRoller(.5);
		}
		distanceDrive(205, .9, 0);
		intake.manualLift(LiftHeight.ALL_UP);
	}
	
	public void mode3(){
		switch(stage){
		case 0:
			intake.manualRoller(1);
			intake.manualLift(Intake.LiftHeight.ALL_DOWN);
			if(intake.getHeight() > .4){
				stage++;
			}
			break;
		case 1:
			intake.manualRoller(0);
			intake.manualLift(Intake.LiftHeight.ALL_DOWN);
			distanceDrive(205, -.4, 0);
			break;
		}
		
	}
	
	public void mode4(){
		switch(stage){
		case 0:
			if(distanceDrive(44, -.4, 0)){
				stage++;
			}
			break;
		case 1:
			intake.manualLift(Intake.LiftHeight.ALL_DOWN);
			if(intake.getHeight() > .4){
				drive.resetEncoders();
				stage++;
			}
			break;
		case 2:
			intake.manualLift(Intake.LiftHeight.ALL_DOWN);
			distanceDrive(120, -.6, 0);
			break;
		}
	}
	
	public void mode5(){
		switch(stage){
		case 0:
			intake.manualRoller(1);
			intake.manualLift(Intake.LiftHeight.ALL_DOWN);
			if(intake.getHeight() > .4){
				stage++;
			}
			break;
		case 1:
			intake.manualRoller(0);
			intake.manualLift(Intake.LiftHeight.ALL_DOWN);
			distanceDrive(205, .4, 0);
			break;
		}
	}
	
	private boolean distanceDrive(double distance, double speed, double angle){
		if(Math.abs(drive.getDistance()) < Math.abs(distance)){
			drive.gyroStraight(speed, angle);
			return false;
		}
		else{
			drive.autonTankDrive(0, 0);
			return true;
		}
	}
	
}
