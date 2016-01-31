package org.usfirst.frc.team2403.robot;

import edu.wpi.first.wpilibj.*;

public class DriveTrain {
	
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
	
	
	
}