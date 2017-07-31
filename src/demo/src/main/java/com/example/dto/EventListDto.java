package com.example.dto;

import java.io.Serializable;
import java.util.Date;

public class EventListDto implements Serializable {

	/** イベントID */
	private Long eventid;
	/** 日付 */
	private Date eventDate;
	/** イベント名 */
	private String eventName;


	public Date getEventDate() {
		return eventDate;
	}
	public void setEventDate(Date eventDate) {
		this.eventDate = eventDate;
	}
	public String getEventName() {
		return eventName;
	}
	public void setEventName(String eventName) {
		this.eventName = eventName;
	}
	public Long getEventid() {
		return eventid;
	}
	public void setEventid(Long eventid) {
		this.eventid = eventid;
	}


}
