package com.example.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.entity.Event;

public interface EventRepository extends JpaRepository<Event, Long> {
}