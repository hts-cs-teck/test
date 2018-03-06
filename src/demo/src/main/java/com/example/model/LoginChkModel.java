package com.example.model;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

/**
 * ログインチェック画面のFormModel
 */
public class LoginChkModel {

	/** ユーザID */
	@NotBlank(message="ユーザIDを入力してください")
	private String employeeid;

	/** パスワード */
    @Size(min=4,max=16,message="パスワードは{min}文字以上{max}文字以下を指定してください")
    @Pattern(regexp="[a-zA-Z0-9]*",message="パスワードは半角英数である必要があります")
	private String passwd;

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
