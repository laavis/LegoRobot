package pack;

import lejos.hardware.Sound;

public class MusicPlayer {

	public final static int[] PIANO = new int[]{4, 25, 500, 7000, 5};	
	
	public void PlayNote(char note, int howManyTimes, int forHowLong)
	{
		int frequency = 0;
		
		switch (note) {
		case 'a':
			frequency = 440;
			break;
		case 'b':
			frequency = 494;
			break;
		case 'c':
			frequency = 262;
			break;
		case 'd':
			frequency = 294;
			break;
		case 'e':
			frequency = 330;
			break;
		case 'f':
			frequency = 349;
			break;
		case 'g':
			frequency = 392;
			break;
		}
		
		int i = 0;
		while (i < howManyTimes)
		{
			Sound.playNote(PIANO, frequency, 300 * forHowLong);
			i++;
		}
	}
	
	
	public void PlaySong(String song)
	{
		if (song.equals("Ukko"))
		{
			//PHASE 1
			PlayNote('c', 3, 1);
			PlayNote('e', 1, 1);
			PlayNote('d', 3, 1);
			PlayNote('f', 1, 1);
			PlayNote('e', 2, 1);
			PlayNote('d', 2, 1);
			PlayNote('c', 1, 4);
			//END OF PHASE 1
			
			//PHASE 2
			PlayNote('e', 4, 1);
			PlayNote('g', 1, 2);
			PlayNote('f', 1, 2);
			PlayNote('d', 4, 1);
			PlayNote('f', 1, 2);
			PlayNote('e', 1, 2);
			//END OF PHASE 2
			
			//PHASE 3
			PlayNote('c', 3, 1);
			PlayNote('e', 1, 1);
			PlayNote('d', 3, 1);
			PlayNote('f', 1, 1);
			PlayNote('e', 2, 1);
			PlayNote('d', 2, 1);
			PlayNote('c', 1, 4);
			//END OF PHASE 3
		}
	}
}
