package com.soundconnect.Beans;

import org.springframework.beans.factory.annotation.Autowired;

import com.soundconnect.Services.ConferenceService;

public class User {

	@Autowired
	ConferenceService conferenceService;
	
	private long id;
	private String name;
	private long conference;

	public User(long id){
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Conference getConference() {
		return conferenceService.getConferenceById(conference);
	}
	public void setConference(Conference conference) {
		this.conference = conference.getId();
	}
	public long getId() {
		return id;
	}
}
