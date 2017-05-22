package com.music.thread;

import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class InitMediaPlayerService extends Service<MediaPlayer> {
	String fileName;
	MediaPlayer mediaPlayer;
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public MediaPlayer getMediaPlayer(){
		return mediaPlayer;
	}
	@Override
	protected Task<MediaPlayer> createTask() {
		return new Task<MediaPlayer>(){
			@Override
			protected MediaPlayer call() throws Exception {
				Media m = new Media(fileName);			
				mediaPlayer = new MediaPlayer(m);
				return mediaPlayer;
			}		
		};
		
	}

}
