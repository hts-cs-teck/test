package com.example.dto;

import java.io.Serializable;
import java.util.List;

public class EventDetailDto implements Serializable {

	/** メンバー名 */
	private String name;
	/** 出欠 */
	private List<String> attendanceList;
	/** コメント */
	private String comment;


	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<String> getAttendanceList() {
		return attendanceList;
	}
	public void setAttendanceList(List<String> attendanceList) {
		this.attendanceList = attendanceList;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}


}
