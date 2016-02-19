//test

package org.usfirst.frc.team2403.robot;

import org.usfirst.frc.team2403.robot.joystick.PlasmaJoystick;
import edu.wpi.first.wpilibj.*;

public class Robot extends IterativeRobot {

	
	PlasmaJoystick joystick;
	DriveTrain driveTrain;
	VisionTracking vision;
	Catapult catapult;
	Intake intake;
	
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
    	intake = new Intake(Constants.TALON_LIFT_PORT, Constants.TALON_ROLLER_PORT);
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
    	vision.update();
    	
        if(joystick.RB.isPressed()){
        	vision.turnToTarget(driveTrain);
        }
        else{
        	driveTrain.FPSDrive(joystick.LeftY, joystick.RightX);
        }
    	joystick.publishValues();
    	catapult.publishData();
    	catapult.cycleShoot(joystick.A, 1, 90);
    	intake.runLift(joystick.LT, joystick.RT);
    	intake.runRollers(joystick.X, joystick.B);
    	intake.display();
    }
    
    
}
