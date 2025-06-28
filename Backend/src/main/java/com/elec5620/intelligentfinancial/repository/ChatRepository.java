package com.elec5620.intelligentfinancial.repository;

import com.elec5620.intelligentfinancial.model.Chat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChatRepository extends JpaRepository<Chat, Long> {
    List<Chat> findByCustomerId(Long customerId);
}