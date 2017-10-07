/**package org.usfirst.frc.team4619.robot.subsystems;

import org.usfirst.frc.team4619.robot.RobotMap;
import org.usfirst.frc.team4619.robot.commands.ShootBall;

import com.ctre.CANTalon;
import com.ctre.CANTalon.FeedbackDevice;
import com.ctre.CANTalon.TalonControlMode;

import edu.wpi.first.wpilibj.command.Subsystem;

public class Shooter extends Subsystem{	
	
	CANTalon shooter;
	int devID = 0;
	double kF = 0;
	double kP = .8;
	double kI = .2;
	double kD = .5;
	double profile = 0;
	double nominalOutPut = 0;
	double peakOutPut = 12;
	double speed;
	
	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
		//11000 should be default (original rpm -10%)
		setDefaultCommand(new ShootBall(11000));
	}
	
	public Shooter() {
		shooter = new CANTalon(devID);
		shooter.setFeedbackDevice(FeedbackDevice.QuadEncoder);
		shooter.configNominalOutputVoltage(nominalOutPut, nominalOutPut);
		shooter.configPeakOutputVoltage(peakOutPut, peakOutPut);
		shooter.setProfile(0);
		shooter.setF(kF);
		shooter.setP(kP);
		shooter.setI(kI);
		shooter.setD(kD);
	}
	
	public void Shoot(double speed) {
		//maximum rpm is 13180 +/- 10%
		this.speed = speed;
		shooter.changeControlMode(TalonControlMode.Speed);
		shooter.set(-speed);
	}
	
	public void doNothing() {
		shooter.changeControlMode(TalonControlMode.Speed);
		shooter.set(0);
	}
}
**/