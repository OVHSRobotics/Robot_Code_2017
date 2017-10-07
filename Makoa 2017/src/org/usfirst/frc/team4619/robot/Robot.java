
package org.usfirst.frc.team4619.robot;

import org.usfirst.frc.team4619.robot.commands.CommandBase;
import org.usfirst.frc.team4619.robot.commands.GyroSelfFix;
import org.usfirst.frc.team4619.robot.commands.TimeDriveMod;

import org.usfirst.frc.team4619.robot.subsystems.ExampleSubsystem;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {
	
	public static final ExampleSubsystem exampleSubsystem = new ExampleSubsystem();
	
	Command autonomousCommand;
	SendableChooser<Command> chooser;
	UsbCamera cam;
	
	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		CommandBase.init();
		
		chooser = new SendableChooser<>();
		chooser.addDefault("Default Auto", new TimeDriveMod(15, 0));
		chooser.addObject("Base Line", new TimeDriveMod(3, .5));
		chooser.addObject("gear ", new TimeDriveMod(10, .3));
		chooser.addObject("Gyro Straight", new GyroSelfFix(10,0));
		
		SmartDashboard.putData("Sup Chrissy, pick one: ", chooser);
		CommandBase.driveMech.gyro.calibrate();
		CommandBase.driveMech.gyro.reset();
		
		cam = CameraServer.getInstance().startAutomaticCapture();
		cam.setResolution(160, 120);
		cam.setFPS(30);
		
	}

	/**
	 * This function is called once each time the robot enters Disabled mode.
	 * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
	 */
	@Override
	
	
	public void disabledInit() {

	}
	
	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select
	 * between different autonomous modes using the dashboard. The sendable
	 * chooser code works with the Java SmartDashboard. If you prefer the
	 * LabVIEW Dashboard, remove all of the chooser code and uncomment the
	 * getString code to get the auto name from the text box below the Gyro
	 *
	 * You can add additional auto modes by adding additional commands to the
	 * chooser code above (like the commented example) or additional comparisons
	 * to the switch structure below with additional strings & commands.
	 */
	@Override
	public void autonomousInit() {
		autonomousCommand = (Command) chooser.getSelected();
		/*
		 * String autoSelected = SmartDashboard.getString("Auto Selector",
		 * "Default"); switch(autoSelected) { case "My Auto": autonomousCommand
		 * = new MyAutoCommand(); break; case "Default Auto": default:
		 * autonomousCommand = new ExampleCommand(); break; }
		 */

		// schedule the autonomous command (example)
		if (autonomousCommand != null)
		{
			autonomousCommand.start();
		}
		
	}

	/**
	 * This function is called periodically during autonomous
	 * It's all good
	 */
	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();		

	}

	@Override
	public void teleopInit() {
		// This makes sure that the autonomous stops running when
		// teleop starts running. If you want the autonomous to
		// continue until interrupted by another command, remove
		// this line or comment it out.
		if (autonomousCommand != null)
			autonomousCommand.cancel();
	}

	/**
	 * This function is called periodically during operator control
	 */
	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
		if(CommandBase.oi.xbox.getRawAxis(RobotMap.R_TRIGGER)>0)
		{
			CommandBase.climberMech.Climb(.7);
		}
	
	}

	/**
	 * This function is called periodically during test mode
	 */
	@Override
	public void testPeriodic() {
		LiveWindow.run();
	}
}
