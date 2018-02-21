package com.example.model;

/**
 * メンバー編集画面のFormModel
 */
public class MemberModel {

	/** 社員番号 */
	private String id;
	/** 氏名 */
	private String name;
	/** 所属 */
	private String steamid;
	/** 権限 */
	private String authoritytext;

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

	public String getSteamid() {
		return steamid;
	}

	public void setSteamid(String steamid) {
		this.steamid = steamid;
	}

	public String getAuthoritytext() {
		return authoritytext;
	}

	public void setAuthoritytext(String authoritytext) {
		this.authoritytext = authoritytext;
	}

}
