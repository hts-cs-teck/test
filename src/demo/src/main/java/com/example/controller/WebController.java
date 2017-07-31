package com.example.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.entity.Event;
import com.example.entity.EventDate;
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
			return "forward:eventList";
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
		model.addAttribute("Message","");
		return "event";
	}

	@RequestMapping(value = {"/registEvent", "/updateEvent"})
	public String RegistEventController(Model model, EventModel eventModel)  {
		try {
			// イベントの登録or更新
			Event event = new Event();
			String id = eventModel.getId();
			if(id != null)
			{
				event.setId(Long.parseLong(id));
			}
			event.setName(eventModel.getName());
			Event eventResult = eventService.save(event);
			if (eventResult == null)
			{
				if(id == null)
				{
					model.addAttribute("Message","イベント登録に失敗しました");
					return "event";
				}
				else
				{
					model.addAttribute("Message","イベント更新に失敗しました");
					return "eventUpdate";
				}
			}

			// 候補日の登録or更新
			{
				String strEventDateList = eventModel.getDatelisttext();
				String[] strEventDate = strEventDateList.split(",");
	
				// 現在の候補日取得
				List<EventDate> eventDateList = eventDateService.findByEventid(eventResult.getId());
	
				// 新規候補日の登録
				for(int i=0;i<strEventDate.length;i++)
				{
					boolean hit = false;
					for (EventDate eventDate : eventDateList) {
						SimpleDateFormat formatA =
				            new SimpleDateFormat("yyyy/MM/dd");
						String A = formatA.format(eventDate.getDate());
						
						// 登録済み
						if(strEventDate[i] == A)
						{
							hit = true;
							continue;
						}
					}
					// 登録済み
					if(hit == true)
					{
						continue;
					}
					
					// 未登録のため登録
					EventDate newEventDate = new EventDate();
					newEventDate.setEventId(eventResult.getId());
	
					SimpleDateFormat formatA =
				            new SimpleDateFormat("yyyy/MM/dd");
					Date date = formatA.parse(strEventDate[i]);
					newEventDate.setDate(date);
					
					EventDate eventDateResult = eventDateService.save(newEventDate);
					if (eventDateResult == null)
					{
						if(id == null)
						{
							model.addAttribute("Message","イベント登録に失敗しました");
							return "event";
						}
						else
						{
							model.addAttribute("Message","イベント更新に失敗しました");
							return "eventUpdate";
						}
					}
				}
	
				// 既存候補日の削除
				for (EventDate eventDate : eventDateList) {
					SimpleDateFormat formatA =
			            new SimpleDateFormat("yyyy/MM/dd");
					String A = formatA.format(eventDate.getDate());
	
					boolean hit = false;
					for(int i=0;i<strEventDate.length;i++)
					{
						// 登録済み
						if(strEventDate[i] == A)
						{
							hit = true;
							continue;
						}
					}
					// 登録済み
					if(hit == true)
					{
						continue;
					}
				
					eventDateService.delete(eventDate.getId());
				}
			}
			
			List<EventDate> eventDateList = eventDateService.findByEventid(eventResult.getId());
			List<String> strDateListResult = new ArrayList<>();
			String dateListText = new String();
			if (!eventDateList.isEmpty())
			{
				for (EventDate eventDate : eventDateList) {
					SimpleDateFormat formatA =
			            new SimpleDateFormat("yyyy/MM/dd");
					String A = formatA.format(eventDate.getDate());
					strDateListResult.add(A);
	
					// 連結した文字列を保持
					if(dateListText.length() == 0)
					{
						dateListText = A;
					}
					else
					{
						dateListText = dateListText + "," + A;
					}
				}
			}
		
			model.addAttribute("event", eventResult);
			model.addAttribute("eventDateList", strDateListResult);
			model.addAttribute("datelisttext", dateListText);

			return "eventUpdate";
		} catch (Exception e) {
			model.addAttribute("Message","イベント登録に失敗しました");
			return "event";
		}
	}

}
