package com.example.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="team")
public class Team {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name="parentid")
    private Long parentid;

	@Column(name="name")
	private String name;

	// getter, setter
	public Long getId() {return id;}
	public void setId(Long id) {this.id = id;}
	public Long getParentid() {return parentid;}
	public void setParentid(Long parentid) {this.parentid = parentid;}
	public String getName() {return name;}
	public void setName(String name) {this.name = name;}
}