package com.soundconnect.Beans;

import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class Conference {

	private long id;
	private String name;
	private Set<User> users;
	private List<Audio> tracks;
	private Timestamp songStarted;
	private String password;

	public Conference(long id, String name, String password, Set<User> users, List<Audio> audios,
			Timestamp songStarted) {
		this.name = name;
		this.id = id;
		this.setPassword(password);
		this.users = users;
		if (audios != null)
			tracks = audios;
		else
			tracks = new LinkedList<Audio>();
		this.songStarted = songStarted;
	}

	public void addAudioToConference(Audio audio) {
		tracks.add(audio);
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}

	public List<Audio> getTracks() {
		return tracks;
	}

	public void setTracks(List<Audio> tracks) {
		this.tracks = tracks;
	}

	public Timestamp getSongStarted() {
		return songStarted;
	}

	public void setSongStarted(Timestamp songStarted) {
		this.songStarted = songStarted;
	}

	public long getId() {
		return id;
	}

	public String getPassword() {
		return password;
	}

	public Object[] getAudioIds() {
		Object[] ids = new Long[tracks.size()];
		for (int i = 0; i < ids.length; i++)
			ids[i] = tracks.get(i).getId();
		return ids;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
