package com.tweetapp.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.tweetapp.entity.UserJpa;
import com.tweetapp.model.TweetTo;
import com.tweetapp.service.TweetService;
import com.tweetapp.service.UserService;

@RestController
@RequestMapping("/tweets/v1")
@CrossOrigin
public class TweetController {

	@Autowired
	private TweetService tweetService;

	@Autowired
	private UserService userService;

	@GetMapping("/view-all-tweets/{userName}")
	public ResponseEntity<List<TweetTo>> listAllTweets(@PathVariable String userName) {
		if (userService.findByUsername(userName) == null) {
			return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
		}
		List<TweetTo> tweets = tweetService.listAllTweets();
		return new ResponseEntity<List<TweetTo>>(tweets, HttpStatus.OK);
	}

	@GetMapping("/{userName}")
	public ResponseEntity<List<TweetTo>> get(@PathVariable String userName) {
		if (userService.findByUsername(userName) == null) {
			return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
		}
		List<TweetTo> tweets = tweetService.getTweetsForUser(userName);
		return new ResponseEntity<>(tweets, HttpStatus.OK);
	}

	@PostMapping("/post-tweet")
	public ResponseEntity<String> add(@RequestBody TweetTo tweet) {
		UserJpa userJpa = userService.findByUsername(tweet.getUserName());
		if (userJpa == null) {
			return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
		}
		String response = tweetService.postTweet(tweet, userJpa);
		return new ResponseEntity<String>(response, HttpStatus.OK);
	}

}
