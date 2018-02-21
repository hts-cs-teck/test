package com.example.dto;

import java.io.Serializable;

public class MemberDto implements Serializable {

	/** 社員番号 */
	private Long id;
	/** 名前 */
	private String name;
	/** 所属 */
	private Long team;
	/** 権限 */
	private String authority;

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Long getTeam() {
		return team;
	}
	public void setTeam(Long team) {
		this.team = team;
	}
	public String getAuthority() {
		return authority;
	}
	public void setAuthority(String authority) {
		this.authority = authority;
	}


}
