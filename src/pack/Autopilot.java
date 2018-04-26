package pack;

import lejos.hardware.lcd.LCD;
import lejos.hardware.Button;

public class Autopilot extends Thread {

	private Drive dr;
	private DistanceIR distance;
	private boolean stop = false;
	private int shootCount = 0;

	public Autopilot(Drive dr, DistanceIR distance) {
		this.dr = dr;
		this.distance = distance;
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
		if (distance.distance() <= 40 && shootCount > 0) {
			dr.turnLeft90();
			LCD.drawString("DETECT", 0, 2);
			shootCount = 0;
		} else if (distance.distance() <= 100 && distance.distance() > 41 && shootCount == 0) {
			kill();
			shootCount++;
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
