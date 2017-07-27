package com.example.model;

/**
 * イベント画面のFormModel
 */
public class EventModel {

	/** イベント名 */
	private String name;
	/** 日付 */
	private String datelisttext;

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
