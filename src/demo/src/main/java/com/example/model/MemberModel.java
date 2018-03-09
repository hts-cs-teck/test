package com.example.model;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

/**
 * メンバー編集画面のFormModel
 */
public class MemberModel {

	/** id */
	private String id;

    /** 氏名 */
	@NotBlank(message="氏名を入力してください")
	private String name;

	/** 社員番号 */
	@NotBlank(message="社員番号を入力してください")
	@Pattern(regexp="[0-9]*",message="社員番号は半角数字である必要があります")
	private String employeeid;

	/** パスワード */
    @Size(min=4,max=16,message="パスワードは{min}文字以上{max}文字以下を指定してください")
    @Pattern(regexp="[a-zA-Z0-9]*",message="パスワードは半角英数である必要があります")
	private String passwd;

	/** 所属 */
	@NotBlank(message="所属を選択してください")
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
