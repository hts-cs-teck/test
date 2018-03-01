package com.example.model;

/**
 * メンバー編集画面のFormModel
 */
public class MemberModel {

	/** id */
	private String id;
	/** 社員番号 */
	private String employeeid;
	/** パスワード */
	private String passwd;
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

	public String getEmployeeid() {
		return employeeid;
	}

	public void setEmployeeid(String employeeid) {
		this.employeeid = employeeid;
	}

	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

}
