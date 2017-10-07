package org.usfirst.frc.team4619.robot.commands;

public class DriveJoystick extends CommandBase {

	public DriveJoystick()
	{
		requires(driveMech);
	}
	
	public void initialize()
	{
	}
	
	public void execute()
	{
		if(oi.getXAxis() <= .35 || oi.getYAxis() <= .35)
		{
			driveMech.arcadeDrive(.3, .3);
			if(oi.getXAxis() > 0 && oi.getYAxis() == 0)
			{
				driveMech.setSetpoint(0);
				driveMech.enable();
			}
			else
			{
				driveMech.disable();
			}
		}
		else
		{
			driveMech.arcadeDrive(oi.getXAxis(), oi.getYAxis());	
			if(oi.getXAxis() > 0 && oi.getYAxis() == 0)
			{
				driveMech.setSetpoint(0);
				driveMech.enable();
			}
			else
			{
				driveMech.disable();
			}
		}
	}
	
	public boolean isFinished()
	{
		return false;
	}
	
	public void end()
	{
		
		
	}
	
	public void interrupted()
	{
		
	}
	
}
