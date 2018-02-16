package com.example.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.entity.EventAttendance;
import com.example.entity.pk.EventAttendancePK;

public interface EventAttendanceRepository extends JpaRepository<EventAttendance, EventAttendancePK> {

	@Query("select a from EventAttendance a, EventDate d where a.eventAttendancePK.memberid = :memberid and a.eventAttendancePK.eventdateid = d.id and d.eventid = :eventid")
	List<EventAttendance> findByMemberidAndEventid(@Param("memberid")Long memberid, @Param("eventid")Long eventid);
}