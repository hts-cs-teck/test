package com.example.model;

/**
 * イベント画面のFormModel
 */
public class EventModel {

	/** イベント名 */
	private String name;
	/** コメント */
	private String comment;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}
}
