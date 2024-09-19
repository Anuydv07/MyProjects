package com.weconnect.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.weconnect.models.Reels;

public interface ReelsRepository extends JpaRepository<Reels,Integer>{

	
	public List<Reels> findByUserId(Integer userId);
}
