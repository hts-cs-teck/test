package com.example.dto;

import java.io.Serializable;

public class MemberListDto implements Serializable {

	/** メンバーID */
	private Long memberId;
	/** 名前 */
	private String name;
	/** チーム名 */
	private String teamName;


	public Long getMemberId() {
		return memberId;
	}
	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}
	public String getName() {
		return name;
	}
	public void setName(String Name) {
		this.name = Name;
	}
	public String getTeamName() {
		return teamName;
	}
	public void setTeamName(String TeamName) {
		this.teamName = TeamName;
	}


}
