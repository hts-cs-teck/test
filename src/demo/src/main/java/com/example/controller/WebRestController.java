package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.entity.Simei;
import com.example.services.SimeiService;

@RestController
public class WebRestController {

	@Autowired
	SimeiService simeiService;

	@RequestMapping(value = "/webrest1")
	public String WebRest1Controller()  {
		return "hello";
	}

	// 一覧表示
	@RequestMapping(value="/simeiFindAll", method=RequestMethod.GET)
	public List<Simei> simeiFindAll() {
		return simeiService.findAll();
	}

}
