package pack;

import lejos.hardware.lcd.LCD;
import lejos.hardware.sensor.EV3IRSensor;
import lejos.utility.Delay;

public class IRChecker extends Thread {
	private EV3IRSensor infraredSensorLeft;
	private int remoteCommand;
	private int channel = 2;
	private boolean chCh = false;

	public IRChecker(EV3IRSensor sensorLeft) {
		this.infraredSensorLeft = sensorLeft;
	}

	public void run() {
		try {
			while (true) {
				Thread.sleep(250);
				remoteCommand = infraredSensorLeft.getRemoteCommand(channel);
			}
		} catch (Exception e) {
			return;
		}
	}

	/** channel changer
	 */
	public void changeChannel() {
		chCh = false;
		LCD.drawString("channel Changeing", 0, 3);
		while (!chCh) { // waits for input on any channel for switching
			if (infraredSensorLeft.getRemoteCommand(0) > 0) {
				channel = 0;
				chCh = true;
			} else if (infraredSensorLeft.getRemoteCommand(1) > 0) {
				channel = 1;
				chCh = true;
			} else if (infraredSensorLeft.getRemoteCommand(2) > 0) {
				channel = 2;
				chCh = true;
			} else if (infraredSensorLeft.getRemoteCommand(3) > 0) {
				channel = 3;
				chCh = true;
			}
		}
		Delay.msDelay(250);
		LCD.clear(3);
		LCD.drawString("channel switched", 0, 3);
		LCD.clear(3);
	}

	public int getCommand() {
		return remoteCommand;
	}

	public int getChannel() {
		return channel;
	}
}
