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
		// ��ʾ��ȡMediaʧ�ܣ�Ӧ����Label��ʾ�ģ����������������̨
		return mediaPlayer;
	}

	private final List<MediaPlayer> players = new ArrayList<>();
	private static final List<String> EXTENSIONS = Arrays.asList(".mp3");

	/**
	 * ���ݴ�����ļ�Ŀ¼����ʼ��MediaPlayer,���������и��������⣬��������Ӹ������࣬������5000�׸裬����Ҫ����1GB�Ļ��档
	 * 
	 * @param dir
	 *            �ļ�Ŀ¼
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
	 * ������Ӷ��ָ�ʽ���ļ���Ĭ������Ϊ.mp3.
	 * @param name �ļ���׺��������".mp3".
	 */
	public void addExtensionName(String name) {
		EXTENSIONS.add(name);
	}
}
