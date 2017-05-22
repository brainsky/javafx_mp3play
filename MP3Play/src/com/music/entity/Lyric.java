package com.music.entity;

import java.io.Serializable;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.StringProperty;

public class Lyric implements Serializable {
	private final StringProperty title;
	private final StringProperty author;
	private final IntegerProperty size;
	
	public Lyric(StringProperty title, StringProperty author, IntegerProperty size) {
		super();
		this.title = title;
		this.author = author;
		this.size = size;
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
	
	public final StringProperty authorProperty() {
		return this.author;
	}
	
	public final String getAuthor() {
		return this.authorProperty().get();
	}
	
	public final void setAuthor(final String author) {
		this.authorProperty().set(author);
	}
	
	public final IntegerProperty sizeProperty() {
		return this.size;
	}
	
	public final int getSize() {
		return this.sizeProperty().get();
	}
	
	public final void setSize(final int size) {
		this.sizeProperty().set(size);
	}
	
}
