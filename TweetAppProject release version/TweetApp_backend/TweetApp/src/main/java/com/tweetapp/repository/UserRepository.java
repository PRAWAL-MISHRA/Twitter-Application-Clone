package com.tweetapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.tweetapp.entity.UserJpa;

@Repository
public interface UserRepository extends JpaRepository<UserJpa, Integer> {
	
	@Query(value = "select user from UserJpa user where user.name = ?1")
	public UserJpa findByUsername(String username);

}