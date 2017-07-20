package com.example.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.entity.EventDate;

public interface EventDateRepository extends JpaRepository<EventDate, Long> {

    @Query(value = "select x from EventDate x where x.eventid = :eventid")
    List<EventDate> findByEventId(@Param("eventid")Long eventid);

}