package com.example.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.dto.EventListDto;
import com.example.entity.Event;
import com.example.entity.EventDate;
import com.example.model.SessionModel;
import com.example.services.EventDateService;
import com.example.services.EventService;

@Controller
public class EventListController {

	@Autowired
	protected SessionModel sessionModel;

	@Autowired
	private EventService eventService;

	@Autowired
	private EventDateService eventDateService;

	@RequestMapping(value = "/eventList")
	public String index(Model model)  {
		List<EventListDto> list = new ArrayList<>();

		// 全てのイベントを取得し、各イベントの日付を取得
		List<Event> events = eventService.findAll();
		for (Event event : events) {
			List<EventDate> eventDateList = eventDateService.findByEventid(event.getId());

			for (EventDate eventDate : eventDateList) {
				EventListDto dto = new EventListDto();
				dto.setEventid(event.getId());
				dto.setEventDate(eventDate.getDate());
				dto.setEventName(event.getName());

				list.add(dto);
			}
		}

		model.addAttribute("events", list);

		model.addAttribute("sessionModel", sessionModel);

		return "eventList";
	}
}
