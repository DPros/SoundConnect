package com.soundconnect.Beans;


public class Audio {

	/**
	 * @param id
	 * @param source
	 * @param length
	 * @param title
	 * @param artist
	 * @param genre
	 */
	public Audio(long id, long owner, Long length, String title, String artist, long genre) {
		this.id = id;
		this.ownerId = owner;
		this.length = length;
		this.title = title;
		this.artist = artist;
		this.genre = genre;
	}
	public Audio(){}
	private long id;
	private long ownerId;
	private long length;
	private String title;
	private String artist;
	private long genre;

	public void setOwnerId(long source) {
		this.ownerId = source;
	}
	public void setLength(int length) {
		this.length = length;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public void setArtist(String artist) {
		this.artist = artist;
	}
	public void setGenre(long genre) {
		this.genre = genre;
	}
	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}
	/**
	 * @return the source
	 */
	public long getOwnerId() {
		return ownerId;
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
