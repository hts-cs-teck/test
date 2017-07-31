package com.example.model;

/**
 * イベント画面のFormModel
 */
public class EventModel {

	/** ID */
	private String id;
	/** イベント名 */
	private String name;
	/** 日付 */
	private String datelisttext;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDatelisttext() {
		return datelisttext;
	}

	public void setDatelisttext(String datelisttext) {
		this.datelisttext = datelisttext;
	}
}
