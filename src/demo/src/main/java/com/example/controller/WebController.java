package com.example.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.dto.EventDto;
import com.example.dto.TeamDto;
import com.example.entity.Event;
import com.example.entity.EventAttendance;
import com.example.entity.EventDate;
import com.example.entity.Member;
import com.example.entity.Team;
import com.example.entity.pk.EventAttendancePK;
import com.example.model.EventModel;
import com.example.model.LoginChkModel;
import com.example.model.SessionModel;
import com.example.services.EventAttendanceService;
import com.example.services.EventDateService;
import com.example.services.EventService;
import com.example.services.MemberService;
import com.example.services.TeamService;
import com.example.utils.StringUtil;

@Controller
public class WebController {

	@Autowired
	protected SessionModel sessionModel;

	@Autowired
	TeamService teamService;

	@Autowired
	EventService eventService;

	@Autowired
	EventDateService eventDateService;

	@Autowired
	MemberService memberService;

	@Autowired
	EventAttendanceService eventAttendanceService;

	@RequestMapping(value = "/login")
	public String LoginController()  {
		return "login";
	}

	@RequestMapping(value = "/loginchk")
	public String LoginchkController(Model model, LoginChkModel loginChkModel)  {
		try {

			List<Member> members = memberService.findAnyCondByEmployeeid(Long.parseLong(loginChkModel.getEmployeeid()));
		
			if (members == null)
			{
				model.addAttribute("Message","ユーザIDもしくはパスワードが不正です");
				return "login";
			}
			Member member = members.get(0);
			if (!member.getPasswd().equals(loginChkModel.getPasswd()))
			{
				model.addAttribute("Message","ユーザIDもしくはパスワードが不正です");
				return "login";
			}
			model.addAttribute("name", member.getName());

			sessionModel.setId(member.getId());
			sessionModel.setSimei(member.getName());

			model.addAttribute("sessionModel", sessionModel);

			return "forward:eventList";
		} catch (Exception e) {
			model.addAttribute("Message","ユーザIDもしくはパスワードが不正です");
			return "login";
		}
	}

	@RequestMapping(value = "/sample")
	public String SampleController()  {
		return "sample";
	}

	@RequestMapping(value = "/logout")
	public String LogoutController(HttpSession session) {
		// dummy spring security を使うべき ...
		session.invalidate();
		return "login";
	}

	@RequestMapping(value = "/event")
	public String EventController(Model model, EventModel eventModel)  {
		try {
			model.addAttribute("Message","");

			// メンバ 登録結果の参照
			List<EventDto> eventDtoList = new ArrayList<>();
			List<Member> memberListDB = memberService.findAll();
			for (Member memberDB : memberListDB){

				Team team = teamService.find(memberDB.getTeamid());
				if(team == null)
				{
					model.addAttribute("Message","登録画面表示失敗");
					return "ngEvent";
				}

				EventDto eventDto = new EventDto();
				eventDto.setTeam(team.getName());
				eventDto.setName(memberDB.getName());
//				eventDto.setSelected(hit);
				eventDtoList.add(eventDto);
			}
			model.addAttribute("memberList", eventDtoList);

			// 所属一覧
			List<Team> teamList = teamService.findAll();
			List<TeamDto> teamDtoList = new ArrayList<>();
			if (!teamList.isEmpty())
			{
				for (Team team : teamList) {

					TeamDto teamDto = new TeamDto();
					teamDto.setName(team.getName());
					teamDtoList.add(teamDto);
				}
			}
			model.addAttribute("teamList", teamDtoList);

			String id = eventModel.getId();
			if(!StringUtil.isNullOrEmpty(id))
			{
				Event eventResult = eventService.find(Long.parseLong(id));

				// イベント日付 登録結果の参照
				List<EventDate> eventDateList = eventDateService.findAnyCondByEventid(Long.parseLong(id));
				List<String> strDateListResult = new ArrayList<>();
				String strDateResult = new String();
				List<Long> memberList = new ArrayList<>();
				if (!eventDateList.isEmpty())
				{
					for (EventDate eventDate : eventDateList) {
						SimpleDateFormat formatA =
				            new SimpleDateFormat("yyyy/MM/dd");
						String A = formatA.format(eventDate.getDate());
						strDateListResult.add(A);

						// 連結した文字列を保持
						if(strDateResult.length() == 0)
						{
							strDateResult = A;
						}
						else
						{
							strDateResult = strDateResult + "," + A;
						}

						// メンバ 登録結果の参照
						List<EventAttendance> eventAttendanceList = eventAttendanceService.findAll();
						for (EventAttendance eventAttendance : eventAttendanceList) {
							Long eventdateid = eventAttendance.getEventAttendancePK().getEventdateid();
							Long memberid = eventAttendance.getEventAttendancePK().getMemberid();
							if(eventdateid.equals(eventDate.getId()))
							{
								memberList.add(memberid);
							}
						}
					}
				}

				List<EventDto> eventDtoListSelect = new ArrayList<>();
				for (Member memberDB : memberListDB){
					boolean hit = false;
					for (Long member : memberList){
						if(memberDB.getId().equals(member)){
							hit = true;
							break;
						}
					}

					Team team = teamService.find(memberDB.getTeamid());
					if(team == null)
					{
						model.addAttribute("Message","登録画面表示失敗");
						return "ngEvent";
					}

					if(hit)
					{
						EventDto eventDto = new EventDto();
						eventDto.setTeam(team.getName());
						eventDto.setName(memberDB.getName());
	//					eventDto.setSelected(hit);
						eventDtoListSelect.add(eventDto);
					}

				}

				model.addAttribute("event", eventResult);
				model.addAttribute("eventDateList", strDateListResult);
				model.addAttribute("datelisttext", strDateResult);
				model.addAttribute("memberListselect", eventDtoListSelect);
			}
			else
			{
				Event event = new Event();
				model.addAttribute("event", event);
			}
			model.addAttribute("sessionModel", sessionModel);
		} catch (Exception e) {
			model.addAttribute("Message","例外発生");
		}
		return "event";
	}

