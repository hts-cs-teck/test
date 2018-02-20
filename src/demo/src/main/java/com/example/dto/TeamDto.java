package com.example.dto;

import java.io.Serializable;

public class TeamDto implements Serializable {

	/** id */
	private Long id;
	/** parent id */
    private Long parentid;
	/** name */
	private String name;
	/** 選択 */
	private boolean selected;

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getParentid() {
		return parentid;
	}
	public void setParentid(Long parentid) {
		this.parentid = parentid;
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
