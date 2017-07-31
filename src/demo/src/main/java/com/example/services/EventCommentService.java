package com.example.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.dao.EventCommentRepository;
import com.example.entity.EventComment;
import com.example.entity.pk.EventCommentPK;

@Service
@Transactional
public class EventCommentService {

	@Autowired
	EventCommentRepository repository;

	@Transactional(readOnly = true, timeout = 10)
	public List<EventComment> findAll() {
		return repository.findAll();
	}

	@Transactional(rollbackFor = {Exception.class}, timeout = 3)
	public EventComment save(final EventComment eventComment) {
		return repository.save(eventComment);
	}

	@Transactional(rollbackFor = {Exception.class}, timeout = 3)
	public void delete(final Long memberid, final Long eventid) {
		EventCommentPK pk = new EventCommentPK();
		pk.setMemberid(memberid);
		pk.setEventid(eventid);
		repository.delete(pk);
	}

	@Transactional(readOnly = true, timeout = 10)
	public EventComment findByPK(final Long memberid, final Long eventid) {
		EventCommentPK pk = new EventCommentPK();
		pk.setMemberid(memberid);
		pk.setEventid(eventid);
		return repository.findOne(pk);
	}
}