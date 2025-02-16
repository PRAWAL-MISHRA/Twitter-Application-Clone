package com.tweetapp.tweetappcli.restclient;

import com.tweetapp.tweetappcli.model.BaseUserTo;
import com.tweetapp.tweetappcli.model.ChangePasswordTo;
import com.tweetapp.tweetappcli.model.CreateUserTo;
import com.tweetapp.tweetappcli.model.TweetTo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient("GATEWAY-SERVICE")
public interface TweetAppClient {

    @PostMapping("/tweetapp/users/v1/signup")
    BaseUserTo signUp(@RequestBody CreateUserTo createUserTo);

    @PostMapping("/tweetapp/users/v1/login")
    BaseUserTo login(@RequestBody BaseUserTo userTo);

    @PostMapping("/tweetapp/users/v1/change-password")
    String changePassword(@RequestBody ChangePasswordTo changePasswordTo);

    @GetMapping("/tweetapp/users/v1/get-all-users")
    List<BaseUserTo> listAllUsers();

    @GetMapping("/tweetapp/tweets/v1/view-all-tweets/{userName}")
    List<TweetTo> listAllTweets(@PathVariable String userName);

    @GetMapping("/tweetapp/tweets/v1/{userName}")
    List<TweetTo> getUserTweets(@PathVariable String userName);

    @PostMapping("/tweetapp/tweets/v1/post-tweet")
    String postTweet(@RequestBody TweetTo tweet);

}
