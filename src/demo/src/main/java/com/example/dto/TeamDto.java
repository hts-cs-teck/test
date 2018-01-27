package com.example.dto;

import java.io.Serializable;

public class TeamDto implements Serializable {

	/** 名前 */
	private String name;
	/** 選択 */
	private boolean selected;

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
