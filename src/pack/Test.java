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

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		RegulatedMotor leftMotor = new EV3LargeRegulatedMotor(MotorPort.A);
		RegulatedMotor rightMotor = new EV3LargeRegulatedMotor(MotorPort.D);
		RegulatedMotor shootMotor = new EV3MediumRegulatedMotor(MotorPort.B);
		EV3IRSensor irSensorLeft = new EV3IRSensor(SensorPort.S4);

		IRChecker checkerThread = new IRChecker(irSensorLeft);
		checkerThread.start();
		Drive dr = new Drive(leftMotor, rightMotor, shootMotor);

		while (!Button.ESCAPE.isDown()) {
			int beacon = checkerThread.getCommand();			
			LCD.drawString("Command :" + beacon, 0, 4);
			switch (beacon) {
			case 1:
				dr.spinLeft();
				break;
			case 2:
				dr.spinLeftBack();
				break;
			case 3:
				dr.spinRight();
				break;
			case 4:
				dr.spinRightBack();
				break;
			case 5:
				dr.driveForward();
				break;
			case 6:
				dr.spinLeft();
				dr.spinRightBack();
				break;
			case 7:
				dr.spinLeftBack();
				dr.spinRight();
				break;
			case 8:
				dr.driveBackward();
				break;
			default:
				dr.stop();
				break;
			}			
			
		}
		leftMotor.close();
		rightMotor.close();
		irSensorLeft.close();
		checkerThread.interrupt();
	}

}
