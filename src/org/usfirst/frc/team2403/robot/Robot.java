//test

package org.usfirst.frc.team2403.robot;

import org.usfirst.frc.team2403.robot.joystick.PlasmaJoystick;
import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Robot extends IterativeRobot {

	
	PlasmaJoystick joystick;
	DriveTrain driveTrain;
	VisionTracking vision;
	Catapult catapult;
	
	boolean isOut = false;
	boolean isIn = false;
	/**
	 * Initialization for robot - called once when robot turns on
	 * 
	 * @author Nic A
	 */
    public void robotInit() {
    	joystick = new PlasmaJoystick(Constants.JOYSTICK1_PORT);
    	driveTrain = new DriveTrain(Constants.TALON_L_PORT, 
    								Constants.TALON_L_SLAVE_PORT, 
    								Constants.TALON_R_PORT, 
    								Constants.TALON_R_SLAVE_PORT);
    	catapult = new Catapult(Constants.TALON_CATAPULT_PORT);
    	vision = new VisionTracking();
    }
    
    /**
	 * Initialization for auto mode - called once when robot starts auto mode
	 * 
	 * @author Nic A
	 */
    public void autonomousInit() {
    	
    }
    
    /**
	 * Main loop for auto mode - called once every 20ms while in auto mode
	 * 
	 * @author Nic A
	 */
    public void autonomousPeriodic() {
    	
    }
    
    /**
	 * Main loop for teleop mode - called once every 20ms while in teleop mode
	 * 
	 * @author Nic A
	 */
    public void teleopPeriodic() {
        
    	//driveTrain.FPSDrive(joystick.LeftY, joystick.RightX);
    	joystick.publishValues();
    	catapult.publishData();
    	
    	vision.update();
    	//catapult.reload();
    }
    
    
}
