package com.music.business.Impl;


import com.music.business.PlayManage;
import com.music.entity.Music;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class PlayManageLocalImpl implements PlayManage {
	
	private MediaPlayer mediaplayer;
	public void play(Music music) {
//			mediaplayer = MusicUtils.playSound(music.getUrl());
			
			mediaplayer.play();
	}

	public void pause(Music music) {
		mediaplayer.pause();
	}
	
	public void stop(Music music) {
		mediaplayer.stop();
	}

}
