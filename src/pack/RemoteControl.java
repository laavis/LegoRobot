package pack;

import lejos.hardware.Button;
import lejos.hardware.lcd.LCD;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.motor.EV3MediumRegulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3IRSensor;
import lejos.robotics.RegulatedMotor;

public class RemoteControl {
	EV3IRSensor irSensorLeft;
	IRChecker checkerThread;
	Drive dr;
	DistanceIR distance;
	Autopilot autopilot;
	MusicPlayer player = new MusicPlayer();

	public RemoteControl(EV3IRSensor irSensorLeft, IRChecker checkerThread, Drive dr, DistanceIR distance,
			Autopilot autopilot) {
		this.irSensorLeft = irSensorLeft;
		this.checkerThread = checkerThread;
		this.dr = dr;
		this.distance = distance;
		this.autopilot = autopilot;
	}

	public void Control() {
		boolean isPressed = false;
		checkerThread.start();
		distance.start();

		// CHANNEL 2 = DRIVE MODE, CHANNEL 3 == ???, CHANNEL 1 == MUSIC MODE
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
					} else if (channel == 1) {
						player.PlaySong("Ukko");
					} else if (channel == 0) {
						autopilot.run();
					}
					isPressed = false;
					break;
				case 2:
					if (channel == 2) {
						dr.spinLeftBack();
					} else if (channel == 3) {

					} else if (channel == 1) {

					} else if (channel == 0) {
						autopilot.interrupt();
					}
					isPressed = false;
					break;
				case 3:
					if (channel == 2) {
						dr.spinRight();
					} else if (channel == 3) {

					}
					isPressed = false;
					break;
				case 4:
					if (channel == 2) {
						dr.spinRightBack();
					} else if (channel == 3) {

					}
					isPressed = false;
					break;
				case 5:
					if (channel == 2) {
						dr.driveForward();
					} else if (channel == 3) {

					}
					isPressed = false;
					break;
				case 6:
					if (channel == 2) {
						dr.turnRight();
					} else if (channel == 3) {

					}
					isPressed = false;
					break;
				case 7:
					if (channel == 2) {
						dr.turnLeft();
					} else if (channel == 3) {

					}
					isPressed = false;
					break;
				case 8:
					if (channel == 2) {
						dr.driveBackward();
					} else if (channel == 3) {

					}
					isPressed = false;
					break;
				case 9:
					isPressed = true;
					while (beacon == 9) {
						beacon = checkerThread.getCommand();
					}
					break;

				default:
					dr.stop();
					isPressed = false;
					break;
				}
			} else {
				while (true) {
					LCD.drawString("Choose Channel", 0, 0);
					beacon = checkerThread.getCommand();
					if (beacon > 0 && beacon < 5) {
						checkerThread.changeChannel(beacon - 1);
						break;
					}
				}
				beacon = 0;
				isPressed = false;
			}

		}
		irSensorLeft.close();
		distance.interrupt();
		autopilot.interrupt();
		checkerThread.interrupt();
	}
}
