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
		MusicPlayer player = new MusicPlayer();
		
		boolean isPressed = false;
		
		// Play a funky tune
		//player.PlaySong("Ukko");
		// Initialize sensors
		checkerThread.start();
		distance.start();
		
		while (!Button.ESCAPE.isDown()) {
			int beacon = checkerThread.getCommand();
			int channel = checkerThread.getChannel();

			LCD.drawString("Command: " + beacon, 0, 4);
			LCD.drawString("Channel: " + channel, 0, 5);
			LCD.drawString("Distance: " + distance.distance(), 0, 6);
			LCD.drawString("isPressed: " + isPressed, 0, 7);
			LCD.refresh();
			LCD.clear();
			
			if (!isPressed) {
				switch (beacon) {
				case 1:
					if (channel == 2) {
						dr.spinLeft();
					} else if (channel == 3) {
						dr.shoot();
					}
					isPressed = false;
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
					isPressed = true;
					while(beacon==9) {
						beacon = checkerThread.getCommand();
					}
					break;

				default:
					dr.stop();
					isPressed = false;
					break;
				}
			}else {
				while (true) {
					LCD.drawString("Choose Channel", 0, 0);
					beacon = checkerThread.getCommand();
					if(beacon > 0 && beacon < 5) {
						checkerThread.changeChannel(beacon -1);
						break;
					}
				}
				beacon = 0;
				isPressed = false;
			}

		}
		leftMotor.close();
		rightMotor.close();
		irSensorLeft.close();
		distance.stopSensor();
		distance.interrupt();
		checkerThread.interrupt();
	}

}
