package org.usfirst.frc.team2403.robot;

import org.usfirst.frc.team2403.robot.joystick.*;

import edu.wpi.first.wpilibj.*;

public class DriveTrain {
	
	private static final double maxSpeed = .8;
	private static final double maxTurn = .4;
	
	CANTalon talonLeft;
	CANTalon talonLeftSlave;
	CANTalon talonRight;
	CANTalon talonRightSlave;
	
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
		talonLeftSlave = new CANTalon(rightSID);
		
		talonLeftSlave.changeControlMode(CANTalon.TalonControlMode.Follower);
		talonRightSlave.changeControlMode(CANTalon.TalonControlMode.Follower);
		
		talonLeftSlave.set(leftID);
		talonRightSlave.set(rightID);
		
		talonRight.reverseOutput(true);
		
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
		double turnVal = maxTurn * turnAxis.getFilteredAxis();
		
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
			DriverStation.reportError("Bug @ fps drive code - no case triggered", false);
		}
		
		speedL *= maxSpeed;
		speedR *= maxSpeed;
		
		talonLeft.set(speedL);
		talonRight.set(speedR);
		
	}
	
	
	
}
