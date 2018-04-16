package pack;

import lejos.robotics.RegulatedMotor;

public class Drive {
	RegulatedMotor mA;
	RegulatedMotor mD;
	
	public Drive(RegulatedMotor mA, RegulatedMotor mD) {
		this.mA = mA;
		this.mD = mD;
		mA.synchronizeWith(new RegulatedMotor[] { mD });
	}
	
	public void driveForward() {
		this.mA.setSpeed(900);
		this.mD.setSpeed(900);
		mA.startSynchronization();
		mA.forward();
		mD.forward();
		mA.endSynchronization();
	}

	public void driveBackward() {
		this.mA.setSpeed(900);
		this.mD.setSpeed(900);
		mA.startSynchronization();
		mA.backward();
		mD.backward();
		mA.endSynchronization();
	}
	
	public void spinLeft() {
		mD.stop();
		mA.forward();
	}
	
	public void spinRight() {
		mA.stop();
		mD.forward();
	}
	
	public void stop() {
		mA.startSynchronization();
		mA.stop();
		mD.stop();
		mA.endSynchronization();
	}

}
