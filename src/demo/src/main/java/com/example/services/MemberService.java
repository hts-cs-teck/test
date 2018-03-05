package com.example.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.dao.MemberRepository;
import com.example.dao.TeamRepository;
import com.example.entity.Member;
import com.example.entity.Team;

@Service
@Transactional
public class MemberService {
    @Autowired
	MemberRepository repository;

    @Autowired
	TeamRepository teamRepository;

	@Transactional(readOnly = true, timeout = 10)
	public List<Member> findAll() {
		return repository.findAll();
	}

	@Transactional(readOnly = true, timeout = 10)
	public Member find(final Long id) {
		return repository.findOne(id);
	}

	@Transactional(readOnly = true, timeout = 10)
	public List<Member> findAnyCondByAll(final Long teamid, final String name) {
		return repository.findAnyCondByAll(teamid, name);
	}

	@Transactional(readOnly = true, timeout = 10)
	public List<Member> findAnyCondByAlls(final Long teamid, final String name) {
		Set<Long> teamids = new HashSet<Long>();
		teamids.add(teamid);
		List<Member> members = new ArrayList<Member>();
		findAnyCondByAlls(members, teamids, name);
		Collections.sort(members, new Comparator<Member>() {
			@Override
			public int compare(Member left, Member right) {
				return (int) (left.getId().longValue() - right.getId().longValue());
			}
		});
		return members;
	}

	public void findAnyCondByAlls(List<Member> members, final Set<Long> teamids, final String name) {
		members.addAll(repository.findAnyCondByAlls(teamids, name));
		List<Team> teamds = teamRepository.findAnyCondByParentids(teamids);
		Set<Long> steamids = new HashSet<Long>();
		for(Team team: teamds) {
			steamids.add(team.getId());
		}
		if (steamids.isEmpty()) {
			return;
		}
		findAnyCondByAlls(members, steamids, name);
	}

	@Transactional(readOnly = true, timeout = 10)
	public List<Member> findAnyCondByTeamid(final Long teamid) {
		return repository.findAnyCondByTeamid(teamid);
	}

	@Transactional(readOnly = true, timeout = 10)
	public List<Member> findAnyCondByEmployeeid(final Long employeeid) {
		return repository.findAnyCondByEmployeeid(employeeid);
	}

	@Transactional(readOnly = true, timeout = 10)
	public List<Member> findAnyCondByTeamids(final Long teamid) {
		Set<Long> teamids = new HashSet<Long>();
		teamids.add(teamid);
		List<Member> members = new ArrayList<Member>();
		findAnyCondByTeamids(members, teamids);
		Collections.sort(members, new Comparator<Member>() {
			@Override
			public int compare(Member left, Member right) {
				return (int) (left.getId().longValue() - right.getId().longValue());
			}
		});
		return members;
	}

	public void findAnyCondByTeamids(List<Member> members, final Set<Long> teamids) {
		members.addAll(repository.findAnyCondByTeamids(teamids));
		List<Team> teamds = teamRepository.findAnyCondByParentids(teamids);
		Set<Long> steamids = new HashSet<Long>();
		for(Team team: teamds) {
			steamids.add(team.getId());
		}
		if (steamids.isEmpty()) {
			return;
		}
		findAnyCondByTeamids(members, steamids);
	}

	@Transactional(readOnly = true, timeout = 10)
	public List<Member> findAnyCondByName(final String name) {
		return repository.findAnyCondByName(name);
	}

	@Transactional(rollbackFor = {Exception.class}, timeout = 3)
	public Member save(final Member member) {
		return repository.save(member);
	}

	@Transactional(rollbackFor = {Exception.class}, timeout = 3)
	public void delete(final Long id) {
		repository.delete(id);
	}

	@Transactional(readOnly = true, timeout = 10)
	public List<Member> findByName(final String name) {
		return repository.findByName(name);
	}

}