package com.tweetapp.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tweetapp.entity.TweetJpa;
import com.tweetapp.entity.UserJpa;
import com.tweetapp.model.TweetTo;
import com.tweetapp.repository.TweetRepository;
import com.tweetapp.repository.UserRepository;

@Service
public class TweetService {

	@Autowired
	private TweetRepository tweetRepository;

	@Autowired
	private UserRepository userRepository;

	public List<TweetTo> listAllTweets() {
		List<TweetTo> tweetToList = new ArrayList<>();
		tweetRepository.findAll().forEach(tweetJpa -> {
			TweetTo tweetTo = populateTweetToFromTweetJpa(tweetJpa);
			tweetToList.add(tweetTo);
		});
		return tweetToList;
	}

	public List<TweetTo> getTweetsForUser(String userName) {
		List<TweetTo> tweetToList = new ArrayList<>();
		UserJpa userJpa = userRepository.findByUsername(userName);
		userJpa.getTweetJpaList().forEach(tweetJpa -> {
			TweetTo tweetTo = populateTweetToFromTweetJpa(tweetJpa);
			tweetToList.add(tweetTo);
		});
		return tweetToList;
	}

	public String postTweet(TweetTo tweetTo, UserJpa userJpa) {
		TweetJpa tweetJpa = populateTweetJpaFromTweetTo(tweetTo, userJpa);
		tweetRepository.save(tweetJpa);
		return "Tweet Posted Successfully";
	}

	private TweetTo populateTweetToFromTweetJpa(TweetJpa tweetJpa) {
		TweetTo tweetTo = new TweetTo();
		tweetTo.setDescription(tweetJpa.getDescription());
		tweetTo.setTweetId(tweetJpa.getId());
		tweetTo.setUserId(tweetJpa.getUser().getId());
		tweetTo.setUserName(tweetJpa.getUser().getName());
		return tweetTo;
	}

	private TweetJpa populateTweetJpaFromTweetTo(TweetTo tweetTo, UserJpa userJpa) {
		TweetJpa tweetJpa = new TweetJpa();
		tweetJpa.setDescription(tweetTo.getDescription());
		tweetJpa.setUser(userJpa);
		return tweetJpa;
	}
}
