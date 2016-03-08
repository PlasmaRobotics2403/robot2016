package org.usfirst.frc.team2403.robot;

import org.usfirst.frc.team2403.robot.controllers.*;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.*;

public class DriveTrain {
	
	CANTalon talonLeft;
	CANTalon talonLeftSlave;
	CANTalon talonRight;
	CANTalon talonRightSlave;
	
	public AHRS navX;
	
	/**
	 * Init for drive train
	 * 
	 * @param leftID - CAN ID of left main Talon SRX
	 * @param leftSID - CAN ID of left slave Talon SRX
	 * @param rightID - CAN ID of right main Talon SRX
	 * @param rightSID - CAN ID of right slave Talon SRX
	 * 
	 * @author Nic A
	 */
	public DriveTrain(int leftID, int leftSID, int rightID, int rightSID){
		
		talonLeft = new CANTalon(leftID);
		talonLeftSlave = new CANTalon(leftSID);
		talonRight = new CANTalon(rightID);
		talonRightSlave = new CANTalon(rightSID);
		
		talonLeftSlave.changeControlMode(CANTalon.TalonControlMode.Follower);
		talonLeftSlave.set(leftID);
		
		talonRightSlave.changeControlMode(CANTalon.TalonControlMode.Follower);
		talonRightSlave.set(rightID);
		
		talonRight.setInverted(true);
		
    	navX = new AHRS(SerialPort.Port.kMXP);
    	navX.zeroYaw();
	}
	
	/**
	 * Drives robot based on FPS controls
	 * 
	 * @param forwardAxis - Joystick axis that controls forward motion
	 * @param turnAxis - Joystick axis that controls turning
	 * 
	 * @author Nic and Mark
	 */
	public void FPSDrive(PlasmaAxis forwardAxis, PlasmaAxis turnAxis){
		
		double forwardVal = forwardAxis.getFilteredAxis();
		double turnVal = Constants.MAX_TURN * turnAxis.getFilteredAxis();
		
		double absForward = Math.abs(forwardVal);
		double absTurn = Math.abs(turnVal);
		
		int forwardSign = (forwardVal == 0) ? 0 : (int)(forwardVal / Math.abs(forwardVal));
		int turnSign = (turnVal == 0) ? 0 : (int)(turnVal / Math.abs(turnVal));
		
		double speedL;
		double speedR;
		
		if(turnVal == 0){ //Straight forward
			speedL = forwardVal;
			speedR = forwardVal;
		}
		else if(forwardVal == 0){ //Pivot turn
			speedL = turnVal;
			speedR = -turnVal;
		}
		else if(forwardSign == 1 && turnSign == 1){ //Forward right
			speedL = forwardVal;
			speedR = (absForward - absTurn < 0) ? 0 : absForward - absTurn;
		}
		else if(forwardSign == 1 && turnSign == -1){ //Forward left
			speedL = (absForward - absTurn < 0) ? 0 : absForward - absTurn;
			speedR = forwardVal;
		}
		else if(forwardSign == -1 && turnSign == 1){ //Backward right
			speedL = forwardVal;
			speedR = (absForward - absTurn < 0) ? 0 : -(absForward - absTurn);
		}
		else if(forwardSign == -1 && turnSign == -1){ //Backward left
			speedL = (absForward - absTurn < 0) ? 0 : -(absForward - absTurn);
			speedR = forwardVal;
		}
		else{
			speedL = 0;
			speedR = 0;
			DriverStation.reportError("You've got two empty halves of coconut and you're bangin' 'em together. (Bug @ fps drive code - no case triggered)", false);
		}
		
		speedL *= Constants.MAX_SPEED;
		speedR *= Constants.MAX_SPEED;
		
		talonLeft.set(speedL);
		talonRight.set(speedR);
		
	}
	
	/**
	 * Tank drive for automated driving
	 * 
	 * @param left - Speed for left motor
	 * @param right - Speed for right motor
	 * 
	 * @author Nic A
	 */
	public void autonTankDrive(double left, double right){
		talonLeft.set(left);
		talonRight.set(right);
	}
	
	public void gyroStraight(double speed, double angle){
		autonTankDrive(speed - .01*(navX.getYaw() - angle), speed + .01*(navX.getYaw() - angle));
	}
	
}
