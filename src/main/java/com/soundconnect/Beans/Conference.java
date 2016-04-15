package com.soundconnect.Beans;

import java.util.Date;
import java.util.List;
import java.util.Queue;

public class Conference {
	
	private long id;
	private String name;
	private List<User>users;
	private Queue<Audio> tracks;
	private Date songStarted;
	private String password;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<User> getUsers() {
		return users;
	}
	public void setUsers(List<User> users) {
		this.users = users;
	}
	public Queue<Audio> getTracks() {
		return tracks;
	}
	public void setTracks(Queue<Audio> tracks) {
		this.tracks = tracks;
	}
	public Date getSongStarted() {
		return songStarted;
	}
	public void setSongStarted(Date songStarted) {
		this.songStarted = songStarted;
	}
	public long getId() {
		return id;
	}
	
}
