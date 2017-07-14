package com.example.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="simei")
public class Simei {

    @Id
    @GeneratedValue
    private Long id;

	@Column(name="passwd")
	private String passwd;

	@Column(name="simeiname")
	private String simeiname;

	// getter, setter
	public Long getId() {return id;}
	public void setId(Long id) {this.id = id;}
	public String getPasswd() {return passwd;}
	public void setPasswd(String passwd) {this.passwd = passwd;}
	public String getSimeiname() {return simeiname;}
	public void setSimeiname(String simeiname) {this.simeiname = simeiname;}
}