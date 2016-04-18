package com.soundconnect.Beans;

import java.util.Set;

public class User {

	private long id;
	private String name;
	private long conference;
	private Set<Audio>audio;

	public Set<Audio> getAudio() {
		return audio;
	}
	
	public Object[] getAudioIds(){
		Long[]ids = new Long[audio.size()];
		int i=0;
		for(Audio a:audio)ids[i++]=a.getId();
		return ids;
	}

	public void setAudio(Set<Audio> audio) {
		this.audio = audio;
	}

	public void setId(long id) {
		this.id = id;
	}

	public User(long id, String name, long conference, Set<Audio>audios){
		this.id = id;
		this.name = name;
		this.conference = conference;
		audio = audios;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public long getConference() {
		return conference;
	}
	public void setConference(long conference) {
		this.conference = conference;
	}
	public long getId() {
		return id;
	}
	
	public boolean equals(User user){
		return id==user.id;
	}
	
	@Override
	public int hashCode(){
		return new Long(id).hashCode();
	}
}
