package com.example.model;

/**
 * ログインチェック画面のFormModel
 */
public class LoginChkModel {

	/** ログインID */
	private String id;

	/** 社員番号 */
	private String employeeid;
	/** パスワード */
	private String passwd;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	public String getEmployeeid() {
		return employeeid;
	}

	public void setEmployeeid(String employeeid) {
		this.employeeid = employeeid;
	}

}
