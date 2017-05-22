package com.music.entity;

import java.io.Serializable;

import com.music.utils.ConstUtils;
import com.music.utils.DaoUtils;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Music implements Serializable {
	private final StringProperty title;
	private final StringProperty singer;
	private final StringProperty special;
	private final LongProperty size;
	private StringProperty lyric;
	private final StringProperty url;
	private final IntegerProperty bitrate;
	private final LongProperty time;

	public Music(String url, String title, String singer, String special, Long size, Integer bitrate, String lyric,
			Long time) {
		super();
		this.url = new SimpleStringProperty(url);
		this.title = new SimpleStringProperty(title);
		this.singer = new SimpleStringProperty(singer);
		this.special = new SimpleStringProperty(special);
		this.size = new SimpleLongProperty(size);
		this.bitrate = new SimpleIntegerProperty(bitrate);
		this.lyric = new SimpleStringProperty(lyric);
		this.time = new SimpleLongProperty(time);
	}

	public Music(String url, String title, String singer, String special, Long size, Integer bitrate, Long time) {
		super();
		this.url = new SimpleStringProperty(url);
		this.title = new SimpleStringProperty(title);
		this.singer = new SimpleStringProperty(singer);
		this.special = new SimpleStringProperty(special);
		this.size = new SimpleLongProperty(size);
		this.bitrate = new SimpleIntegerProperty(bitrate);
		this.time = new SimpleLongProperty(time);
	}

	public final StringProperty titleProperty() {
		return this.title;
	}

	public final String getTitle() {
		return this.titleProperty().get();
	}

	public final void setTitle(final String title) {
		this.titleProperty().set(title);
	}

	public final StringProperty singerProperty() {
		return this.singer;
	}

	public final String getSinger() {
		return this.singerProperty().get();
	}

	public final void setSinger(final String singer) {
		this.singerProperty().set(singer);
	}

	public final StringProperty specialProperty() {
		return this.special;
	}

	public final String getSpecial() {
		return this.specialProperty().get();
	}

	public final void setSpecial(final String special) {
		this.specialProperty().set(special);
	}

	public final StringProperty urlProperty() {
		return this.url;
	}

	public final String getUrl() {
		return this.urlProperty().get();
	}

	public final void setUrl(final String url) {
		this.urlProperty().set(url);
	}

	public final LongProperty sizeProperty() {
		return this.size;
	}

	public final long getSize() {
		return this.sizeProperty().get();
	}

	public final void setSize(final long size) {
		this.sizeProperty().set(size);
	}

	public final IntegerProperty bitrateProperty() {
		return this.bitrate;
	}

	public final int getBitrate() {
		return this.bitrateProperty().get();
	}

	public final void setBitrate(final int bitrate) {
		this.bitrateProperty().set(bitrate);
	}

	public final StringProperty lyricProperty() {
		return this.lyric;
	}

	public final String getLyric() {
		return this.lyricProperty().get();
	}

	public final void setLyric(final String lyric) {
		this.lyricProperty().set(lyric);
	}

	public final LongProperty timeProperty() {
		return this.time;
	}

	public final long getTime() {
		return this.timeProperty().get();
	}

	public final void setTime(final long time) {
		this.timeProperty().set(time);
	}

	public String getBitrateString() {
		return this.bitrate + "kb/s";
	}
	public String getSizeString(){
		return DaoUtils.byte2FitMemorySize((this.getSize()));
	}
	public String getTimeString(){
		return DaoUtils.millis2TimeOnSecond(getTime(), 2)+"";
	}

}
