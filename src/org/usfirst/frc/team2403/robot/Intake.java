package org.usfirst.frc.team2403.robot;

import org.usfirst.frc.team2403.robot.joystick.*;
import edu.wpi.first.wpilibj.*;

public class Intake {
	
	private CANTalon lift;
	private CANTalon roller;
	
	/**
	 * Constructor for intake object
	 * 
	 * @param liftID - Talon ID of lift motor
	 * @param rollerID - Talon ID of roller motor
	 * @author Nic A
	 */
	public Intake(int liftID, int rollerID) {
		lift = new CANTalon(liftID);
		roller = new CANTalon(rollerID);
	}
	
	/**
	 * Runs rollers based on two button inputs
	 * @param in - Button that makes rollers pull ball in
	 * @param out - Button that makes rollers push ball out
	 * @author Nic A
	 */
	public void runRollers(PlasmaButton in, PlasmaButton out){
		if(in.isPressed()){
			roller.set(Constants.ROLLER_SPEED);
		}
		else if(out.isPressed()){
			roller.set(-Constants.ROLLER_SPEED);
		}
		else{
			roller.set(Constants.ROLLER_STOPPED);
		}
	}
	
	/**
	 * Runs lift based on two trigger inputs
	 * @param up - Trigger that makes lift go up
	 * @param out - Trigger that makes lift go down
	 * @author Nic A
	 */
	public void runLift(PlasmaTrigger up, PlasmaTrigger down){
		if(up.isPressed()){
			lift.set(up.getFilteredAxis() * Constants.MAX_LIFT_SPEED);
		}
		else if(down.isPressed()){
			lift.set(-down.getFilteredAxis() * Constants.MAX_LIFT_SPEED);
		}
		else{
			lift.set(Constants.LIFT_STOPPED);
		}
	}
	
}
