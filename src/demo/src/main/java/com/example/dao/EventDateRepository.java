package com.example.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.entity.EventDate;

public interface EventDateRepository extends JpaRepository<EventDate, Long> {

    List<EventDate> findByEventid(Long eventid);

    @Query("from EventDate m where m.eventid = :eventid order by m.startDate")
	public List<EventDate> findAnyCondByEventid(@Param("eventid") Long eventid);
}