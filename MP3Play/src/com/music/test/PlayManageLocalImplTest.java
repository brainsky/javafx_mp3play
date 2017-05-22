/**
 * 
 */
package com.music.test;

import static org.junit.Assert.*;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;


import org.junit.Test;

import com.music.business.PlayManage;
import com.music.business.Impl.PlayManageLocalImpl;
import com.music.dao.MusicDao;
import com.music.dao.Impl.MusicDaoImpl;
import com.music.entity.Music;
import com.music.utils.SingleMediaPlayer;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

/**
 * @author Administrator
 *
 */
public class PlayManageLocalImplTest extends Application{
	private MusicDaoImpl md = new MusicDaoImpl();
	@Test
	public void testGetMediaPlayer() throws Exception{
		String fileName = "resource/MM.mp3";
		String filepath ="file:///" + System.getProperty("user.dir").replace('\\', '/') + "/" + fileName;
		URL url = new URL(filepath);
		File file = new File(url.toURI());
		Music music = md.addMusic(file);	
//		MediaPlayer mediaPlayer = SingleMediaPlayer.getMediaPlayer(music.getUrl());
//		mediaPlayer.play();
	}
	@Override
	public void start(Stage primaryStage) throws Exception {
		testGetMediaPlayer();
	}
}
