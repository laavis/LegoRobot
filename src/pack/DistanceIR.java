package pack;

import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3IRSensor;
import lejos.robotics.SampleProvider;
import lejos.utility.Delay;

public class DistanceIR extends Thread {
	private EV3IRSensor infraredSensorRight;

	// private static EV3IRSensor ir1 = new EV3IRSensor(SensorPort.S4);

	public DistanceIR(EV3IRSensor sensorRight) {
		this.infraredSensorRight = sensorRight;
	}

	private static int HALF_SECOND = 500;

	public int getDistance() {

		final SampleProvider sp = infraredSensorRight.getDistanceMode();
		int distanceValue = 0;

		// Control loop

		float[] sample = new float[sp.sampleSize()];
		sp.fetchSample(sample, 0);
		distanceValue = (int) sample[0];

		Delay.msDelay(HALF_SECOND);
		return distanceValue;

	}

}
