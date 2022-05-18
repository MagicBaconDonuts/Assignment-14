package com.assignment14.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.assignment14.domain.Channel;
import com.assignment14.domain.User;
@Repository
public interface ChannelRepository extends JpaRepository<Channel, Long> {


}
