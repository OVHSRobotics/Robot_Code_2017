package org.usfirst.frc.team4619.robot;

import org.usfirst.frc.team4619.robot.commands.CommandBase;
import org.usfirst.frc.team4619.robot.commands.RobotClimb;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	//created xbox joystick object
	Joystick xbox = new Joystick(RobotMap.XBOX_PORT);
	
	//created logitech joystick object
	//Joystick logitech = new Joystick(RobotMap.LOGITECH_PORT);
	
	//created buttons for xbox joysticks
	public Button A;
	public Button B;
	public Button X;
	public Button Y;
	public Button L_BUMPER;
	public Button R_BUMPER;
	public Button BACK;
	public Button START;
	
	//create button for logitech joystick
	public Button TRIGGER;
	public Button BUTTON3;
	
	//an array list of all the button values
	public int[] buttonValues = {RobotMap.A, RobotMap.B, RobotMap.X, RobotMap.Y,
			 RobotMap.L_BUMPER, RobotMap.R_BUMPER, RobotMap.BACK, RobotMap.START};

	//an array list of all the buttons
	public Button [] buttons = {A, B, X, Y, L_BUMPER, R_BUMPER, BACK, START};
	
	//this method uses enhance for loops to assign values to all the buttons
	public void setButtonValues()
	{		
		for (int i = 0; i < buttonValues.length; i++)
		{
			buttons[i] = new JoystickButton(xbox, buttonValues[i]);
		}
		
		//TRIGGER = new JoystickButton(logitech, RobotMap.TRIGGER);
		//BUTTON3 = new JoystickButton(logitech, RobotMap.BUTTON3);
	}
	
	//constructor
	//basically assign values to all the buttons by calling the setButtonValues method
	public OI()
	{
		setButtonValues();		
		buttons[0].whileHeld(new RobotClimb(.9));
		buttons[1].whileHeld(new RobotClimb(.9));
	}
	
	//this method return the raw value of the xbox x-axis
	public double getXAxis()
	{
		return xbox.getRawAxis(RobotMap.XBOX_XAXIS);
	}
	
	//this method return the raw value of the xbox y-axis
	public double getYAxis()
	{
		return xbox.getRawAxis(RobotMap.XBOX_YAXIS);
	}
	
	

	

	// There are a few additional built in buttons you can use. Additionally,
	// by subclassing Button you can create custom triggers and bind those to
	// commands the same as any other Button.

	//// TRIGGERING COMMANDS WITH BUTTONS
	// Once you have a button, it's trivial to bind it to a button in one of
	// three ways:

	// Start the command when the button is pressed and let it run the command
	// until it is finished as determined by it's isFinished method.
	// button.whenPressed(new ExampleCommand());

	// Run the command while the button is being held down and interrupt it once
	// the button is released.
	// button.whileHeld(new ExampleCommand());

	// Start the command when the button is released and let it run the command
	// until it is finished as determined by it's isFinished method.
	// button.whenReleased(new ExampleCommand());

}
