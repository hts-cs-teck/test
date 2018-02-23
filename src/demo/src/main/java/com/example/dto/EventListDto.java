package com.example.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class EventListDto implements Serializable {

	/** イベントID */
	private Long eventid;
	/** 日付 */
	private List<Date> eventDate;
	/** イベント名 */
	private String eventName;


	public List<Date> getEventDate() {
		return eventDate;
	}
	public void setEventDate(List<Date> eventDate) {
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
