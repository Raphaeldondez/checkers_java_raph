package client;

import java.io.File;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.SourceDataLine;

public class sound_effect {
	File audio_file;

	public sound_effect(String filename) {
		audio_file = new File(filename);
		try {
			AudioInputStream in = AudioSystem.getAudioInputStream(audio_file);
			AudioFormat format = in.getFormat();
			DataLine.Info info = new DataLine.Info(SourceDataLine.class, format);
			SourceDataLine source = (SourceDataLine) AudioSystem.getLine(info);
			source.open(format);
			source.start();
			int bytes_read = 0;
			byte[] audio_data = new byte[2000];
			while (bytes_read > -1) {
				bytes_read = in.read(audio_data);
				if (bytes_read >= 0)
					source.write(audio_data, 0, bytes_read);
			}
			source.drain();
			source.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}
