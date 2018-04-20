package pack;

import lejos.hardware.lcd.LCD;

public class Autopilot extends Thread{
	
	private Drive dr;
	private DistanceIR distance;

	public Autopilot (Drive dr, DistanceIR distance) {
		this.dr = dr;
		this.distance = distance;
	}
	
	public void run() {
		try {
			while (true) {
				LCD.drawString("AUTOPILOT", 0, 0);
				Thread.sleep(250);
				dr.driveForward();
				detectObstacle();
			}
		} catch (Exception e) {
			return;
		}
	}
	
	private void detectObstacle() {
		if(distance.distance() <= 30) {
			dr.turnAround();
		}
	}
}
