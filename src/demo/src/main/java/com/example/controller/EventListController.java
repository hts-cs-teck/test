package com.example.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.dto.EventListDto;
import com.example.entity.Event;
import com.example.entity.EventAttendance;
import com.example.entity.EventComment;
import com.example.entity.EventDate;
import com.example.entity.pk.EventAttendancePK;
import com.example.model.EventDetailModel;
import com.example.model.SessionModel;
import com.example.services.EventAttendanceService;
import com.example.services.EventCommentService;
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

	@Autowired
	private EventAttendanceService eventAttendanceService;

	@Autowired
	private EventCommentService eventCommentService;

	@RequestMapping(value = "/eventList")
	public String index(Model model)  {
		List<EventListDto> list = new ArrayList<>();

		// 全てのイベントを取得し、各イベントの日付を取得
		List<Event> events = eventService.findAll();
		for (Event event : events) {
			EventListDto dto = new EventListDto();

			List<EventDate> eventDateList = eventDateService.findAnyCondByEventid(event.getId());
			List<Date> listDate = new ArrayList<>();

			for (EventDate eventDate : eventDateList) {
				listDate.add(eventDate.getDate());
			}
			dto.setEventid(event.getId());
			dto.setEventDate(listDate);
			dto.setEventName(event.getName());

			list.add(dto);
		}

		model.addAttribute("events", list);

		model.addAttribute("sessionModel", sessionModel);

		return "eventList";
	}

	@RequestMapping(value = "/eventDelete")
	public String eventDelete(Model model, EventDetailModel eventDetailModel)  {
		try {
			Long id = eventDetailModel.getEventid();

			// イベント日付を参照
			List<EventDate> eventDateList = eventDateService.findAnyCondByEventid(id);
			if (!eventDateList.isEmpty())
			{
				for (EventDate eventDate : eventDateList)
				{
					// イベントIDが一致する
					if(eventDate.getEventId().equals(id))
					{
						List<Long> memberidList = new ArrayList<>();

						// 出欠登録を参照
						List<EventAttendance> eventAttendanceList = eventAttendanceService.findAll();
						for (EventAttendance eventAttendance : eventAttendanceList)
						{
							// イベント日付IDが一致する
							EventAttendancePK eventAttendancePK = eventAttendance.getEventAttendancePK();
							if(eventAttendancePK.getEventdateid().equals(eventDate.getId()))
							{
								// 出欠登録削除
								eventAttendanceService.deleteByPK(eventAttendancePK);
								// 対象メンバを取得
								Long memberid = eventAttendancePK.getMemberid();
								memberidList.add(memberid);
							}
						}

						// コメント削除
						for (Long memberid : memberidList)
						{
							EventComment eventComment = eventCommentService.findByPK(memberid, id);
							if(eventComment != null)
							{
								eventCommentService.delete(memberid, id);
							}
						}

						// イベント日付の削除
						eventDateService.delete(eventDate.getId());
					}
				}
			}

			// イベント削除
			eventService.delete(id);

			model.addAttribute("sessionModel", sessionModel);

			return "forward:eventList";
		} catch (Exception e) {
			model.addAttribute("Message","イベント削除に失敗しました");
			return "ngEvent";
		}
	}

}
