package com.example.dto;

import java.io.Serializable;

public class EventDto implements Serializable {

	/** 所属 */
	private String team;
	/** 名前 */
	private String name;
	/** 選択 */
	private boolean selected;

	public String getTeam() {
		return team;
	}
	public void setTeam(String team) {
		this.team = team;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public boolean getSelected() {
		return selected;
	}
	public void setSelected(boolean selected) {
		this.selected = selected;
	}


}
