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
			}
		} catch (Exception e) {
			return;
		}
	}
	// polls distance sensor for information and shoots or turns around accordingly
	private void detectObstacle() {
		if (distance.distance() <= 40) {
			dr.turnLeft90();
		}
		/*else if (distance.distance() <= 100) {
			dr.stop();
			dr.shoot();
		}*/
	}

	public void stopAutopilot() {
		stop = !stop;
	}

}
