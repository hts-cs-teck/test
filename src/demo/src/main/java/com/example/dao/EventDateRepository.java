package com.example.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.entity.EventDate;

public interface EventDateRepository extends JpaRepository<EventDate, Long> {

    List<EventDate> findByEventid(Long eventid);

}