package com.example.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.dao.MemberRepository;
import com.example.entity.Member;

@Service
@Transactional
public class MemberService {

	@Autowired
	MemberRepository repository;

	@Transactional(readOnly = true, timeout = 10)
	public List<Member> findAll() {
		return repository.findAll();
	}

	@Transactional(readOnly = true, timeout = 10)
	public Member find(final Long id) {
		return repository.findOne(id);
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