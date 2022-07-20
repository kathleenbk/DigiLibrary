package com.kavan.bookproject.controllers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.kavan.bookproject.models.Book;
import com.kavan.bookproject.models.LoginUser;
import com.kavan.bookproject.models.User;
import com.kavan.bookproject.services.BookService;
import com.kavan.bookproject.services.UserService;

@Controller
public class HomeController {

	@Autowired
	private UserService userService;

	@Autowired
	private BookService bookService;

	@GetMapping("/")
	public String index(Model model, HttpSession session) {
		if (session.getAttribute("userId") == null) {
			return "main.jsp";
		}
		return "redirect:/home";
	}

	@GetMapping("/register")
	public String register(Model model) {
		model.addAttribute("newUser", new User());
		model.addAttribute("newLogin", new LoginUser());
		return "index.jsp";
	}

	@PostMapping("/register")
	public String register(@Valid @ModelAttribute("newUser") User newUser, BindingResult result, Model model,
			HttpSession session) {

		User user = userService.register(newUser, result);

		if (result.hasErrors()) {

			model.addAttribute("newLogin", new LoginUser());
			return "index.jsp";
		}
		session.setAttribute("userId", user.getId());

		return "redirect:/home";
	}

	@PostMapping("/login")
	public String login(@Valid @ModelAttribute("newLogin") LoginUser newLogin, BindingResult result, Model model,
			HttpSession session) {

		User user = userService.login(newLogin, result);

		if (result.hasErrors()) {
			model.addAttribute("newUser", new User());
			return "index.jsp";
		}

		session.setAttribute("userId", user.getId());

		return "redirect:/home";
	}

	@GetMapping("/logout")
	public String logout(HttpSession session, Model model) {
		session.setAttribute("userId", null);
		return "redirect:/";
	}

	@GetMapping("/home")
	public String home(Model model, HttpSession session) {
		if (session.getAttribute("userId") == null) {
			return "redirect:/logout";
		}
		Long userId = (Long) session.getAttribute("userId");
		model.addAttribute("user", userService.findUser(userId));

		List<Book> books = bookService.allBooks();
		Collections.reverse(books);

		model.addAttribute("books", books);
		return "home.jsp";
	}

	@GetMapping("/about")
	public String about(Model model) {
		return "about.jsp";
	}

	@GetMapping("/search")
	public String search(@RequestParam("searchString") String searchString, Model model, HttpSession session) {
		if (session.getAttribute("userId") == null) {
			return "redirect:/logout";
		}
		List<Book> allBooks = bookService.allBooks();
		List<Long> ids = new ArrayList<Long>();
		for (Book book : allBooks) {
			if ((book.getTitle().toLowerCase().contains(searchString.toLowerCase())
					|| book.getAuthor().toLowerCase().contains(searchString.toLowerCase()))) {
				ids.add(book.getId());
			}
			for (int i = 0; i < book.getGenres().length; i++) {
				if ((book.getGenres()[i].toLowerCase().contains(searchString.toLowerCase()))) {
					ids.add(book.getId());
				}
			}
		}

		Iterable<Book> books = bookService.filteredBooks(ids);
		model.addAttribute("books", books);
		return "results.jsp";
	}

	@GetMapping("/find")
	public String find(@RequestParam("username") String username, Model model, HttpSession session) {
		Long userId = (Long) session.getAttribute("userId");
		if (userId == null) {
			return "redirect:/logout";
		}
		User user = userService.findUser(userId);
		User user2 = userService.findByUsername(username);
		if (user2 == null) {
			String result = new String("That username does not exist.");
			model.addAttribute("user", user);
			model.addAttribute("result", result);
			return "profile.jsp";
		}
		Long searchId = user2.getId();
		
		return "redirect:/user/" + searchId;
	}

