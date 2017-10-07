package org.usfirst.frc.team4619.robot.subsystems;

import org.usfirst.frc.team4619.robot.RobotMap;
import org.usfirst.frc.team4619.robot.commands.CommandBase;
import org.usfirst.frc.team4619.robot.commands.DriveJoystick;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.PIDSubsystem;

public class DriveBase extends PIDSubsystem
{	
	private VictorSP frontL = new VictorSP(RobotMap.PWM_PORT_3);
	private VictorSP backL = new VictorSP(RobotMap.PWM_PORT_2);
	private VictorSP frontR = new VictorSP(RobotMap.PWM_PORT_1);
	private VictorSP backR = new VictorSP(RobotMap.PWM_PORT_0);
	//private Encoder rightEncoder;
	//private Encoder leftEncoder;
	public ADXRS450_Gyro gyro;
	private final static double p = .13;
	private final static double i = 0;
	private final static double d = .05;
	
	
	public RobotDrive driveTrain;
	
	public DriveBase() {
		super("DriveBase", p, i, d);
		//rightEncoder = new Encoder(2, 3, false);
		//leftEncoder = new Encoder(0, 1, true);
		gyro = new ADXRS450_Gyro();
		frontL.setInverted(true);
		backL.setInverted(true);
		frontR.setInverted(true);
		backR.setInverted(true);
	    driveTrain = new RobotDrive(frontL, backL, frontR, backR);
	}
	
	private double noVector = 0;
	
	
	public void arcadeDrive(double y, double x)
	{
		driveTrain.arcadeDrive(y, x, true);
	}
	
	@Override
	protected void initDefaultCommand() 
	{
		// TODO Auto-generated method stub
		setDefaultCommand(new DriveJoystick());
	} 
	
	public void doNothing()
	{
		frontL.set(noVector);
	}
	
	public void moveForward() 
	{
		moveForward(0.75);
	}
	
	public void moveForward(double speed)
	{
		frontL.set(speed);
		frontR.set(-speed);
		backL.set(speed);
		backR.set(-speed);
	}

	public void moveBack() 
	{
		moveBack(.75);
	}
	
	public void moveBack(double speed)
	{
		frontL.set(-speed);
		frontR.set(speed);
		backL.set(-speed);
		backR.set(speed);
	}
	
	public void turnL()
	{
		turnL(.75);
	}
	
	public void forwardMod(double speed)
	{
		frontL.set(-speed);
		frontR.set(speed*.965);
		backL.set(-speed);
		backR.set(speed*.965);
	}
	
	public void turnL(double speed) 
	{
		frontL.set(-speed);
		frontR.set(-speed);
		backL.set(-speed);
		backR.set(-speed);
	}

	public void turnR()
	{
		turnR(.75);
	}
	
	public void turnR(double speed) 
	{
		frontL.set(speed);
		frontR.set(speed);
		backL.set(speed);
		backR.set(speed);
	}
	
	//@Override
	//protected double returnPIDInput() {
		// TODO Auto-generated method stub	
		//return CommandBase.driveMech.gyro.pidGet();
	//}

	@Override
	protected void usePIDOutput(double output) {
		// TODO Auto-generated method stub
		arcadeDrive(-.5,output);
	}

	@Override
	protected double returnPIDInput() {
		// TODO Auto-generated method stub
		return 0;
	}
}
