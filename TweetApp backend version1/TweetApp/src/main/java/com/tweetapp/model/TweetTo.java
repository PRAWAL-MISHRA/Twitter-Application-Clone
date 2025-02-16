package com.tweetapp.model;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;

public class TweetTo implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer tweetId;

	@NotBlank(message = "Tweet is mandatory")
	private String description;

	@NotBlank(message = "User Name is mandatory")
	private String userName;

	private Integer userId;

	public Integer getTweetId() {
		return tweetId;
	}

	public void setTweetId(Integer tweetId) {
		this.tweetId = tweetId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String tweet) {
		this.description = tweet;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

}
