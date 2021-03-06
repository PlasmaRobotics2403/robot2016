//test

package org.usfirst.frc.team2403.robot;

import org.usfirst.frc.team2403.robot.controllers.ControlPanel;
import org.usfirst.frc.team2403.robot.controllers.PlasmaJoystick;
import org.usfirst.frc.team2403.robot.sensors.RangeFinder;

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
	Autonomous auton;
	RangeFinder range;
	//RGBController rgb;
	
	boolean navXWorkaround;
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
    	climb = new Climber(21, 22);
    	auton = new Autonomous(driveTrain, catapult, intake);
    	range = new RangeFinder(0);
    	//rgb = new RGBController(panel);
    	CameraServer server = CameraServer.getInstance();
    	server.startAutomaticCapture("cam0", 0);
    
    }
    
    public void disabledPeriodic(){
    	panel.selectAutonMode();
    	//rgb.controlRGB();
    	SmartDashboard.putNumber("range finder", range.getRange());
    }
    
    /**
	 * Initialization for auto mode - called once when robot starts auto mode
	 * 
	 * @author Nic A
	 */
    public void autonomousInit() {
    	panel.lockSelection();
    	auton.initAuton();
    	driveTrain.navX.zeroYaw();
    }
    
    /**
	 * Main loop for auto mode - called once every 20ms while in auto mode
	 * 
	 * @author Nic A
	 */
    public void autonomousPeriodic() {
    	
    	switch(panel.getSelectedMode()){
    		case 1:
    			auton.mode1();
    			break;
    		case 2:
    			auton.mode2();
    			break;
    		case 3:
    			auton.mode3();
    			break;
    		case 4:
    			auton.mode4();
    			break;
    		case 5:
    			auton.mode5();
    			break;
    		case 8:
    			auton.mode8();
    			break;
			default:
				break;
    	} 
    	
        SmartDashboard.putNumber("navX", driveTrain.navX.getYaw());
    }
    
    public void teleopInit(){
    	//panel.lockSelection();
    	driveTrain.navX.zeroYaw();
    }
    
    /**
	 * Main loop for teleop mode - called once every 20ms while in teleop mode
	 * 
	 * @author Nic A
	 */
    public void teleopPeriodic() {

    	driveTrain.FPSDrive(joystick.LeftY, joystick.RightX);
    	intake.liftControl(joystick.Y, joystick.A, catapult);
        intake.runRollers(joystick.RT, joystick.LT);
    	
        
        //driveTrain.autonTankDrive(.2, .2);
        SmartDashboard.putNumber("navX", driveTrain.navX.getYaw());
    	catapult.cycleShoot(joystick.RB, 1, 110, joystick.LB, .2, 90, intake);
    	catapult.publishData();
    	climb.controlClimb(joystick.LT, joystick.RT);
    	intake.publishData();
    	SmartDashboard.putNumber("range finder", range.getRange());
    	//rgb.controlRGB();
    }
    

    
}
