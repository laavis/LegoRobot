/**
* Measures the distance and returns it as an integer
* Using left IR sensor
*
* @author  Sara Suviranta
* @since   9-4-2018
*/
package pack;

import lejos.hardware.ev3.LocalEV3;
import lejos.hardware.port.Port;
import lejos.hardware.sensor.EV3IRSensor;
import lejos.robotics.SampleProvider;
import lejos.robotics.filter.MeanFilter;


public class DistanceIR extends Thread {
	private SampleProvider average;
	private SampleProvider sampler;
	private EV3IRSensor sensor;
	private boolean stop = false;
	private int distance;

	

	/** create DistanceIR object */
	public DistanceIR() {
		Port port = LocalEV3.get().getPort("S1");
		sensor = new EV3IRSensor(port);
		sampler = sensor.getMode("Distance");		
		this.setDaemon(true);
	}
	/** return distance */
	public synchronized int distance() { 
		return distance; 
	}
	/** stop sensor */
	public void stopSensor() {
		stop = true;
	}

	/** this runs until stopSensor() is called
	 * mean filtering raw distance data */
	public void run() {
		float[] sample = new float[sampler.sampleSize()];
	try {
		while(!stop) {
			Thread.sleep(250);
			sampler.fetchSample(sample, 0);
			average = new MeanFilter(sampler, 5);
			average.fetchSample(sample, 0);
			distance = (int)sample[0];
		}
	} catch (Exception e) { return; } 
		sensor.close();
	}
}
