package com.music.business;

import com.music.entity.Music;

public interface PlayManage {
	/**
	 * Plays music function
	 * @param music present music
	 * @param url file url path
	 */
	public void play(Music music);
	
	/**
	 * Pause music function
	 * @param music present music
	 * @param url file url path
	 */
	public void pause(Music music);
	
	/**
	 * Stop music.
	 * @param music present music
	 * @param url file url path
	 */
	public void stop(Music music);
}
