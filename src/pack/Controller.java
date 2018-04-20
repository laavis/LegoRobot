package pack;

import lejos.hardware.Button;
import lejos.hardware.lcd.LCD;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.motor.EV3MediumRegulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3IRSensor;
import lejos.robotics.RegulatedMotor;

public class Controller {
	MusicPlayer player = new MusicPlayer();
	
	public void Control (RegulatedMotor leftMotor, RegulatedMotor rightMotor, RegulatedMotor shootMotor, EV3IRSensor irSensorLeft, IRChecker checkerThread, Drive dr)
	{
		boolean isPressed = false;
		checkerThread.start();
		
		//CHANNEL 2 = DRIVE MODE, CHANNEL 3 == ???, CHANNEL 4 == MUSIC MODE
		while (!Button.ESCAPE.isDown()) {
			int beacon = checkerThread.getCommand();
			int channel = checkerThread.getChannel();
			LCD.drawString("Command: " + beacon, 0, 4);
			LCD.drawString("Channel: " + channel, 0, 5);
			if (!isPressed) {
				switch (beacon) {
				case 1:
					if (channel == 2) {
						dr.spinLeft();
					} else if (channel == 3) {
						dr.shoot();
					} else if (channel == 4) {
						player.PlaySong("Ukko");
					}
					isPressed = false;
					break;
				case 2:
					if (channel == 2) {
						dr.spinLeftBack();
					} else if (channel == 3) {
						
					} else if (channel == 4)
					{
						
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
					while(beacon==9) {
						
					}
					break;

				default:
					dr.stop();
					isPressed = false;
					break;
				}
			}else {
				checkerThread.changeChannel();
			}

		}
		leftMotor.close();
		rightMotor.close();
		irSensorLeft.close();
		checkerThread.interrupt();	
	}
}
