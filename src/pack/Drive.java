/**
* Class Drive is responsible for controlling the 3 motors on the robot.
* Contains methods for most functions that the motors can do.
* Majority of methods are intended to be called repeatedly for continuous movement.
* @author Niklas Kiuru
* @since 9-4-2018
*/
package pack;

import lejos.robotics.RegulatedMotor;
import lejos.utility.Delay;

public class Drive {
	private RegulatedMotor rightMotor;
	private RegulatedMotor leftMotor;
	private RegulatedMotor top;
	private int rotationRight;
	private int maxSpeed = 750;

	/** create Drive object */
	public Drive(RegulatedMotor rightMotor, RegulatedMotor leftMotor, RegulatedMotor top) {
		this.rightMotor = rightMotor;
		this.leftMotor = leftMotor;
		this.rightMotor.setSpeed(maxSpeed);
		this.leftMotor.setSpeed(maxSpeed);
		this.top = top;
		this.top.setSpeed(900);
		rightMotor.synchronizeWith(new RegulatedMotor[] { leftMotor });
	}

	/** both motors driving forward */
	public void driveForward() {
		rightMotor.startSynchronization();
		rightMotor.forward();
		leftMotor.forward();
		rightMotor.endSynchronization();
		Delay.msDelay(50);
		rightMotor.stop(true);
		leftMotor.stop(true);
	}

	public int getRotation() {
		rotationRight = rightMotor.getTachoCount();
		return rotationRight;
	}

	/** both motors driving backwards */
	public void driveBackward() {
		rightMotor.startSynchronization();
		rightMotor.backward();
		leftMotor.backward();
		rightMotor.endSynchronization();
		Delay.msDelay(50);
		rightMotor.stop(true);
		leftMotor.stop(true);
	}

	/** spin left motor */
	public void spinLeft() {
		rightMotor.forward();
		Delay.msDelay(50);
		rightMotor.stop(true);
	}

	/** spin right motor */
	public void spinRight() {
		leftMotor.forward();
		Delay.msDelay(50);
		leftMotor.stop(true);
	}

	/** turn right using both motors*/
	public void turnRight() {
		rightMotor.startSynchronization();
		rightMotor.forward();
		leftMotor.backward();
		rightMotor.endSynchronization();
		Delay.msDelay(50);
		leftMotor.stop(true);
		rightMotor.stop(true);
	}

	/** turn left using both motors*/
	public void turnLeft() {
		rightMotor.startSynchronization();
		rightMotor.backward();
		leftMotor.forward();
		rightMotor.endSynchronization();
		Delay.msDelay(50);
		leftMotor.stop(true);
		rightMotor.stop(true);
	}

	/** turns left motor in reverse direction */
	public void spinLeftBack() {
		rightMotor.backward();
	}

	/** turns right motor in reverse direction */
	public void spinRightBack() {
		leftMotor.backward();
	}

	/** turn around */
	public void turnAround() {
		rightMotor.startSynchronization();
		rightMotor.backward();
		leftMotor.forward();
		rightMotor.endSynchronization();
		Delay.msDelay(1750);
		leftMotor.stop(true);
		rightMotor.stop(true);
	}

	/** turns left 90 degrees */
	public void turnLeft90() {
		rightMotor.startSynchronization();
		rightMotor.backward();
		leftMotor.forward();
		rightMotor.endSynchronization();
		Delay.msDelay(750);
		leftMotor.stop(true);
		rightMotor.stop(true);
		rightMotor.resetTachoCount();
	}

	/** continuously turns in place */
	public void spin() {
		rightMotor.startSynchronization();
		rightMotor.forward();
		leftMotor.backward();
		rightMotor.endSynchronization();
		Delay.msDelay(50);
		leftMotor.stop(true);
		rightMotor.stop(true);
	}

	/** drives backward for 50cm */
	public void goBackward() {
		rightMotor.startSynchronization();
		rightMotor.backward();
		leftMotor.backward();
		rightMotor.endSynchronization();
		Delay.msDelay(750);
		rightMotor.stop(true);
		leftMotor.stop(true);
	}

	/** shoots once */
	public void shoot() {
		top.rotate(1080);
	}

	/** shoots in reverse */
	public void shootReverse() {
		top.rotate(-1080);
	}

	/** stop the motors */
	public void stop() {
		rightMotor.startSynchronization();
		rightMotor.stop(true);
		leftMotor.stop(true);
		rightMotor.endSynchronization();
	}

}
