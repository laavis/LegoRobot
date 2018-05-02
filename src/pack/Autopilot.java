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

	public Autopilot(Drive dr, DistanceIR distance, EV3TouchSensor touchSensorLeft, EV3TouchSensor touchSensorRight) {
		this.dr = dr;
		this.distance = distance;
		this.touchSensorLeft = touchSensorLeft;
		this.touchSensorRight = touchSensorRight;
		this.sampleLeft = new float[touchSensorLeft.sampleSize()];
		this.sampleRight = new float[touchSensorRight.sampleSize()];
	}

	public void run() {
		try {
			while (!Button.ESCAPE.isDown()) {
				LCD.drawString("AUTOPILOT", 0, 0);
				LCD.drawString("Distance: " + distance.distance(), 0, 6);
				LCD.drawString("Shoot count: " + shootCount, 0, 5);
				LCD.refresh();
				//LCD.clear();
				Thread.sleep(25);
				dr.driveForward();
				detectObstacle();
				
			}
		} catch (Exception e) {
			return;
		}
	}
	// polls distance sensor for information and shoots or turns around accordingly
	private void detectObstacle() {
		touchSensorLeft.fetchSample(sampleLeft, 0);
		touchSensorRight.fetchSample(sampleRight, 0);
		if(sampleLeft[0] == 1 || sampleRight[0] == 1) {
			dr.goBackward();
			dr.turnAround();
		}
		else if (distance.distance() <= 40) {
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
	
	private void kill() {
		dr.stop();
		dr.shoot();
		LCD.clear(3);
	}


	public void stopAutopilot() {
		stop = !stop;
	}

}
