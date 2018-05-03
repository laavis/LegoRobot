package pack;

import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.motor.EV3MediumRegulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3IRSensor;
import lejos.robotics.RegulatedMotor;
import lejos.hardware.sensor.EV3TouchSensor;

public class Main {

	public static void main(String[] args) {
		RegulatedMotor leftMotor = new EV3LargeRegulatedMotor(MotorPort.D);
		RegulatedMotor rightMotor = new EV3LargeRegulatedMotor(MotorPort.A);
		RegulatedMotor shootMotor = new EV3MediumRegulatedMotor(MotorPort.B);

		Drive dr = new Drive(leftMotor, rightMotor, shootMotor);
		EV3IRSensor irSensorLeft = new EV3IRSensor(SensorPort.S4);
		EV3TouchSensor touchSensorLeft = new EV3TouchSensor(SensorPort.S2);
		EV3TouchSensor touchSensorRight = new EV3TouchSensor(SensorPort.S3);
		IRChecker checkerThread = new IRChecker(irSensorLeft);
		DistanceIR distance = new DistanceIR();
		Autopilot tesla = new Autopilot(dr, distance, touchSensorLeft, touchSensorRight);

		checkerThread.changeChannel(); // Initializes channel

		RemoteControl rC = new RemoteControl(irSensorLeft, checkerThread, dr, distance, tesla);
		rC.Control(); // Starts contol

		leftMotor.close();
		rightMotor.close();
	}

}
