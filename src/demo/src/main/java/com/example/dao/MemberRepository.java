package com.example.dao;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.entity.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {

    List<Member> findByName(String name);

    @Query("from Member m where m.teamid = :teamid and m.name like %:name% order by m.id")
	public List<Member> findAnyCondByAll(@Param("teamid") Long teamid, @Param("name") String name);

    @Query("from Member m where m.teamid = :teamid order by m.id")
	public List<Member> findAnyCondByTeamid(@Param("teamid") Long teamid);

    @Query("from Member m where m.name like %:name% order by m.id")
	public List<Member> findAnyCondByName(@Param("name") String name);

    @Query("from Member m where m.teamid in (:teamids) and m.name like %:name% order by m.id")
	public List<Member> findAnyCondByAlls(@Param("teamids") Set<Long> teamids, @Param("name") String name);

    @Query("from Member m where m.teamid in (:teamids) order by m.id")
	public List<Member> findAnyCondByTeamids(@Param("teamids") Set<Long> teamids);

}