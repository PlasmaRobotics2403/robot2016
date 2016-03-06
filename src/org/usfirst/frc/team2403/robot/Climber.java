package org.usfirst.frc.team2403.robot;

import org.usfirst.frc.team2403.robot.controllers.ControlPanel;
import org.usfirst.frc.team2403.robot.controllers.PlasmaTrigger;

import edu.wpi.first.wpilibj.*;

public class Climber {

	CANTalon frontTalon;
	CANTalon backLeftTalon;
	CANTalon backRightTalon;
	
	public Climber(int frontPort, int backLeftPort, int backRightPort){
		frontTalon = new CANTalon(frontPort);
		backLeftTalon = new CANTalon(backLeftPort);
		backRightTalon = new CANTalon(backRightPort);
	}
	
	public void controlClimb(PlasmaTrigger front, PlasmaTrigger back, ControlPanel panel){
		if(panel.toggleSwitch.isPressed()){
			frontTalon.set(front.getFilteredAxis());
			backLeftTalon.set(back.getFilteredAxis());
			backRightTalon.set(-back.getFilteredAxis());
		}
		else{
			frontTalon.set(-front.getFilteredAxis());
			backLeftTalon.set(-back.getFilteredAxis());
			backRightTalon.set(back.getFilteredAxis());
		}
	}
}
