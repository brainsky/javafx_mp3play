package com.music.dao.Impl;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.mpatric.mp3agic.ID3v1;
import com.mpatric.mp3agic.ID3v1Tag;
import com.mpatric.mp3agic.ID3v2;
import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.Mp3File;
import com.mpatric.mp3agic.UnsupportedTagException;
import com.music.dao.MusicDao;
import com.music.entity.Music;

public class MusicDaoImpl implements MusicDao {

	public Music addMusic(File file) {
		// new File("file:///" + file.getPath().replace("\\", "/").replaceAll("
		// ", "20%"))
		Music music = null;
		if (file.exists()) {
			try {
				Mp3File mp3file = new Mp3File(file);
				Long Seconds = mp3file.getLengthInMilliseconds();
				String url = file.toURI().toURL().toString();

				if (mp3file.hasId3v1Tag()) {
					ID3v1 id3v1 = mp3file.getId3v1Tag();
					music = new Music(url, id3v1.getTitle(), id3v1.getArtist(), id3v1.getAlbum(), mp3file.getLength(),
							mp3file.getBitrate(), Seconds);

				} else if (mp3file.hasId3v2Tag()) {
					ID3v2 id3v2 = mp3file.getId3v2Tag();
					music = new Music(url, id3v2.getTitle(), id3v2.getArtist(), id3v2.getAlbum(), mp3file.getLength(),
							mp3file.getBitrate(), id3v2.getLyrics(), Seconds);
				}
			} catch (UnsupportedTagException e) {
				e.printStackTrace();
			} catch (InvalidDataException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return music;
	}

	public void deleteMusic(Music music) {
		if (music.getUrl() != null) {
			try {
				URL url = new URL(music.getUrl());
				File file = new File(url.toURI());
				file.delete();
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
	}

	private static final List<String> EXTENSIONS = Arrays.asList(".mp3");
	private List<Music> musices = new ArrayList<>();

	public List<Music> getMusices() {
		return musices;
	}

	public void addDirMusic(String dirpath) {
		File dir = new File(dirpath);
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
			musices.add(addMusic(file));
	}

	public void addExtensionName(String name) {
		EXTENSIONS.add(name);
	}
}
