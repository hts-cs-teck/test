package com.example.model;

import java.io.Serializable;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

@Component
@Scope(value= "session", proxyMode = ScopedProxyMode.TARGET_CLASS )
public class SessionModel implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	private String simei;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSimei() {
		return simei;
	}

	public void setSimei(String simei) {
		this.simei = simei;
	}

}