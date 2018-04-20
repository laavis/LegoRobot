package pack;

import lejos.hardware.Button;
import lejos.hardware.lcd.LCD;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.motor.EV3MediumRegulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3IRSensor;
import lejos.robotics.RegulatedMotor;
import lejos.utility.Delay;
import lejos.hardware.Sound;

public class Main {

	public static void main(String[] args) {
		RegulatedMotor leftMotor = new EV3LargeRegulatedMotor(MotorPort.D);
		RegulatedMotor rightMotor = new EV3LargeRegulatedMotor(MotorPort.A);
		RegulatedMotor shootMotor = new EV3MediumRegulatedMotor(MotorPort.B);
		
		Drive dr = new Drive(leftMotor, rightMotor, shootMotor);
		EV3IRSensor irSensorLeft = new EV3IRSensor(SensorPort.S4);
		IRChecker checkerThread = new IRChecker(irSensorLeft);
		DistanceIR distance = new DistanceIR();
		
		RemoteControl rC = new RemoteControl(irSensorLeft, checkerThread, dr, distance);
		rC.Control();
		
		leftMotor.close();
		rightMotor.close();
	}

}
