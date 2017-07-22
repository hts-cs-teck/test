package com.example.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="eventdate")
public class EventDate {

    @Id
    @GeneratedValue
    private Long id;

	@Column(name="eventid")
	private Long eventid;

	@Column(name="date")
	private Date date;

	// getter, setter
	public Long getId() {return id;}
	public void setId(Long id) {this.id = id;}
	public Long getEventId() {return eventid;}
	public void setEventId(Long eventid) {this.eventid = eventid;}
	public Date getDate() {return date;}
	public void setDate(Date date) {this.date = date;}
}