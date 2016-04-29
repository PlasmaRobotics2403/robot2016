package org.usfirst.frc.team2403.robot;

import org.usfirst.frc.team2403.robot.Intake.LiftHeight;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Autonomous {
	
	DriveTrain drive;
	AHRS navX;
	Catapult catapult;
	Intake intake;
	Timer timer;
	int stage;
	public boolean isDoingCheval;
	int chevalStage;
	
	public Autonomous(DriveTrain drive, Catapult catapult, Intake intake){
		this.drive = drive;
		this.navX = drive.navX;
		this.catapult = catapult;
		this.intake = intake;
		isDoingCheval = false;
		timer = new Timer();
	}
	
	public void initAuton(){
		timer.start();
		stage = 0;
		drive.resetEncoders();
		navX.zeroYaw();
	}
	
	public void mode1()
	{	
		SmartDashboard.getNumber("distance  ", drive.getDistance());
		if(drive.getDistance() > 20){
			intake.manualRoller(0);
		}
		else{
			intake.manualRoller(.5);
		}
		distanceDrive(205, .8, 0);
		intake.manualLift(LiftHeight.LOAD_TO_SHOOT);
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
				drive.resetEncoders();
				stage++;
			}
			break;
		case 1:
			if(!pause(1)){
				distanceDrive(20, -.4, 0);
			}
			else{
				drive.resetEncoders();
				stage++;
			}
			break;
		case 2:
			if(distanceDrive(4, .4, 0)){
				drive.resetEncoders();
				stage++;
			}
			break;
		case 3:
			intake.manualLift(Intake.LiftHeight.ALL_DOWN);
			intake.manualRoller(.5);
			if(intake.getHeight() > .4){
				drive.resetEncoders();
				stage++;
			}
			break;
		case 4:
			intake.manualLift(Intake.LiftHeight.ALL_DOWN);
			intake.manualRoller(0);
			if(distanceDrive(10, -.6, 0)){
				drive.resetEncoders();
				stage++;
			}
			break;
		case 5:
			intake.manualLift(Intake.LiftHeight.ALL_UP);
			intake.manualRoller(0);
			distanceDrive(134, -.6, 0);
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
				intake.manualRoller(.5);
				intake.manualLift(Intake.LiftHeight.PICKUP_BALL);
				if(intake.getHeight() > .2){
					stage++;
				}
				break;
			case 1:
				intake.manualRoller(0);
				if(distanceDrive(213, .5, 0)){
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
				if(angleTurn(60, .3)){
					drive.resetEncoders();
					stage++;
				}
				break;
			case 3:
				if(distanceDrive(95,0.6,60)){
					drive.resetEncoders();
					drive.resetEncoders();
					stage++;
				}
				break;
			case 4:
				intake.manualLift(Intake.LiftHeight.ALL_DOWN);
				if(distanceDrive(2,0.2,60)){
					drive.resetEncoders();
					stage++;
				}
				break;
			case 5:
				intake.manualLift(Intake.LiftHeight.ALL_DOWN);
				if(pause(1)){
					stage = 20;//So it doesnt shoot
				}
				break;
			case 6:
				if(catapult.autoShoot(1, 110, intake)){
					stage ++;
				}
				break;
			default:
				break;
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
	
	boolean isWaiting = false;
	double tempTime;
	private boolean pause(double time){
		if(!isWaiting){
			tempTime = timer.get();
			isWaiting = true;
		}
		if(timer.get() - tempTime >= time){
			isWaiting = false;
			return true;
		}
		DriverStation.reportError(timer.get() + "", false);
		return false;
	}
}

	