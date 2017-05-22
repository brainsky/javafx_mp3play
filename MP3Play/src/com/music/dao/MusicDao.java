package com.music.dao;

import java.io.File;
import java.util.List;

import com.music.entity.Music;

public interface MusicDao {
	/**
	 * Add music function.
	 * @param file music file
	 * @return Music entity
	 */
	public Music addMusic(File file);
	
	/**
	 * Delete music function
	 * @param music music entity
	 */
	public void deleteMusic(Music music);

	public void addDirMusic(String dir);

	public List<Music> getMusices();
}
