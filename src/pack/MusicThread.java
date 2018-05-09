/**
* Class MusicThread is responsible for playing music.
* Contains methods that enable music to be played.
* This class is a thread and runs independently from the main program.
* Values for the PIANO variable and the matching frequencies for the notes were taken from the Internet 
* @author Miikka Oksanen
* @since 9-4-2018
*/
package pack;

import lejos.hardware.Button;
import lejos.hardware.Sound;
import lejos.hardware.lcd.LCD;

public class MusicThread extends Thread {
	
	private String song = null;
	
	//Instrument for playing tunes. Adjust values in array to fine tune sound
	private final static int[] PIANO = new int[]{4, 25, 500, 7000, 5};
	
	public void run() {
		try {
			//SELECT SONG + PRESS ENTER + PRESS 1 == ???? V PROFIT
			while (!Button.ENTER.isDown()) {					
				if (song != null) {
					Sound.setVolume(10);
					LCD.drawString("Playing " + song, 0, 0);
					PlaySong(song);
				}
			}
		} catch (Exception e) {
			return;
		}
		
	}
		
	//Expands on the premade method PlayNote.
	private void PlayNote(char note, int howManyTimes, double forHowLong, int scale) {
		
		int frequency = 0;
		
		//Transforms notes to frequencies so that the machine can play them.
		switch (note) {
		case 'a':
			if (scale == 4) {
				frequency = 440;
			}
			else if (scale == 5) {
				frequency = 880;
			}
			break;
		case 'b':
			if (scale == 4) {
				frequency = 494;
			}
			else if (scale == 5) {
				frequency = 988;
			}
			break;
		case 'c':
			if (scale == 4) {
				frequency = 262;
			}
			else if (scale == 5) {
				frequency = 523;
			}
			break;
		case 'd':
			if (scale == 4) {
				frequency = 294;
			}
			else if (scale == 5) {
				frequency = 587;
			}
			break;
		case 'e':
			if (scale == 4) {
				frequency = 330;
			}
			else if (scale == 5) {
				frequency = 659;
			}
			break;
		case 'f':
			if (scale == 4) {
				frequency = 349;
			}
			else if (scale == 5) {
				frequency = 698;
			}
			break;
		case 'g':
			if (scale == 4) {
				frequency = 392;
			}
			else if (scale == 5) {
				frequency = 784;
			}
			break;
		default:
			break;
		}
		
		//Will stop playing if ENTER button is pressed for a short while.
		int i = 0;
		while (i < howManyTimes && !Button.ENTER.isDown()) {
			Sound.playNote(PIANO, frequency, (int)Math.round(300 * forHowLong));
			i++;
		}
	}
	
	/** Credit for song notes goes to Alex */
	public void PlaySong(String song) {
		if (song.equals("Ukko")) {
			//PHASE 1			
			PlayNote('c', 3, 1, 4);
			PlayNote('e', 1, 1, 4);
			PlayNote('d', 3, 1, 4);
			PlayNote('f', 1, 1, 4);
			PlayNote('e', 2, 1, 4);
			PlayNote('d', 2, 1, 4);
			PlayNote('c', 1, 4, 4);
			//END OF PHASE 1
			
			//PHASE 2
			PlayNote('e', 4, 1, 4);
			PlayNote('g', 1, 2, 4);
			PlayNote('f', 1, 2, 4);
			PlayNote('d', 4, 1, 4);
			PlayNote('f', 1, 2, 4);
			PlayNote('e', 1, 2, 4);
			//END OF PHASE 2
			
			//PHASE 3
			PlayNote('c', 3, 1, 4);
			PlayNote('e', 1, 1, 4);
			PlayNote('d', 3, 1, 4);
			PlayNote('f', 1, 1, 4);
			PlayNote('e', 2, 1, 4);
			PlayNote('d', 2, 1, 4);
			PlayNote('c', 1, 4, 4);
			//END OF PHASE 3
		}
		
		if (song.equals("Shelter")) {
			//PHASE 1
			PlayNote('c', 1, 0.75, 5);
			PlayNote('a', 1, 0.19, 5);
			PlayNote('g', 1, 0.375, 5);
			PlayNote('c', 1, 1.6875, 5);
			//END OF PHASE 1
			
			//PHASE 2
			PlayNote('c', 1, 0.75, 5);
			PlayNote('a', 1, 0.19, 5);
			PlayNote('g', 1, 0.375, 5);
			PlayNote('h', 1, 0.9375, 4);
			PlayNote('d', 1, 0.375, 5);
			PlayNote('c', 1, 0.375, 5);
			//END OF PHASE 2
			
			//PHASE 3
			PlayNote('c', 1, 0.75, 5);
			PlayNote('a', 1, 0.19, 5);
			PlayNote('g', 1, 0.375, 5);
			PlayNote('c', 1, 1.3125, 5);
			PlayNote('e', 1, 0.375, 5);
			//END OF PHASE 3
			
			//PHASE 4
			PlayNote('e', 1, 0.25, 5);
			PlayNote('c', 1, 0.25, 5);
			PlayNote('d', 1, 0.25, 5);
			
			PlayNote('e', 1, 0.25, 5);
			PlayNote('c', 1, 0.25, 5);
			PlayNote('d', 1, 0.25, 5);
			
			PlayNote('e', 1, 0.25, 5);
			PlayNote('c', 1, 0.25, 5);
			PlayNote('d', 1, 0.25, 5);
			
			PlayNote('e', 1, 0.25, 5);
			PlayNote('c', 1, 0.25, 5);
			PlayNote('d', 1, 0.25, 5);
			//END OF PHASE 4
			
			//PHASE 5
			PlayNote('c', 1, 0.75, 5);
			PlayNote('a', 1, 0.19, 5);
			PlayNote('g', 1, 0.375, 5);
			PlayNote('c', 1, 1.6875, 5);
			//END OF PHASE 5
			
			//PHASE 6
			PlayNote('c', 1, 0.75, 5);
			PlayNote('a', 1, 0.19, 5);
			PlayNote('g', 1, 0.375, 5);
			PlayNote('d', 1, 0.9375, 5);
			PlayNote('e', 1, 0.375, 5);
			PlayNote('g', 1, 0.375, 5);
			//END OF PHASE 6
			
			//PHASE 7
			PlayNote('g', 1, 0.75, 5);
			PlayNote('a', 1, 0.19, 5);
			PlayNote('g', 1, 0.375, 5);
			PlayNote('c', 1, 1.3125, 5);
			PlayNote('e', 1, 0.375, 5);
			//END OF PHASE 7
			
			//PHASE 8
			PlayNote('e', 1, 0.25, 5);
			PlayNote('c', 1, 0.25, 5);
			PlayNote('d', 1, 0.25, 5);
			
			PlayNote('e', 1, 0.25, 5);
			PlayNote('c', 1, 0.25, 5);
			PlayNote('d', 1, 0.25, 5);
			
			PlayNote('e', 1, 0.25, 5);
			PlayNote('c', 1, 0.25, 5);
			PlayNote('d', 1, 0.25, 5);
			
			PlayNote('e', 1, 0.25, 5);
			PlayNote('c', 1, 0.25, 5);
			PlayNote('d', 1, 0.25, 5);
			PlayNote('f', 1, 0.75, 4);
			//END OF PHASE 8
			
		}
	}
	
	
	public void setSong(String song) {
		this.song = song;
	}

}
