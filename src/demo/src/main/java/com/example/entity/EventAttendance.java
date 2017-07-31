package com.example.entity;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.example.entity.pk.EventAttendancePK;


@Entity
@Table(name="eventattendance")
public class EventAttendance {

	@EmbeddedId
	private EventAttendancePK eventAttendancePK;

	@Column(name="attendance")
	private String attendance;

	// getter, setter
	public EventAttendancePK getEventAttendancePK() {return eventAttendancePK;}
	public void setEventAttendancePK(EventAttendancePK eventAttendancePK) {this.eventAttendancePK = eventAttendancePK;}
	public String getAttendance() {return attendance;}
	public void setAttendance(String attendance) {this.attendance = attendance;}
}