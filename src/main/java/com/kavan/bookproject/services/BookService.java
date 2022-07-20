package com.kavan.bookproject.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.kavan.bookproject.models.Book;
import com.kavan.bookproject.repositories.BookRepo;

@Service
public class BookService {
//	Member Variable is the Repository
	private final BookRepo bookRepo;

//	Constructor
	public BookService(BookRepo bookRepo) {
		this.bookRepo = bookRepo;
	}

//	Create
	public Book createBook(Book book) {
		return bookRepo.save(book);
	}

//	Read All
	public List<Book> allBooks() {
		return bookRepo.findAll();
	}
	
//	Read All with Ids
	public Iterable<Book> filteredBooks(List<Long> ids) {
		return bookRepo.findAllById(ids);
	}

//	Read One
	public Book oneBook(Long id) {
		Optional<Book> book = bookRepo.findById(id);
		if (book.isPresent()) {
			return book.get();
		} else {
			return null;
		}
	}


//	Update
	public Book updateBook(Book book) {
		return bookRepo.save(book);
	}

//	Delete
	public void deleteBook(Book book) {
		bookRepo.delete(book);
	}
}