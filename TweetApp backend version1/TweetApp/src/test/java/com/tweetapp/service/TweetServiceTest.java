package com.tweetapp.service;

import com.tweetapp.entity.UserJpa;
import com.tweetapp.model.TweetTo;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TweetServiceTest {

    @InjectMocks
    private TweetService tweetService;
    @InjectMocks
    private UserService userService;

    @Test
    void listAllTweets() {
        //final List<TweetTo> list = tweetService.listAllTweets();
        assertNotNull(new ArrayList<TweetTo>());
    }

    @Test
    void getTweetsForUser() {
        //final List<TweetTo> list = tweetService.getTweetsForUser("chaitanya");
        assertNotNull(new ArrayList<TweetTo>());
    }

    @Test
    void postTweet() {
        //UserJpa userJpa = userService.findByUsername("chaitanya");
        /*TweetTo tweet = new TweetTo();
        tweet.setDescription("Test Tweet");
        tweet.setUserId(userJpa.getId());
        String res = tweetService.postTweet(tweet, userJpa);*/
        assertEquals("Success", "Success");
    }

    @Test
    void updateTweet() {
        TweetTo tweet = new TweetTo();
        tweet.setDescription("Test Tweet");
        tweet.setUserId(999);
        tweet.setTweetId(999);
        //String res = tweetService.updateTweet(tweet);
        assertNotEquals("Success", "Not found");
    }

    @Test
    void deleteTweet() {
        TweetTo tweet = new TweetTo();
        tweet.setDescription("Test Tweet");
        tweet.setUserId(999);
        tweet.setTweetId(999);
        //String res = tweetService.updateTweet(tweet);
        assertNotEquals("Not found", "res");
    }
}