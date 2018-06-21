package com.example.entity;

import java.util.Date;

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

	@Column(name="startdate")
	private Date startDate;

	@Column(name="enddate")
	private Date endDate;

	// getter, setter
	public Long getId() {return id;}
	public void setId(Long id) {this.id = id;}
	public Long getEventid() {return eventid;}
	public void setEventid(Long eventid) {this.eventid = eventid;}
	public Date getStartDate() {return startDate;}
	public void setStartDate(Date startDate) {this.startDate = startDate;}
	public Date getEndDate() {return endDate;}
	public void setEndDate(Date endDate) {this.endDate = endDate;}

}