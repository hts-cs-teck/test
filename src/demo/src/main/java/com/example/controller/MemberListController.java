package com.example.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.dto.MemberListDto;
import com.example.dto.TeamDto;
import com.example.entity.Member;
import com.example.entity.Team;
import com.example.model.MemberListModel;
import com.example.model.SessionModel;
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
}
