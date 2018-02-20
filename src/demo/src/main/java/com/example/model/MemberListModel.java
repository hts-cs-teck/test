package com.example.model;

/**
 * メンバ一覧画面のFormModel
 */
public class MemberListModel {

	/** チームID */
	private String steamid;
	/** メンバ名 */
	private String sname;
	/** 子階層 */
	private String schild;

	public String getSchild() {
		return schild;
	}

	public void setSchild(String schild) {
		this.schild = schild;
	}

	public String getSteamid() {
		return steamid;
	}

	public void setSteamid(String steamid) {
		this.steamid = steamid;
	}

	public String getSname() {
		return sname;
	}

	public void setSname(String sname) {
		this.sname = sname;
	}
}
