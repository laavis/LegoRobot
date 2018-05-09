package pack;

import lejos.hardware.Button;
import lejos.hardware.Sound;
import lejos.hardware.lcd.LCD;
import lejos.hardware.sensor.EV3IRSensor;
import lejos.utility.Delay;
/**
* <h1> RemoteControl <h1>
* This class contains the main loop of the program.
* Takes two integers from IRChecker and based on those sends commands to other classes.
* @author  Niklas Kiuru
* @since   16-4-2018 
*/
public class RemoteControl {
	private EV3IRSensor irSensorLeft;
	private IRChecker checkerThread;
	private Drive dr;
	private DistanceIR distance;
	private Autopilot autopilot;
	private MusicThread musicThread = new MusicThread();
	/**
	 * Constructor method
	 * @param irSensorLeft left IR sensor responsible for distance measurements.
	 * @param checkerThread IR sensor for receiving commands from beacon.
	 * @param dr Drive class used to give commands to it.
	 * @param distance Distance class
	 * @param autopilot autopilot class that is called by the user
	 * */
	public RemoteControl(EV3IRSensor irSensorLeft, IRChecker checkerThread, Drive dr, DistanceIR distance, Autopilot autopilot) {
		this.irSensorLeft = irSensorLeft;
		this.checkerThread = checkerThread;
		this.dr = dr;
		this.distance = distance;
		this.autopilot = autopilot;
	}
	/**	Listens to IR and does the corresponding command*/
	public void Control() {
		boolean isPressed = false;
		checkerThread.start();
		distance.start();

		// CHANNEL 2 = DRIVE MODE, CHANNEL 3 == ???, CHANNEL 1 == MUSIC MODE
		while (!Button.ESCAPE.isDown()) {
			int beacon = checkerThread.getCommand();
			int channel = checkerThread.getChannel();
			// display info on the LCD screen
			LCD.drawString("Command: " + beacon, 0, 4);
			LCD.drawString("Channel: " + channel, 0, 5);
			LCD.drawString("Distance: " + distance.distance(), 0, 6);
			LCD.drawString("isPressed: " + isPressed, 0, 7);
			LCD.refresh();
			LCD.clear();

			if (!isPressed) {
				switch (beacon) {
				// execute code depending the current channel
				case 1:
					if (channel == 0) {
						autopilot.run();
					} else if (channel == 1) {
						Sound.setVolume(10);
						musicThread.run();
						musicThread.setSong("Ukko");
					} else if (channel == 2) {
						dr.spinLeft();
					} else if (channel == 3) {
						dr.shoot();
					}
					isPressed = false;
					break;
				case 2:
					if (channel == 0) { 			
						autopilot.interrupt();			
					} else if (channel == 1) {		
						Sound.setVolume(10);
						musicThread.run();
						musicThread.setSong("Shelter");
					} else if (channel == 2) {
						dr.spinLeftBack();
					} else if (channel == 3) {		
						dr.shootReverse();
					} 
					isPressed = false;
					break;
				case 3:
					if (channel == 0) {
						// do something
					} else if (channel == 1) {
						// do something
					} else if (channel == 2) {
						dr.spinRight();
					} else if (channel == 3) {
						// do something
					}
					isPressed = false;
					break;
				case 4:
					if (channel == 0) {
						// do something
					} else if (channel == 1) {
						// do something
					} else if (channel == 2) {
						dr.spinRightBack();
					} else if (channel == 3) {
						// do something
					}
					isPressed = false;
					break;
				case 5:
					if (channel == 0) {
						// do something
					} else if (channel == 1) {
						// do something
					} else if (channel == 2) {
						dr.driveForward();
					} else if (channel == 3) {
						// do something
					}
					isPressed = false;
					break;
				case 6:
					if (channel == 0) {
						// do something
					} else if (channel == 1) {
						// do something
					} else if (channel == 2) {
						dr.turnRight();
					} else if (channel == 3) {
						// do something
					}
					isPressed = false;
					break;
				case 7:
					if (channel == 0) {
						// do something
					} else if (channel == 1) {
						// do something
					} else if (channel == 2) {
						dr.turnLeft();
					} else if (channel == 3) {
						// do something
					}
					isPressed = false;
					break;
				case 8:
					if (channel == 0) {
						// do something
					} else if (channel == 1) {
						// do something
					} else if (channel == 2) {
						dr.driveBackward();
					} else if (channel == 3) {
						// do something
					}
					isPressed = false;
					break;
				case 9:
					isPressed = false;
					while (beacon == 9) {
						beacon = checkerThread.getCommand();
					}
					checkerThread.changeChannel();
					break;

				default:
					dr.stop();
					isPressed = false;
					break;
				}
			} 
		}
		irSensorLeft.close();
		distance.interrupt();
		autopilot.interrupt();
		checkerThread.interrupt();
	}
}
