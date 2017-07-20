package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.entity.Event;
import com.example.entity.Simei;
import com.example.model.EventModel;
import com.example.model.LoginChkModel;
import com.example.services.EventDateService;
import com.example.services.EventService;
import com.example.services.SimeiService;

@Controller
public class WebController {

	@Autowired
	SimeiService simeiService;

	@Autowired
	EventService eventService;

	@Autowired
	EventDateService eventDateService;

	@RequestMapping(value = "/login")
	public String LoginController()  {
		return "login";
	}

	@RequestMapping(value = "/loginchk")
	public String LoginchkController(Model model, LoginChkModel loginChkModel)  {
		try {
			Simei simei = simeiService.find(Long.parseLong(loginChkModel.getId()));
			if (simei == null)
			{
				model.addAttribute("id", loginChkModel.getId());
				return "ng";
			}
			if (!simei.getPasswd().equals(loginChkModel.getPasswd()))
			{
				model.addAttribute("id", loginChkModel.getId());
				return "ng";
			}
			model.addAttribute("name", simei.getSimeiname());
			return "ok";
		} catch (Exception e) {
			model.addAttribute("id", loginChkModel.getId());
			return "ng";
		}
	}

	@RequestMapping(value = "/sample")
	public String SampleController()  {
		return "sample";
	}

	@RequestMapping(value = "/event")
	public String EventController(Model model, EventModel eventModel)  {
		try {
			Event event = eventService.find(Long.parseLong(eventModel.getId()));
			if (event == null)
			{
				model.addAttribute("id", eventModel.getId());
				return "ng";
			}
			model.addAttribute("event", event);

//			List<EventDate> eventDateList = eventDateService.findByEventId(Long.parseLong(eventModel.getId()));
//			if (eventDateList.isEmpty())
//			{
//				model.addAttribute("id", eventModel.getId());
//				return "ng";
//			}
//			model.addAttribute("eventDataList", eventDateList);
			
			return "event";
		} catch (Exception e) {
			model.addAttribute("id", eventModel.getId());
			return "ng";
		}
	}


}
