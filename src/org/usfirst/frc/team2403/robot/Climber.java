package org.usfirst.frc.team2403.robot;


import org.usfirst.frc.team2403.robot.controllers.PlasmaTrigger;

import edu.wpi.first.wpilibj.*;

public class Climber {

	CANTalon frontTalon;
	CANTalon frontTalon2;
	
	public Climber(int frontPort, int frontPort2){
		frontTalon = new CANTalon(frontPort);
		frontTalon2 = new CANTalon(frontPort2);
	}
	
	public void controlClimb(PlasmaTrigger up, PlasmaTrigger down){
		if(up.isPressed()){
			frontTalon.set(up.getFilteredAxis());
			frontTalon2.set(-up.getFilteredAxis());
		}
		else{
			frontTalon.set(-down.getFilteredAxis());
			frontTalon2.set(down.getFilteredAxis());
		}
	}
}
