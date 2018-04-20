package pack;

import lejos.hardware.sensor.EV3IRSensor;

public class IRChecker extends Thread {
	private EV3IRSensor infraredSensorLeft;
	private int remoteCommand;
	private int channel = 2;

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
	
	public void changeChannel(int channel) {
			this.channel = channel;
	}

	public int getCommand() {
		return remoteCommand;
	}
	
	public int getChannel() {
		return channel;
	}
}
