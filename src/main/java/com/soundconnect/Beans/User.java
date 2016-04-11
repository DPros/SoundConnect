package com.soundconnect.Beans;

public class User {

	private long id;
	private String name;
	private Conference conference;

	public User(long id, String name, Conference conference){
		this.id = id;
		this.name = name;
		this.conference = conference;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Conference getConference() {
		return conference;
	}
	public void setConference(Conference conference) {
		this.conference = conference;
	}
	public long getId() {
		return id;
	}
}
