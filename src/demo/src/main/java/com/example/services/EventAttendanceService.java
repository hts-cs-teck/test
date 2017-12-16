package com.example.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.dao.EventAttendanceRepository;
import com.example.entity.EventAttendance;
import com.example.entity.pk.EventAttendancePK;

@Service
@Transactional
public class EventAttendanceService {

	@Autowired
	EventAttendanceRepository repository;

	@Transactional(readOnly = true, timeout = 10)
	public List<EventAttendance> findAll() {
		return repository.findAll();
	}

	@Transactional(rollbackFor = {Exception.class}, timeout = 3)
	public EventAttendance save(final EventAttendance eventAttendance) {
		return repository.save(eventAttendance);
	}

	@Transactional(rollbackFor = {Exception.class}, timeout = 3)
	public void delete(final Long memberid, final Long eventdateid) {
		EventAttendancePK pk = new EventAttendancePK();
		pk.setMemberid(memberid);
		pk.setEventdateid(eventdateid);
		repository.delete(pk);
	}

	@Transactional(rollbackFor = {Exception.class}, timeout = 3)
	public void deleteByPK(EventAttendancePK pk) {
		repository.delete(pk);
	}

	@Transactional(readOnly = true, timeout = 10)
	public EventAttendance findByPK(final Long memberid, final Long eventdateid) {
		EventAttendancePK pk = new EventAttendancePK();
		pk.setMemberid(memberid);
		pk.setEventdateid(eventdateid);
		return repository.findOne(pk);
	}
}