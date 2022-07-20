package com.kavan.bookproject.models;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "books")
public class Book {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank(message = "Title cannot be blank.")
	private String title;

	@NotBlank(message = "Author cannot be blank.")
	private String author;

	@NotEmpty(message = "Genre(s) cannot be blank.")
	private String[] genres;

	@Column(updatable = false)
	@OneToMany(mappedBy = "currentlyReading", fetch = FetchType.LAZY)
	private List<User> usersReading;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "users_likes", joinColumns = @JoinColumn(name = "book_id"), inverseJoinColumns = @JoinColumn(name = "user_id"))
	private List<User> likes;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "users_future", joinColumns = @JoinColumn(name = "book_id"), inverseJoinColumns = @JoinColumn(name = "user_id"))
	private List<User> future;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "users_past", joinColumns = @JoinColumn(name = "book_id"), inverseJoinColumns = @JoinColumn(name = "user_id"))
	private List<User> past;

	@Column(updatable = false)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date createdAt;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date updatedAt;

	public Book() {

	}

	public Book(String title, String author, String[] genres) {
		this.title = title;
		this.author = author;
		this.genres = genres;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public List<User> getLikes() {
		return likes;
	}

	public void setLikes(List<User> likes) {
		this.likes = likes;
	}

	public String[] getGenres() {
		return genres;
	}

	public void setGenres(String[] genres) {
		this.genres = genres;
	}


	public List<User> getUsersReading() {
		return usersReading;
	}

	public void setUsersReading(List<User> usersReading) {
		this.usersReading = usersReading;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public List<User> getFuture() {
		return future;
	}

	public void setFuture(List<User> future) {
		this.future = future;
	}

	public List<User> getPast() {
		return past;
	}

	public void setPast(List<User> past) {
		this.past = past;
	}

}
