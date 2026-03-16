package Client;

import java.io.File;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.SourceDataLine;

public class SoundEffect {
	File audio_file;
	SourceDataLine source;
	AudioInputStream in;

	public SoundEffect(String fileName) {
		audio_file = new File(fileName);
		try {
			in = AudioSystem.getAudioInputStream(audio_file);
			AudioFormat format = in.getFormat();
			DataLine.Info info = new DataLine.Info(SourceDataLine.class, format);
			source = (SourceDataLine) AudioSystem.getLine(info);
			source.open(format);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public void play() {
		try {
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
		} catch (Exception e) {
		}
	}

}
