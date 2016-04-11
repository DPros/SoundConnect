package com.soundconnect.Beans;

import java.net.URL;

public class Audio {

	/**
	 * @param id
	 * @param source
	 * @param length
	 * @param title
	 * @param artist
	 * @param genre
	 */
	public Audio(long id, URL source, long length, String title, String artist, long genre) {
		super();
		this.id = id;
		this.source = source;
		this.length = length;
		this.title = title;
		this.artist = artist;
		this.genre = genre;
	}
	private long id;
	private URL source;
	private long length;
	private String title;
	private String artist;
	private long genre;
	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}
	/**
	 * @return the source
	 */
	public URL getSource() {
		return source;
	}
	/**
	 * @return the length
	 */
	public long getLength() {
		return length;
	}
	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * @return the artist
	 */
	public String getArtist() {
		return artist;
	}
	/**
	 * @return the genre
	 */
	public long getGenre() {
		return genre;
	}
	
}
