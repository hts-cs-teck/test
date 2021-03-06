package com.example.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="member")
public class Member {

    @Id
    @GeneratedValue
    private Long id;

	@Column(name="employeeid")
    private Long employeeid;

	@Column(name="passwd")
	private String passwd;

	@Column(name="teamid")
    private Long teamid;

	@Column(name="name")
	private String name;

	// getter, setter
	public Long getId() {return id;}
	public void setId(Long id) {this.id = id;}
	public Long getemployeeid() {return employeeid;}
	public void setemployeeid(Long employeeid) {this.employeeid = employeeid;}
	public String getPasswd() {return passwd;}
	public void setPasswd(String passwd) {this.passwd = passwd;}
	public Long getTeamid() {return teamid;}
	public void setTeamid(Long teamid) {this.teamid = teamid;}
	public String getName() {return name;}
	public void setName(String name) {this.name = name;}
}