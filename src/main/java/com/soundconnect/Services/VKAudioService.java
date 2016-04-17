package com.soundconnect.Services;

import java.io.IOException;
import java.util.List;

import com.soundconnect.Beans.Audio;

public interface VKAudioService {
	public List<Audio> searchForAudio(String query) throws IOException;
}
