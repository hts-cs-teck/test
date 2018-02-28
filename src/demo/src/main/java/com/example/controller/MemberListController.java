package com.example.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.dto.MemberListDto;
import com.example.dto.TeamDto;
import com.example.entity.EventAttendance;
import com.example.entity.EventComment;
import com.example.entity.EventDate;
import com.example.entity.Member;
import com.example.entity.Team;
import com.example.entity.pk.EventAttendancePK;
import com.example.model.MemberListModel;
import com.example.model.MemberModel;
import com.example.model.SessionModel;
import com.example.services.EventAttendanceService;
import com.example.services.EventCommentService;
import com.example.services.EventDateService;
import com.example.services.EventService;
import com.example.services.MemberService;
import com.example.services.TeamService;
import com.example.utils.StringUtil;

@Controller
public class MemberListController {

	@Autowired
	protected SessionModel sessionModel;

	@Autowired
	private MemberService memberService;

	@Autowired
	private TeamService teamService;

	@Autowired
	private EventService eventService;

	@Autowired
	private EventDateService eventDateService;

	@Autowired
	private EventAttendanceService eventAttendanceService;

	@Autowired
	private EventCommentService eventCommentService;

	@RequestMapping(value = "/member")
	public String MemberSearch(Model model, MemberListModel memberModel){
		try {
			model.addAttribute("Message","");

			List<Member> memberList = null;
			String steamid = memberModel.getSteamid();
			String sname = memberModel.getSname();
			String schild = memberModel.getSchild();
			if (!StringUtil.isNullOrEmpty(steamid) && !StringUtil.isNullOrEmpty(sname)) {
				if (!StringUtil.isNullOrEmpty(schild) && !StringUtil.isNullOrEmpty(schild)) {
					memberList = memberService.findAnyCondByAlls(Long.parseLong(steamid), sname);
				} else {
					memberList = memberService.findAnyCondByAll(Long.parseLong(steamid), sname);
				}
			} else if (!StringUtil.isNullOrEmpty(steamid)) {
				if (!StringUtil.isNullOrEmpty(schild) && !StringUtil.isNullOrEmpty(schild)) {
					memberList = memberService.findAnyCondByTeamids(Long.parseLong(steamid));
				} else {
					memberList = memberService.findAnyCondByTeamid(Long.parseLong(steamid));
				}
			} else if (!StringUtil.isNullOrEmpty(sname)) {
				memberList = memberService.findAnyCondByName(sname);
			} else {
				memberList = memberService.findAll();
			}

			List<MemberListDto> list = new ArrayList<>();
			if (!memberList.isEmpty())
			{
				for (Member member : memberList) {
					MemberListDto dto = new MemberListDto();
					dto.setMemberId(member.getId());
					dto.setName(member.getName());
					dto.setTeamName(teamService.find(member.getTeamid()).getName());

					list.add(dto);
				}
			}
			model.addAttribute("members", list);

			List<Team> teamList = teamService.findAll();
			List<TeamDto> teamDtoList = new ArrayList<>();
			if (!teamList.isEmpty())
			{
				for (Team team : teamList) {
					TeamDto teamDto = new TeamDto();
					teamDto.setId(team.getId());
					teamDto.setParentid(team.getParentid());
					teamDto.setName(team.getName());
					teamDtoList.add(teamDto);
				}
			}
			model.addAttribute("teamList", teamDtoList);
			model.addAttribute("conditions", memberModel);
			model.addAttribute("sessionModel", sessionModel);
		} catch (Exception e) {
			model.addAttribute("Message","例外発生");
		}
		return "memberList";
	}

	@RequestMapping(value = "/memberDelete")
	public String DeleteMemberController(Model model, MemberModel memberModel)  {
		try {

			Long id = Long.parseLong(memberModel.getId());

			List<Long> eventdateidList = new ArrayList<>();

			// 出欠登録を参照
			List<EventAttendance> eventAttendanceList = eventAttendanceService.findAll();
			for (EventAttendance eventAttendance : eventAttendanceList)
			{
				// メンバIDが一致する
				EventAttendancePK eventAttendancePK = eventAttendance.getEventAttendancePK();
				if(eventAttendancePK.getMemberid().equals(id))
				{
					// 出欠登録削除
					eventAttendanceService.deleteByPK(eventAttendancePK);
					// 対象イベント日付を取得
					Long eventdateid = eventAttendancePK.getEventdateid();
					eventdateidList.add(eventdateid);
				}
			}

			// 削除対象となるイベント日付
			List<Long> eventidList = new ArrayList<>();

			// 出欠情報を削除したイベント日付を持つ出欠情報が他にあるか？
			eventAttendanceList = eventAttendanceService.findAll();
			for (Long eventdateid : eventdateidList)
			{
				boolean hit = false;
				for (EventAttendance eventAttendance : eventAttendanceList)
				{
					EventAttendancePK eventAttendancePK = eventAttendance.getEventAttendancePK();

					// イベント日付IDが一致する
					if(eventAttendancePK.getEventdateid().equals(eventdateid))
					{
						hit = true;
						break;
					}
				}
				// ない場合は削除
				if(!hit)
				{
					// イベント日付を削除
					EventDate eventDate = eventDateService.find(eventdateid);
					eventDateService.delete(eventdateid);
					eventidList.add(eventDate.getEventId());
				}
			}

			// コメント削除
			List<EventComment> eventCommentList = eventCommentService.findAll();
			for (EventComment eventComment : eventCommentList)
			{
				if(eventComment.getEventcommentPK().getMemberid().equals(id))
				{
					eventCommentService.delete(eventComment.getEventcommentPK().getMemberid(),eventComment.getEventcommentPK().getEventid());
				}
			}

			// イベント日付がなくなったイベントの削除
			for (Long eventid : eventidList)
			{
				List<EventDate> eventDateList = eventDateService.findAnyCondByEventid(eventid);
				if(eventDateList.isEmpty())
				{
					eventService.delete(eventid);
				}
			}

			// メンバの削除
			memberService.delete( id );

			return "forward:member";
		} catch (Exception e) {
			model.addAttribute("Message","例外発生");
			return "ng";
		}
	}
}
