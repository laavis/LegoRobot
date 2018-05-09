/**
* Autopilot class
* Robot drives independently and avoids obstacles
*
* @author  Sara Suviranta
* @since   16-4-2018
*/
package pack;

import lejos.hardware.lcd.LCD;
import lejos.hardware.sensor.EV3TouchSensor;
import lejos.hardware.Button;

public class Autopilot extends Thread {

	private Drive dr;
	private DistanceIR distance;
	private EV3TouchSensor touchSensorLeft;
	private EV3TouchSensor touchSensorRight;
	private boolean stop = false;
	private int shootCount = 0;
	private float[] sampleLeft;
	private float[] sampleRight;
	
	/**
	 * create Autopilot object
	 * @param dr drive object
	 * @param distance DistanceIR object
	 * @param touchSensorLeft This is the left touch sensor 
	 * @param touchSensorRight This is the right touch sensor 
	 * */
	public Autopilot(Drive dr, DistanceIR distance, EV3TouchSensor touchSensorLeft, EV3TouchSensor touchSensorRight) {
		this.dr = dr;
		this.distance = distance;
		this.touchSensorLeft = touchSensorLeft;
		this.touchSensorRight = touchSensorRight;
		this.sampleLeft = new float[touchSensorLeft.sampleSize()];
		this.sampleRight = new float[touchSensorRight.sampleSize()];
	}

	/** drives forward until it detects an obstacle */
	public void run() {
		try {
			while (!Button.ESCAPE.isDown()) {
				LCD.drawString("AUTOPILOT", 0, 0);
				LCD.drawInt(dr.getRotation(), 0, 1);
				LCD.drawString("Distance: " + distance.distance(), 0, 6);
				LCD.drawString("Shoot count: " + shootCount, 0, 5);
				LCD.refresh();
				Thread.sleep(25);
				dr.driveForward();
				if (dr.getRotation() > 2500) {
					dr.stop();
					dr.turnLeft90();
				} else {
					detectObstacle();
				}
			}
		} catch (Exception e) {
			return;
		}
	}

	/** polls distance sensor for information and shoots or turns around accordingly.
	* if either touch sensor is pressed back up and turn around */
	private void detectObstacle() {
		touchSensorLeft.fetchSample(sampleLeft, 0);
		touchSensorRight.fetchSample(sampleRight, 0);
		if (sampleLeft[0] == 1 || sampleRight[0] == 1) {
			dr.goBackward();
			dr.turnAround();
		} else if (distance.distance() <= 40) {
			dr.turnLeft90();
			LCD.drawString("DETECT", 0, 2);
			shootCount = 0;
		} else if (distance.distance() <= 100 && distance.distance() > 41 && shootCount == 0) {
			this.kill();
			shootCount++;
			dr.turnLeft90();
		}
		LCD.clear(2);
	}

	/** stops robot & shoots */
	private void kill() {
		dr.stop();
		dr.shoot();
		LCD.clear(3);
	}
	
	/** stops autopilot */
	public void stopAutopilot() {
		stop = !stop;
	}

}