	@GetMapping("/follow/{id}")
	public String follow(@PathVariable("id") Long id, Model model, HttpSession session) {
		Long userId = (Long) session.getAttribute("userId");
		if (userId == null) {
			return "redirect:/logout";
		}
		if(userId == id) {
			return "redirect:/profile";
		}
		User user = userService.findUser(userId);
		User user2 = userService.findUser(id);
		
		if (user.getFollowing().contains(user2)) {
			model.addAttribute("user", user);
			return "redirect:/user/{id}";
		}
		user2.getFollowers().add(user);
		user.getFollowing().add(user2);
		userService.update(user2);
		userService.update(user);
		model.addAttribute("user", user);
		return "redirect:/user/{id}";
	}

	@GetMapping("/unfollow/{id}")
	public String unfollow(@PathVariable("id") Long id, Model model, HttpSession session) {
		Long userId = (Long) session.getAttribute("userId");
		if (userId == null) {
			return "redirect:/logout";
		}

		User user = userService.findUser(userId);
		User user2 = userService.findUser(id);

		if (user.getFollowing().contains(user2)) {
			user2.getFollowers().remove(user);
			user.getFollowing().remove(user2);
			userService.update(user2);
			userService.update(user);
			model.addAttribute("user", user);
			return "redirect:/user/{id}";
		}
		model.addAttribute("user", user);
		return "redirect:/user/{id}";

	}

	@GetMapping("/user/{id}")
	public String user(@PathVariable("id") Long id, Model model, HttpSession session) {
		Long userId = (Long) session.getAttribute("userId");
		if (userId == null) {
			return "redirect:/logout";
		}
		User user = userService.findUser(userId);
		User user2 = userService.findUser(id);
		if(user == user2) {
			model.addAttribute("user", user);
			return "redirect:/profile";
		}
		model.addAttribute("user2", user2);
		model.addAttribute("user", user);
		
		return "user.jsp";
	}

	@GetMapping("/add")
	public String addForm(@ModelAttribute("book") Book book, Model model, HttpSession session) {
		return "add.jsp";
	}

	@PostMapping("/add")
	public String addBook(@Valid @ModelAttribute("book") Book book, BindingResult result, HttpSession session) {
		Long userId = (Long) session.getAttribute("userId");
		if (userId == null) {
			return "redirect:/logout";
		}
		if (result.hasErrors()) {
			System.out.println(result.getAllErrors());
			return "add.jsp";
		} else {
			bookService.createBook(new Book(book.getTitle(), book.getAuthor(), book.getGenres()));
			return "redirect:/home";
		}
	}

	@GetMapping("/books/{id}")
	public String book(@PathVariable("id") Long id, Model model, HttpSession session) {
		Long userId = (Long) session.getAttribute("userId");
		if (userId == null) {
			return "redirect:/logout";
		}
		model.addAttribute("user", userService.findUser(userId));
		Book book = bookService.oneBook(id);

		model.addAttribute("book", book);
		return "book.jsp";
	}

	@RequestMapping("/book/{id}/like")
	public String like(@PathVariable("id") Long id, Model model, HttpSession session) {
		Long userId = (Long) session.getAttribute("userId");
		if (userId == null) {
			return "redirect:/logout";
		}
		User user = userService.findUser(userId);
		Book book = bookService.oneBook(id);
		if (user.getLikes().contains(book)) {
			model.addAttribute("book", book);
			return "redirect:/books/{id}";
		}
		user.getLikes().add(book);
		userService.update(user);
		model.addAttribute("book", book);
		return "redirect:/books/{id}";
	}

	@RequestMapping("/book/{id}/unlike")
	public String unlike(@PathVariable("id") Long id, Model model, HttpSession session) {
		Long userId = (Long) session.getAttribute("userId");
		if (userId == null) {
			return "redirect:/logout";
		}
		User user = userService.findUser(userId);
		Book book = bookService.oneBook(id);
		if (user.getLikes().contains(book)) {
			user.getLikes().remove(book);
			userService.update(user);
			model.addAttribute("book", book);
			return "redirect:/books/{id}";
		}
		model.addAttribute("book", book);
		return "redirect:/books/{id}";
	}

