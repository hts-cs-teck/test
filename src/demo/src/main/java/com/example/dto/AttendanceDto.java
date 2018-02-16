package com.example.dto;

import java.io.Serializable;
import java.util.Date;

public class AttendanceDto implements Serializable {

	/** イベント名 */
	private String eventName;
	/** 日時 */
	private Date eventDate;
	/** 開催場所 */
	private String eventPlace;
	/** コメント */
	private String eventComment;
	/** メンバー名 */
	private String memberName;
	/** イベントID */
	private Long eventid;
	/** メンバーID */
	private Long memberid;


	public String getEventName() {
		return eventName;
	}
	public void setEventName(String eventName) {
		this.eventName = eventName;
	}
	public Date getEventDate() {
		return eventDate;
	}
	public void setEventDate(Date eventDate) {
		this.eventDate = eventDate;
	}
	public String getEventPlace() {
		return eventPlace;
	}
	public void setEventPlace(String eventPlace) {
		this.eventPlace = eventPlace;
	}
	public String getEventComment() {
		return eventComment;
	}
	public void setEventComment(String eventComment) {
		this.eventComment = eventComment;
	}
	public String getMemberName() {
		return memberName;
	}
	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}
	public Long getEventid() {
		return eventid;
	}
	public void setEventid(Long eventid) {
		this.eventid = eventid;
	}
	public Long getMemberid() {
		return memberid;
	}
	public void setMemberid(Long memberid) {
		this.memberid = memberid;
	}


}
