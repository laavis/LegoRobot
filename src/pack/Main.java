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
		// TODO Auto-generated method stub
		RegulatedMotor leftMotor = new EV3LargeRegulatedMotor(MotorPort.D);
		RegulatedMotor rightMotor = new EV3LargeRegulatedMotor(MotorPort.A);
		RegulatedMotor shootMotor = new EV3MediumRegulatedMotor(MotorPort.B);
		EV3IRSensor irSensorLeft = new EV3IRSensor(SensorPort.S4);
		IRChecker checkerThread = new IRChecker(irSensorLeft);
		Drive dr = new Drive(leftMotor, rightMotor, shootMotor);
		MusicPlayer player = new MusicPlayer();
		player.PlaySong("Ukko");
		boolean isPressed = false;
		
		checkerThread.start();
		while (!Button.ESCAPE.isDown()) {
			int beacon = checkerThread.getCommand();			
			LCD.drawString("Command :" + beacon, 0, 4);
			switch (beacon) {
			case 1:
				isPressed = false;
				dr.spinLeft();
				break;
			case 2:
				isPressed = false;
				dr.spinLeftBack();
				break;
			case 3:
				isPressed = false;
				dr.spinRight();
				break;
			case 4:
				isPressed = false;
				dr.spinRightBack();
				break;
			case 5:
				isPressed = false;
				dr.driveForward();
				break;
			case 6:
				isPressed = false;
				dr.turnRight();
				break;
			case 7:
				isPressed = false;
				dr.turnLeft();
				break;
			case 8:
				isPressed = false;
				dr.driveBackward();
				break;
			case 9:
				if(!isPressed) {
					isPressed = true;
					dr.shoot();
				}
				break;

			default:
				dr.stop();
				isPressed = false;
				break;
			}			
			
		}
		leftMotor.close();
		rightMotor.close();
		irSensorLeft.close();
		checkerThread.interrupt();
	}

}
