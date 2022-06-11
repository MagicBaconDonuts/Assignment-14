package com.assignment14.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.assignment14.domain.Message;
@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {

	
	List<Message> findByChannel_id(Long channelId);


}
