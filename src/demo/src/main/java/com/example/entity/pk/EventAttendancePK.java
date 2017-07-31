package com.example.entity.pk;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class EventAttendancePK implements Serializable {

	@Column(name="memberid")
    private Long memberid;

	@Column(name="eventdateid")
	private Long eventdateid;

	public Long getMemberid() {
		return memberid;
	}

	public void setMemberid(Long memberid) {
		this.memberid = memberid;
	}

	public Long getEventdateid() {
		return eventdateid;
	}

	public void setEventdateid(Long eventdateid) {
		this.eventdateid = eventdateid;
	}

}