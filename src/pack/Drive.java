package pack;

import lejos.hardware.lcd.LCD;
import lejos.robotics.RegulatedMotor;
import lejos.utility.Delay;

public class Drive {
	RegulatedMotor rightMotor;
	RegulatedMotor leftMotor;
	RegulatedMotor top;

	public Drive(RegulatedMotor rightMotor, RegulatedMotor leftMotor, RegulatedMotor top) {
		this.rightMotor = rightMotor;
		this.leftMotor = leftMotor;
		int maxSpeed = (int)this.rightMotor.getMaxSpeed();
		this.rightMotor.setSpeed(maxSpeed);
		this.leftMotor.setSpeed(maxSpeed);
		this.top = top;
		this.top.setSpeed(900);
		rightMotor.synchronizeWith(new RegulatedMotor[] { leftMotor });
	}

	public void driveForward() {
		rightMotor.startSynchronization();
		rightMotor.forward();
		leftMotor.forward();
		rightMotor.endSynchronization();
		Delay.msDelay(50);
		rightMotor.stop(true);
		leftMotor.stop(true);
	}

	public void driveBackward() {
		rightMotor.startSynchronization();
		rightMotor.backward();
		leftMotor.backward();
		rightMotor.endSynchronization();
		Delay.msDelay(50);
		rightMotor.stop(true);
		leftMotor.stop(true);
	}

	public void spinLeft() {
		rightMotor.forward();
		Delay.msDelay(50);
		rightMotor.stop(true);
	}

	public void spinRight() {
		leftMotor.forward();
		Delay.msDelay(50);
		leftMotor.stop(true);
	}
	
	public void turnRight() {
		rightMotor.startSynchronization();
		rightMotor.forward();
		leftMotor.backward();
		rightMotor.endSynchronization();
		Delay.msDelay(50);
		leftMotor.stop(true);
		rightMotor.stop(true);
	}
	public void turnLeft() {
		rightMotor.startSynchronization();
		rightMotor.backward();
		leftMotor.forward();
		rightMotor.endSynchronization();
		Delay.msDelay(50);
		leftMotor.stop(true);
		rightMotor.stop(true);
	}
	
	public void spinLeftBack() {
		rightMotor.backward();
	}
	
	public void spinRightBack() {
		leftMotor.backward();
	}
	
	public void turnAround() {
		rightMotor.rotate(180);
		leftMotor.rotate(-180);
	}

	public void shoot() {
		top.rotate(1080);
	}
	
	public void shootReverse() {
		top.rotate(-1080);
	}

	public void stop() {
		rightMotor.startSynchronization();
		rightMotor.stop(true);
		leftMotor.stop(true);
		rightMotor.endSynchronization();
	}

}
