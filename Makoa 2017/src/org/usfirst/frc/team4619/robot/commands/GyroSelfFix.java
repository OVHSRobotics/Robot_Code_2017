package org.usfirst.frc.team4619.robot.commands;

public class GyroSelfFix extends CommandBase{
	
	double setPoint;
	double time;
	long startTime;
	
	public GyroSelfFix(double time, double setPoint)
	{
		requires(driveMech);
		this.time = time;
	}
	
	protected void initialize() 
	{
		this.startTime = System.currentTimeMillis();
		driveMech.setSetpoint(setPoint);
		driveMech.enable();
		//setTimeout(this.time);
	}

	@Override	
	protected void execute() 
	{	
	}	
	
	
	@Override
	protected boolean isFinished() 
	{
		// TODO Auto-generated method stub
		//return isTimedOut();
		return System.currentTimeMillis() - this.startTime > this.time * 1000;
	}
	
	@Override
	protected void end() 
	{
		driveMech.disable();
	}

	@Override
	protected void interrupted() {
		end();
		//driveMech.doNothing();
	}


}
