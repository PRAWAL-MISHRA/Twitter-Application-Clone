package com.tweetapp.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "USER", schema = "edb")
public class UserJpa {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	private Integer id;

	@Column(name = "user_name")
	private String name;

	@Column(name = "user_password")
	private String password;

	@Column(name = "user_gender")
	private String gender;

	@Column(name = "user_email")
	private String email;

	@Column(name = "last_updated")
	@Temporal(value = TemporalType.TIMESTAMP)
	private Date lastUpdatedDate;

	@Column(name = "created_at")
	@Temporal(value = TemporalType.TIMESTAMP)
	private Date createdAtDate;

	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
	private List<TweetJpa> tweetJpaList;

	public Integer getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getLastUpdatedDate() {
		return lastUpdatedDate;
	}

	public void setLastUpdatedDate(Date lastUpdatedDate) {
		this.lastUpdatedDate = lastUpdatedDate;
	}

	public Date getCreatedAtDate() {
		return createdAtDate;
	}

	public void setCreatedAtDate(Date createdAtDate) {
		this.createdAtDate = createdAtDate;
	}

	public List<TweetJpa> getTweetJpaList() {
		return tweetJpaList;
	}

	public void setTweetJpaList(List<TweetJpa> tweetJpaList) {
		this.tweetJpaList = tweetJpaList;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "UserJpa{" +
				"id=" + id +
				", name='" + name + '\'' +
				", password='" + password + '\'' +
				", gender='" + gender + '\'' +
				", email='" + email + '\'' +
				", lastUpdatedDate=" + lastUpdatedDate +
				", createdAtDate=" + createdAtDate +
				", tweetJpaList=" + tweetJpaList +
				'}';
	}
}
