package pack;

import lejos.hardware.sensor.EV3IRSensor;

public class IRChecker extends Thread {
	private EV3IRSensor infraredSensorLeft;
	private int remoteCommand;

	public IRChecker(EV3IRSensor sensorLeft) {
		this.infraredSensorLeft = sensorLeft;
	}

	public void run() {
		try {
			while (true) {
				Thread.sleep(50);
				remoteCommand = infraredSensorLeft.getRemoteCommand(2);
			}
		} catch (Exception e) {
			return;
		}
	}

	public int getCommand() {
		return remoteCommand;
	}
}
