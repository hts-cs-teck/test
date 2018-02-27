package com.example.model;

/**
 * イベント詳細画面のFormModel
 */
public class EventDetailModel {

	/** イベントID */
	private Long eventid;
	/** メンバーID */
	private Long memberid;

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
