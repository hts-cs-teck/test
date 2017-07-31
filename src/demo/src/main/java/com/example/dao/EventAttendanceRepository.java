package com.example.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.entity.EventAttendance;
import com.example.entity.pk.EventAttendancePK;

public interface EventAttendanceRepository extends JpaRepository<EventAttendance, EventAttendancePK> {
}