package pack;

import lejos.robotics.RegulatedMotor;
import lejos.utility.Delay;

public class Drive {
	RegulatedMotor rightMotor;
	RegulatedMotor leftMotor;
	RegulatedMotor top;
	// create Drive object
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
	// both motors driving forward
	public void driveForward() {
		rightMotor.startSynchronization();
		rightMotor.forward();
		leftMotor.forward();
		rightMotor.endSynchronization();
		Delay.msDelay(50);
		rightMotor.stop(true);
		leftMotor.stop(true);
	}
	// both motors driving backwards
	public void driveBackward() {
		rightMotor.startSynchronization();
		rightMotor.backward();
		leftMotor.backward();
		rightMotor.endSynchronization();
		Delay.msDelay(50);
		rightMotor.stop(true);
		leftMotor.stop(true);
	}
	// spin left
	public void spinLeft() {
		rightMotor.forward();
		Delay.msDelay(50);
		rightMotor.stop(true);
	}
	// spin right
	public void spinRight() {
		leftMotor.forward();
		Delay.msDelay(50);
		leftMotor.stop(true);
	}
	// turn right
	public void turnRight() {
		rightMotor.startSynchronization();
		rightMotor.forward();
		leftMotor.backward();
		rightMotor.endSynchronization();
		Delay.msDelay(50);
		leftMotor.stop(true);
		rightMotor.stop(true);
	}
	// turn left
	public void turnLeft() {
		rightMotor.startSynchronization();
		rightMotor.backward();
		leftMotor.forward();
		rightMotor.endSynchronization();
		Delay.msDelay(50);
		leftMotor.stop(true);
		rightMotor.stop(true);
	}
	// turns left motor in reverse direction
	public void spinLeftBack() {
		rightMotor.backward();
	}
	// turns right motor in reverse direction
	public void spinRightBack() {
		leftMotor.backward();
	}
	// turn around
	public void turnAround() {
		rightMotor.startSynchronization();
		rightMotor.backward();
		leftMotor.forward();
		rightMotor.endSynchronization();
		Delay.msDelay(2000);
		leftMotor.stop(true);
		rightMotor.stop(true);
	}
	
	public void turnLeft90() {
		rightMotor.startSynchronization();
		rightMotor.backward();
		leftMotor.forward();
		rightMotor.endSynchronization();
		Delay.msDelay(750);
		leftMotor.stop(true);
		rightMotor.stop(true);
	}
	// continuously turns in place
	public void spin() {
		rightMotor.startSynchronization();
		rightMotor.forward();
		leftMotor.backward();
		rightMotor.endSynchronization();
		Delay.msDelay(50);
		leftMotor.stop(true);
		rightMotor.stop(true);
	}
	// drives backward for 50cm
	public void goBackward() {
		rightMotor.startSynchronization();
		rightMotor.backward();
		leftMotor.backward();
		rightMotor.endSynchronization();
		Delay.msDelay(750);
		rightMotor.stop(true);
		leftMotor.stop(true);
	}
	// shoots once
	public void shoot() {
		top.rotate(1080);
	}
	// shoots in reverse
	public void shootReverse() {
		top.rotate(-1080);
	}
	// stop the motors
	public void stop() {
		rightMotor.startSynchronization();
		rightMotor.stop(true);
		leftMotor.stop(true);
		rightMotor.endSynchronization();
	}

}