	@RequestMapping("/book/{id}/read")
	public String addFuture(@PathVariable("id") Long id, Model model, HttpSession session) {
		Long userId = (Long) session.getAttribute("userId");
		if (userId == null) {
			return "redirect:/logout";
		}
		User user = userService.findUser(userId);
		Book book = bookService.oneBook(id);
		user.setCurrentlyReading(book);
		userService.update(user);
		model.addAttribute("book", book);

		return "redirect:/books/{id}";
	}

	@RequestMapping("/{id}/finish")
	public String finish(@PathVariable("id") Long id, Model model, HttpSession session) {
		Long userId = (Long) session.getAttribute("userId");
		if (userId == null) {
			return "redirect:/logout";
		}
		User user = userService.findUser(userId);
		Book book = bookService.oneBook(id);
		user.setCurrentlyReading(null);
		if (user.getPastReads().contains(book)) {
			userService.update(user);
			model.addAttribute("user", user);
			model.addAttribute("book", book);
			return "redirect:/profile";
		}
		user.getPastReads().add(book);
		userService.update(user);
		model.addAttribute("user", user);
		model.addAttribute("book", book);
		return "redirect:/profile";

	}

	@RequestMapping("/book/{id}/future")
	public String future(@PathVariable("id") Long id, Model model, HttpSession session) {
		Long userId = (Long) session.getAttribute("userId");
		if (userId == null) {
			return "redirect:/logout";
		}
		User user = userService.findUser(userId);
		Book book = bookService.oneBook(id);
		if (user.getFutureReads().contains(book)) {
			model.addAttribute("book", book);
			return "redirect:/books/{id}";
		}
		user.getFutureReads().add(book);
		userService.update(user);
		model.addAttribute("book", book);
		return "redirect:/books/{id}";
	}

	@RequestMapping("/book/{id}/future/remove")
	public String removeFuture(@PathVariable("id") Long id, Model model, HttpSession session) {
		Long userId = (Long) session.getAttribute("userId");
		if (userId == null) {
			return "redirect:/logout";
		}
		User user = userService.findUser(userId);
		Book book = bookService.oneBook(id);
		if (user.getFutureReads().contains(book)) {
			user.getFutureReads().remove(book);
			userService.update(user);
			model.addAttribute("book", book);
			return "redirect:/books/{id}";
		}
		model.addAttribute("book", book);
		return "redirect:/books/{id}";
	}

	@RequestMapping("/book/{id}/past")
	public String past(@PathVariable("id") Long id, Model model, HttpSession session) {
		Long userId = (Long) session.getAttribute("userId");
		if (userId == null) {
			return "redirect:/logout";
		}
		User user = userService.findUser(userId);
		Book book = bookService.oneBook(id);
		if (user.getPastReads().contains(book)) {
			model.addAttribute("book", book);
			return "redirect:/books/{id}";
		}
		user.getPastReads().add(book);
		userService.update(user);
		model.addAttribute("book", book);
		return "redirect:/books/{id}";
	}

	@RequestMapping("/book/{id}/past/remove")
	public String removePast(@PathVariable("id") Long id, Model model, HttpSession session) {
		Long userId = (Long) session.getAttribute("userId");
		if (userId == null) {
			return "redirect:/logout";
		}
		User user = userService.findUser(userId);
		Book book = bookService.oneBook(id);
		if (user.getPastReads().contains(book)) {
			user.getPastReads().remove(book);
			userService.update(user);
			model.addAttribute("book", book);
			return "redirect:/books/{id}";
		}
		model.addAttribute("book", book);
		return "redirect:/books/{id}";
	}

	@GetMapping("/profile")
	public String profile(Model model, HttpSession session) {
		Long userId = (Long) session.getAttribute("userId");
		if (userId == null) {
			return "redirect:/logout";
		}
		User user = userService.findUser(userId);
		model.addAttribute("user", user);
		return "profile.jsp";
	}

}
