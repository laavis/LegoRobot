package pack;

import lejos.hardware.Button;
import lejos.hardware.lcd.LCD;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3IRSensor;
import lejos.robotics.RegulatedMotor;
import lejos.utility.Delay;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		RegulatedMotor leftMotor = new EV3LargeRegulatedMotor(MotorPort.A);
		RegulatedMotor rightMotor = new EV3LargeRegulatedMotor(MotorPort.B);
		EV3IRSensor irSensor = new EV3IRSensor(SensorPort.S1);

		IRChecker checkerThread = new IRChecker(irSensor);
		checkerThread.start();
		Drive dr = new Drive(leftMotor,rightMotor);
		
		while(!Button.ESCAPE.isDown()) {
			int command = checkerThread.getCommand();
			if(command == 1) {
				LCD.drawString("Forward", 0, 4);
				dr.driveForward();
				Delay.msDelay(2000);
				LCD.clear();
			}
			else if(command == 2) {
				LCD.drawString("Backward", 0, 4);
				dr.driveBackward();
				Delay.msDelay(2000);
				LCD.clear();
			}
			else if(command == 3) {
				dr.spinLeft();
				LCD.drawString("SpinLeft", 0, 4);
				Delay.msDelay(2000);
				LCD.clear();
			}
			else if(command == 4) {
				dr.spinRight();
				LCD.drawString("SpinRight", 0, 4);
				Delay.msDelay(2000);
				LCD.clear();
			}
		}
		leftMotor.close();
		rightMotor.close();
		irSensor.close();
		checkerThread.interrupt();
	}

}
