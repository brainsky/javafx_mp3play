package com.music.test;

import static org.junit.Assert.*;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.Iterator;
import java.util.List;


import org.junit.Test;

import com.mpatric.mp3agic.Mp3File;
import com.music.dao.Impl.MusicDaoImpl;
import com.music.entity.Music;
import com.music.utils.ConstUtils;
import com.music.utils.DaoUtils;
import com.music.view.controller.RootController;
import com.music.view.controller.StageController;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class MusicDaoImplTest {
	
	private MusicDaoImpl md = new MusicDaoImpl();
	
	@Test
	public void testAddMusic() throws Exception {
		String fileName = "resource/MM.mp3";
		String filepath ="file:///" + System.getProperty("user.dir").replace('\\', '/') + "/" + fileName;
		URL url = new URL(filepath);
		File file = new File(url.toURI());
		if(file.exists()){
			Music music = md.addMusic(file);
			Double time = DaoUtils.millis2TimeOnSecond(music.getTime(),2);
//			System.out.println(music.getTitle());
//			System.out.println(music.getSinger());
//			System.out.println(music.getSize());
//			System.out.println(music.getSpecial());
//			System.out.println(music.getLyric());
			System.out.println(time);
			System.out.println(music.getTime());
		}else{
			System.out.print("not found");
		}
//		String dir = "D:\\CloudMusic";
//		md.addDirMusic(dir);
//		List<Music> musiclist = md.getMusices();
	}

	@Test
	public void testDeleteMusic() throws Exception {
		
	}

}
