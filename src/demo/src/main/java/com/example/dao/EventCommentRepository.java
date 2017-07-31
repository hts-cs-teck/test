package com.example.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.entity.EventComment;
import com.example.entity.pk.EventCommentPK;

public interface EventCommentRepository extends JpaRepository<EventComment, EventCommentPK> {
}