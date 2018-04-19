package pack;

import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3IRSensor;
import lejos.robotics.SampleProvider;
import lejos.utility.Delay;

public class DistanceIR {

	private static EV3IRSensor ir1 = new EV3IRSensor(SensorPort.S4);

	private static int HALF_SECOND = 500;

	public static void main(String[] args) {

		final SampleProvider sp = ir1.getDistanceMode();
		int distanceValue = 0;

		// Control loop
		final int iteration_threshold = 50;
		for (int i = 0; i <= iteration_threshold; i++) {

			float[] sample = new float[sp.sampleSize()];
			sp.fetchSample(sample, 0);
			distanceValue = (int) sample[0];

			System.out.println("Iteration: {}" + i);
			System.out.println("Distance: {}" + distanceValue);

			Delay.msDelay(HALF_SECOND);
		}

	}

}
