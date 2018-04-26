package pack;

import lejos.hardware.lcd.LCD;
import lejos.hardware.Button;

public class Autopilot extends Thread {

	private Drive dr;
	private DistanceIR distance;
	private boolean stop = false;

	public Autopilot(Drive dr, DistanceIR distance) {
		this.dr = dr;
		this.distance = distance;
	}

	public void run() {
		try {
			while (!Button.ESCAPE.isDown()) {
				LCD.drawString("AUTOPILOT", 0, 0);
				Thread.sleep(25);
				dr.driveForward();
				detectObstacle();
				shoot();
			}
		} catch (Exception e) {
			return;
		}
	}
	// polls distance sensor for information and shoots or turns around accordingly
	private void detectObstacle() {
		if (distance.distance() <= 30) {
			dr.turnLeft90();
			LCD.drawString("DETECT", 0, 2);
		}
		LCD.clear(2);
	}
	
	private void shoot() {
		if (distance.distance() <= 100 && distance.distance() > 31) {
			dr.stop();
			dr.shoot();
			LCD.drawString("SHOOT", 0, 3);
		}
		LCD.clear(3);
	}


	public void stopAutopilot() {
		stop = !stop;
	}

}
