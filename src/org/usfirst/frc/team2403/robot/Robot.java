//test

package org.usfirst.frc.team2403.robot;

import org.usfirst.frc.team2403.robot.controllers.ControlPanel;
import org.usfirst.frc.team2403.robot.controllers.PlasmaJoystick;

import com.kauailabs.navx.frc.*;

import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Robot extends IterativeRobot {

	
	PlasmaJoystick joystick;
	DriveTrain driveTrain;
	VisionTracking vision;
	Catapult catapult;
	Intake intake;
	ControlPanel panel;
	Climber climb;
	
	/**
	 * Initialization for robot - called once when robot turns on
	 * 
	 * @author Nic A
	 */
    public void robotInit() {
    	joystick = new PlasmaJoystick(Constants.JOYSTICK1_PORT);
    	panel = new ControlPanel(1);
    	driveTrain = new DriveTrain(Constants.TALON_L_PORT, 
    								Constants.TALON_L_SLAVE_PORT, 
    								Constants.TALON_R_PORT, 
    								Constants.TALON_R_SLAVE_PORT);
    	catapult = new Catapult(Constants.TALON_CATAPULT_PORT);
    	intake = new Intake(Constants.TALON_LIFT_PORT,Constants.TALON_ROLLER_PORT);
    	vision = new VisionTracking();
    	climb = new Climber(21, 22, 23);
    	/*CameraServer server = CameraServer.getInstance();
    	server.setQuality(50);
    	server.startAutomaticCapture("cam1");*/
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
    	//vision.update();
    	
        //driveTrain.FPSDrive(joystick.LeftY, joystick.RightX);
        SmartDashboard.putNumber("navX", driveTrain.navX.getAngle());
    	//driveTrain.autonTankDrive(.3, .3);
    	
    	joystick.publishValues();
    	catapult.publishData();
    	catapult.cycleShoot(joystick.RB, 1, 90, joystick.LB, .2, 60, intake);
    	intake.liftControl(joystick.Y, joystick.A, catapult);
    	intake.runRollers(joystick.X, joystick.B);
    	intake.publishData();
    	panel.set7Seg();
    	climb.controlClimb(joystick.LT, joystick.RT, panel);
    }
    
    
}
