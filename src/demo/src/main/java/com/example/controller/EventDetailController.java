package com.example.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.dto.EventDetailDto;
import com.example.entity.EventAttendance;
import com.example.entity.EventComment;
import com.example.entity.EventDate;
import com.example.entity.Member;
import com.example.model.EventDetailModel;
import com.example.model.SessionModel;
import com.example.services.EventAttendanceService;
import com.example.services.EventCommentService;
import com.example.services.EventDateService;
import com.example.services.MemberService;

@Controller
public class EventDetailController {

	@Autowired
	protected SessionModel sessionModel;

	@Autowired
	private EventDateService eventDateService;

	@Autowired
	private MemberService memberService;

	@Autowired
	private EventAttendanceService eventAttendanceService;

	@Autowired
	private EventCommentService eventCommentService;

	@RequestMapping(value = "/eventDetail")
	public String index(Model model, EventDetailModel eventDetailModel)  {
		// メンバーをすべて取得
		List<Member> memberList = memberService.findAll();

		// イベントに対する日付を取得
		List<EventDate> eventDateList = eventDateService.findAnyCondByEventid(eventDetailModel.getEventid());

		// 各メンバーの出欠、コメントを取得
		List<EventDetailDto> eventDetailList = new ArrayList<>();
		for (Member member : memberList) {
			EventDetailDto dto = new EventDetailDto();

			// メンバー名
			dto.setName(member.getName());

			// 出欠
			List<String> attendanceList = new ArrayList<>();
			for (EventDate eventDate : eventDateList) {
				EventAttendance eventAttendance = eventAttendanceService.findByPK(member.getId(), eventDate.getId());
				if (eventAttendance != null) {
					attendanceList.add(eventAttendance.getAttendance());
				} else {
					attendanceList.add("");
				}
			}
			dto.setAttendanceList(attendanceList);

			// コメント
			EventComment eventComment = eventCommentService.findByPK(member.getId(), eventDetailModel.getEventid());
			if (eventComment != null) {
				dto.setComment(eventComment.getComment());
			}

			eventDetailList.add(dto);
		}

		model.addAttribute("eventDateList", eventDateList);
		model.addAttribute("eventDetailList", eventDetailList);

		model.addAttribute("sessionModel", sessionModel);

		return "eventDetail";
	}
}
