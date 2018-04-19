package pack;

import lejos.robotics.RegulatedMotor;
import lejos.utility.Delay;

public class Drive {
	RegulatedMotor rightMotor;
	RegulatedMotor leftMotor;
	RegulatedMotor top;

	public Drive(RegulatedMotor rightMotor, RegulatedMotor leftMotor, RegulatedMotor top) {
		this.rightMotor = rightMotor;
		this.leftMotor = leftMotor;
		this.top = top;
		this.top.stop();
		rightMotor.synchronizeWith(new RegulatedMotor[] { leftMotor });
	}

	public void driveForward() {
		this.rightMotor.setSpeed(900);
		this.leftMotor.setSpeed(900);
		rightMotor.startSynchronization();
		rightMotor.forward();
		leftMotor.forward();
		Delay.msDelay(250);
		rightMotor.stop();
		leftMotor.stop();
		rightMotor.endSynchronization();
	}

	public void driveBackward() {
		this.rightMotor.setSpeed(900);
		this.leftMotor.setSpeed(900);
		rightMotor.startSynchronization();
		rightMotor.backward();
		leftMotor.backward();
		Delay.msDelay(250);
		rightMotor.stop();
		leftMotor.stop();
		rightMotor.endSynchronization();
	}

	public void spinLeft() {
		rightMotor.rotate(360);
	}

	public void spinRight() {
		leftMotor.rotate(360);
	}
	
	public void turnRight() {
		rightMotor.startSynchronization();
		rightMotor.forward();
		leftMotor.backward();
		Delay.msDelay(250);
		this.stop();
		rightMotor.endSynchronization();
	}
	public void turnLeft() {
		rightMotor.startSynchronization();
		rightMotor.backward();
		leftMotor.forward();
		Delay.msDelay(250);
		this.stop();
		rightMotor.endSynchronization();
	}
	
	public void spinLeftBack() {
		rightMotor.rotate(-360);
	}
	
	public void spinRightBack() {
		leftMotor.rotate(-360);
	}

	public void shoot() {
		top.rotate(720);
	}

	public void stop() {
		rightMotor.startSynchronization();
		rightMotor.stop();
		leftMotor.stop();
		rightMotor.endSynchronization();
	}

}