	@RequestMapping(value = {"/registEvent"})
	public String RegistEventController(Model model, EventModel eventModel)  {
		try {
			// イベントの登録or更新
			Event event = new Event();
			String id = eventModel.getId();
			if(id != "")
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
					return "ngEvent";
				}
				else
				{
					model.addAttribute("Message","イベント更新に失敗しました");
					return "ngEvent";
				}
			}

			// 候補日の登録&削除
			{
				String strEventDateList = eventModel.getDatelisttext();
				String[] strEventDate = strEventDateList.split(",");

				// 現在の候補日取得
				List<EventDate> eventDateList = eventDateService.findAnyCondByEventid(eventResult.getId());

				// 新規候補日の登録
				for(int i=0;i<strEventDate.length;i++)
				{
					boolean hit = false;
					for (EventDate eventDate : eventDateList) {
						SimpleDateFormat formatA =
				            new SimpleDateFormat("yyyy/MM/dd");
						String A = formatA.format(eventDate.getDate());

						// 登録済み
						if(strEventDate[i].equals(A))
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
							return "ngEvent";
						}
						else
						{
							model.addAttribute("Message","イベント更新に失敗しました");
							return "ngEvent";
						}
					}

					// 出席情報登録
					String[] memberList = eventModel.getMemberlist();
					for (String member : memberList) {
						List<Member> memberListDB = memberService.findByName(member);
						if (memberListDB.size()!=1)
						{
							if(id == null)
							{
								model.addAttribute("Message","イベント登録に失敗しました");
								return "ngEvent";
							}
							else
							{
								model.addAttribute("Message","イベント更新に失敗しました");
								return "ngEvent";
							}
						}
						Long memberId = memberListDB.get(0).getId();

						EventAttendance eventAttendance = new EventAttendance();
						EventAttendancePK eventAttendancePK = new EventAttendancePK();
						eventAttendancePK.setMemberid(memberId);
						eventAttendancePK.setEventdateid(eventDateResult.getId());
						eventAttendance.setEventAttendancePK(eventAttendancePK);
						EventAttendance eventAttendanceResult = eventAttendanceService.save(eventAttendance);
						if (eventAttendanceResult == null)
						{
							if(id == null)
							{
								model.addAttribute("Message","イベント登録に失敗しました");
								return "ngEvent";
							}
							else
							{
								model.addAttribute("Message","イベント更新に失敗しました");
								return "ngEvent";
							}
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
						if(strEventDate[i].equals(A))
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

					// 出席情報削除
					List<EventAttendance> eventAttendanceList = eventAttendanceService.findAll();
					for (EventAttendance eventAttendance : eventAttendanceList) {
						Long eventdateid = eventAttendance.getEventAttendancePK().getEventdateid();
						if(eventdateid.equals(eventDate.getId()))
						{
							eventAttendanceService.deleteByPK(eventAttendance.getEventAttendancePK());
						}
					}

					// イベント日付削除
					eventDateService.delete(eventDate.getId());
				}
			}

			// メンバの登録&削除
			String[] memberList = eventModel.getMemberlist();
			{
				// 新規追加
				List<EventDate> eventDateList = eventDateService.findAnyCondByEventid(eventResult.getId());

				for (String member : memberList) {
					List<Member> memberListDB = memberService.findByName(member);
					if (memberListDB.size()!=1)
					{
						if(id == null)
						{
							model.addAttribute("Message","イベント登録に失敗しました");
							return "ngEvent";
						}
						else
						{
							model.addAttribute("Message","イベント更新に失敗しました");
							return "ngEvent";
						}
					}
					Long memberId = memberListDB.get(0).getId();

					// 新規出席情報の追加
					for (EventDate eventDate : eventDateList) {

						EventAttendance eventAttendanceResult = eventAttendanceService.findByPK(memberId, eventDate.getId());
						if (eventAttendanceResult == null)
						{
							EventAttendance eventAttendance = new EventAttendance();
							EventAttendancePK eventAttendancePK = new EventAttendancePK();
							eventAttendancePK.setMemberid(memberId);
							eventAttendancePK.setEventdateid(eventDate.getId());
							eventAttendance.setEventAttendancePK(eventAttendancePK);
							eventAttendanceResult = eventAttendanceService.save(eventAttendance);
							if (eventAttendanceResult == null)
							{
								if(id == null)
								{
									model.addAttribute("Message","イベント登録に失敗しました");
									return "ngEvent";
								}
								else
								{
									model.addAttribute("Message","イベント更新に失敗しました");
									return "ngEvent";
								}
							}
						}
					}
				}

				// 削除
				List<EventAttendance> eventAttendanceList = eventAttendanceService.findAll();
				for (EventAttendance eventAttendance : eventAttendanceList) {
					Long eventdateid = eventAttendance.getEventAttendancePK().getEventdateid();
					Long memberid = eventAttendance.getEventAttendancePK().getMemberid();

					// 処理対象イベントの情報かチェック
					boolean hit = false;
					for (EventDate eventDate : eventDateList) {
						if(eventdateid.equals(eventDate.getId()))
						{
							hit = true;
							break;
						}
					}
					if(!hit)
					{
						continue;
					}

					hit = false;
					for (String member : memberList) {
						List<Member> memberListDB = memberService.findByName(member);
						if (memberListDB.size()!=1)
						{
							if(id == null)
							{
								model.addAttribute("Message","イベント登録に失敗しました");
								return "ngEvent";
							}
							else
							{
								model.addAttribute("Message","イベント更新に失敗しました");
								return "ngEvent";
							}
						}
						if(memberid.equals(memberListDB.get(0).getId()))
						{
							hit = true;
							break;
						}
					}
					if(!hit)
					{
						eventAttendanceService.delete(memberid, eventdateid);
						continue;
					}
				}
			}

			// イベント日付 登録結果の参照
			List<EventDate> eventDateList = eventDateService.findAnyCondByEventid(eventResult.getId());
			List<String> strDateListResult = new ArrayList<>();
			String strDateResult = new String();
			if (!eventDateList.isEmpty())
			{
				for (EventDate eventDate : eventDateList) {
					SimpleDateFormat formatA =
			            new SimpleDateFormat("yyyy/MM/dd");
					String A = formatA.format(eventDate.getDate());
					strDateListResult.add(A);

					// 連結した文字列を保持
					if(strDateResult.length() == 0)
					{
						strDateResult = A;
					}
					else
					{
						strDateResult = strDateResult + "," + A;
					}
				}
			}

			// メンバ 登録結果の参照
			List<EventDto> eventDtoListSelect = new ArrayList<>();
			List<EventDto> eventDtoList = new ArrayList<>();
			List<Member> memberListDB = memberService.findAll();
			for (Member memberDB : memberListDB){
				boolean hit = false;
				for (String member : memberList){
					if(memberDB.getName().equals(member)){
						hit = true;
						break;
					}
				}

				Team team = teamService.find(memberDB.getTeamid());
				if(team == null)
				{
					model.addAttribute("Message","登録画面表示失敗");
					return "ngEvent";
				}

				if(hit)
				{
					EventDto eventDto = new EventDto();
					eventDto.setTeam(team.getName());
					eventDto.setName(memberDB.getName());
//					eventDto.setSelected(hit);
					eventDtoListSelect.add(eventDto);
				}

				EventDto eventDto = new EventDto();
				eventDto.setTeam(team.getName());
				eventDto.setName(memberDB.getName());
//				eventDto.setSelected(hit);
				eventDtoList.add(eventDto);
			}

			model.addAttribute("event", eventResult);
			model.addAttribute("eventDateList", strDateListResult);
			model.addAttribute("datelisttext", strDateResult);
			model.addAttribute("memberListselect", eventDtoListSelect);
			model.addAttribute("memberList", eventDtoList);

			// 所属一覧
			List<Team> teamList = teamService.findAll();
			List<TeamDto> teamDtoList = new ArrayList<>();
			if (!teamList.isEmpty())
			{
				for (Team team : teamList) {

					TeamDto teamDto = new TeamDto();
					teamDto.setName(team.getName());
					teamDtoList.add(teamDto);
				}
			}
			model.addAttribute("teamList", teamDtoList);

			model.addAttribute("sessionModel", sessionModel);

			return "event";
		} catch (Exception e) {
			model.addAttribute("Message","例外発生");
			return "ngEvent";
		}
	}

}
