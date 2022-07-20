package com.kavan.bookproject.models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;


@Entity
@Table(name = "users")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotEmpty(message = "Name is required!")
	@Size(min = 2, max = 30, message = "Name must be between 2 and 30 characters")
	private String firstName;
	
	@NotEmpty(message = "Username is required!")
	@Size(min = 2, max = 30, message = "Username must be between 2 and 30 characters")
	private String username;

	@NotEmpty(message = "Email is required!")
	@Email(message = "Please enter a valid email!")
	private String email;

	@NotEmpty(message = "Password is required!")
	@Size(min = 8, max = 128, message = "Password must be between 8 and 128 characters")
	private String password;

	@NotEmpty(message = "Confirm Password is required!")
	@Size(min = 8, max = 128, message = "Confirm Password must be between 8 and 128 characters")
	private String confirm;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "following",  joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "user2_id"))
	private List<User> following;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "followers",  joinColumns = @JoinColumn(name = "user2_id"), inverseJoinColumns = @JoinColumn(name = "user_id"))
	private List<User> followers;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "book_id")
	private Book currentlyReading;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "users_future", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "book_id"))
	private List<Book> futureReads;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "users_past", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "book_id"))
	private List<Book> pastReads;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "users_likes", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "book_id"))
	private List<Book> likes;
	

	public User() {
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}




	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getConfirm() {
		return confirm;
	}


	public void setConfirm(String confirm) {
		this.confirm = confirm;
	}



	public String getFirstName() {
		return firstName;
	}


	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	public List<Book> getLikes() {
		return likes;
	}


	public void setLikes(List<Book> likes) {
		this.likes = likes;
	}


	public Book getCurrentlyReading() {
		return currentlyReading;
	}


	public void setCurrentlyReading(Book currentlyReading) {
		this.currentlyReading = currentlyReading;
	}


	public List<Book> getFutureReads() {
		return futureReads;
	}


	public void setFutureReads(List<Book> futureReads) {
		this.futureReads = futureReads;
	}


	public List<Book> getPastReads() {
		return pastReads;
	}


	public void setPastReads(List<Book> pastReads) {
		this.pastReads = pastReads;
	}


	public List<User> getFollowing() {
		return following;
	}


	public void setFollowing(List<User> following) {
		this.following = following;
	}


	public List<User> getFollowers() {
		return followers;
	}


	public void setFollowers(List<User> followers) {
		this.followers = followers;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


}
