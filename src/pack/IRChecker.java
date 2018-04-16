package pack;

import lejos.hardware.sensor.EV3IRSensor;

public class IRChecker extends Thread {
	private EV3IRSensor infraredSensor;
	private int remoteCommand;

	public IRChecker(EV3IRSensor sensor) {
		this.infraredSensor = sensor;
	}

	public void run() {
		try {
			while (true) {
				Thread.sleep(50);
				remoteCommand = infraredSensor.getRemoteCommand(0);
			}
		} catch (Exception e) {
			return;
		}
		
	}
	
	public int getCommand() {
		return remoteCommand;
	}
}
