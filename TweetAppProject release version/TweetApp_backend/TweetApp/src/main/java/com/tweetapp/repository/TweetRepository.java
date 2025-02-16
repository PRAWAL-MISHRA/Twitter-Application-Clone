package com.tweetapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.tweetapp.entity.TweetJpa;

@Repository
public interface TweetRepository extends JpaRepository<TweetJpa, Integer> {

	@Query(value = "select tweet from TweetJpa tweet where tweet.user.id = ?1")
	public List<TweetJpa> findByUserId(Integer userId);
}
