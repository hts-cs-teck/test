package com.example.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.dao.EventDateRepository;
import com.example.entity.EventDate;

@Service
@Transactional
public class EventDateService {

	@Autowired
	EventDateRepository repository;

	@Transactional(readOnly = true, timeout = 10)
	public List<EventDate> findAll() {
		return repository.findAll();
	}

	@Transactional(readOnly = true, timeout = 10)
	public EventDate find(final Long id) {
		return repository.findOne(id);
	}

	@Transactional(rollbackFor = {Exception.class}, timeout = 3)
	public EventDate save(final EventDate eventDate) {
		return repository.save(eventDate);
	}

	@Transactional(rollbackFor = {Exception.class}, timeout = 3)
	public void delete(final Long id) {
		repository.delete(id);
	}

	@Transactional(readOnly = true, timeout = 10)
	public List<EventDate> findByEventid(final Long eventid) {
		return repository.findByEventid(eventid);
	}

}