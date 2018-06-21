package com.example.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class EventListDto implements Serializable {

	/** イベントID */
	private Long eventid;
	/** 開始日時 */
	private List<Date> eventStartDate;
	/** 終了日時 */
	private List<Date> eventEndDate;
	/** イベント名 */
	private String eventName;


	public List<Date> getEventStartDate() {
		return eventStartDate;
	}
	public void setEventStartDate(List<Date> eventStartDate) {
		this.eventStartDate = eventStartDate;
	}
	public List<Date> getEventEndDate() {
		return eventEndDate;
	}
	public void setEventEndDate(List<Date> eventEndDate) {
		this.eventEndDate = eventEndDate;
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
