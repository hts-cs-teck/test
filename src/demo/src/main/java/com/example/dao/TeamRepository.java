package com.example.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.entity.Team;

public interface TeamRepository extends JpaRepository<Team, Long> {
}