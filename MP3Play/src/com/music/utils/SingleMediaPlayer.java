package com.music.utils;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class SingleMediaPlayer {

	private MediaPlayer currentplayer;
	private MediaPlayer nextplayer;
	private MediaPlayer preplayer;
	// private static volatile SingleMediaPlayer singleMediaPlayer = null;
	//
	// public static SingleMediaPlayer getInstance(){
	// if(singleMediaPlayer == null){
	// synchronized (SingleMediaPlayer.class) {
	// if(singleMediaPlayer == null){
	// singleMediaPlayer = new SingleMediaPlayer();
	// }
	// }
	// }
	// return singleMediaPlayer;
	// }
	private static MediaPlayer mediaPlayer;

	public MediaPlayer getMediaPlayer(String fileName) {
		// Media m = new Media("file:///" +
		// System.getProperty("user.dir").replace('\\', '/') + "/" + fileName);
		Media m = new Media(fileName);
		mediaPlayer = new MediaPlayer(m);
//		mediaPlayer = new javax.media.bean.playerbean.MediaPlayer();
//		mediaPlayer.setMediaLocation(fileName);
		// 提示读取Media失败，应该用Label显示的，现在先输出到控制台
		return mediaPlayer;
	}

	private final List<MediaPlayer> players = new ArrayList<>();
	private static final List<String> EXTENSIONS = Arrays.asList(".mp3");

	/**
	 * 根据传入的文件目录，初始化MediaPlayer,但是这里有个性能问题，当我们添加歌曲过多，即大于5000首歌，则需要至少1GB的缓存。
	 * 
	 * @param dir
	 *            文件目录
	 */
	public void initMediaPlayer(File dir) {
		for (File file : dir.listFiles(new FileFilter() {

			@Override
			public boolean accept(File pathname) {
				if (pathname.isDirectory()) {
					return false;
				}
				for (String extensions : EXTENSIONS) {
					if (pathname.getName().endsWith(extensions)) {
						return true;
					}
				}
				return false;
			}

		}))
			players.add(getMediaPlayer("file:///" + (dir + "\\" + file).replace("\\", "/").replaceAll(" ", "%20")));
		for (int i = 0; i < players.size(); i++) {
			currentplayer = players.get(i);
			nextplayer = players.get((i + 1) % players.size());
			if (i == 0) {
				preplayer = players.get(0);
			} else {
				preplayer = players.get((i - 1) % players.size());
			}

		}
	}

	public List<MediaPlayer> getListMediaPlayer() {
		return players;
	}

	public MediaPlayer getCurrentplayer() {
		return currentplayer;
	}

	public MediaPlayer getNextplayer() {
		return nextplayer;
	}

	public MediaPlayer getPreplayer() {
		return preplayer;
	}

	public void setCurrentplayer(MediaPlayer currentplayer) {
		this.currentplayer = currentplayer;
	}

	public void setNextplayer(MediaPlayer nextplayer) {
		this.nextplayer = nextplayer;
	}

	public void setPreplayer(MediaPlayer preplayer) {
		this.preplayer = preplayer;
	}
	/**
	 * 用来添加多种格式的文件，默认设置为.mp3.
	 * @param name 文件后缀名，类似".mp3".
	 */
	public void addExtensionName(String name) {
		EXTENSIONS.add(name);
	}
}
