package com.email.management.system.app.repository;

import com.email.management.system.app.model.SentMails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SentMailsRepository extends JpaRepository<SentMails, Long> { }