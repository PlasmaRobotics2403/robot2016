package org.usfirst.frc.team2403.robot;

import org.usfirst.frc.team2403.robot.Intake.LiftHeight;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Autonomous {
	
	DriveTrain drive;
	AHRS navX;
	Catapult catapult;
	Intake intake;
	int stage;
	public boolean isDoingCheval;
	int chevalStage;
	
	public Autonomous(DriveTrain drive, Catapult catapult, Intake intake){
		this.drive = drive;
		this.navX = drive.navX;
		this.catapult = catapult;
		this.intake = intake;
		isDoingCheval = false;
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


public void mode8(){
	switch(stage){
		case 0:
			intake.manualRoller(.7);
			intake.manualLift(Intake.LiftHeight.ALL_DOWN);
			if(intake.getHeight() > .2){
				stage++;
			}
			break;
		case 1:
			intake.manualRoller(0);
			if(distanceDrive(170, .4, 0)){
				stage++;
			}
			break;
		case 2:
			drive.resetEncoders();
			intake.manualRoller(1);
			intake.manualLift(Intake.LiftHeight.ALL_UP);
			if(intake.getHeight() < 0.4){
				intake.manualRoller(0);
			}
			if(angleTurn(50, .3)){
				drive.resetEncoders();
				stage++;
			}
			break;
		case 3:
			if(distanceDrive(75,0.4,50)){
				drive.resetEncoders();
				stage++;
			}
			break;
		case 4:
			intake.manualLift(Intake.LiftHeight.ALL_DOWN);
			if(distanceDrive(15,-0.2,50)){
				drive.resetEncoders();
				stage++;
			}
			break;
		case 5:
			if(catapult.autoShoot(1, 110, intake)){
				stage ++;
			}
			break;
		default:
			break;
	}
}

public void mode9(){
	
}
	
public boolean autoCheval(){
		if(!isDoingCheval){
			chevalStage = 0;
			isDoingCheval = true;
			drive.resetEncoders();
		}
		SmartDashboard.putNumber("cheval stage", chevalStage);
		switch(chevalStage){
		case 0:
			if(distanceDrive(2, .4, 0)){
				chevalStage++;
			}
			return false;
		case 1:
			intake.manualLift(Intake.LiftHeight.ALL_DOWN);
			if(intake.getHeight() > .4){
				drive.resetEncoders();
				chevalStage++;
			}
			return false;
		case 2:
			intake.manualLift(Intake.LiftHeight.ALL_DOWN);
			if(distanceDrive(60, -.6, 0)){
				isDoingCheval = false;
				return true;
			}
			return false;
		default:
			return false;
		}
	}
	
	private boolean angleTurn(double angle, double speed){
		int direction = (int)((angle - navX.getYaw()) / Math.abs((angle - navX.getYaw())));
		if(Math.abs(angle - navX.getYaw()) > 5){
			drive.autonTankDrive(speed * direction, -speed * direction);
			return false;
		}
		else{
			drive.autonTankDrive(0, 0);
			return true;
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
