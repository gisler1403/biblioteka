package biblioteka.przemek.controllers;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import biblioteka.przemek.model.Book;
import biblioteka.przemek.model.LibraryUser;
import biblioteka.przemek.model.SearchedBook;
import biblioteka.przemek.service.BookService;
import biblioteka.przemek.service.UserService;

@Controller
@RequestMapping(path="/bookUser")
public class UserBookController {

	@Autowired
	BookService bookService;
	
	@Autowired
	UserService userService;
	
	@RequestMapping(path="/bookReservation")
	public String doBookReservation(@RequestParam("bookId") int id, Model model) {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		LibraryUser loggedUser = userService.getUserByName(auth.getName());
		
		Book book = bookService.findBookById(id);
		book.setReservationUser(loggedUser);
		bookService.updatekBook(book);
		
		return "redirect:/";
	}
	
	@RequestMapping(path="/listBooks")
	public String listBooks(Model model) {

		List<Book> booksList = bookService.booksList();
		
		model.addAttribute("listOfBooks", booksList);

		return "listBookPage";
	}
	
	
	@RequestMapping(path="/search")
	public String searchBook(@ModelAttribute("searchedBook") SearchedBook searchedBook, Model model) {
		
		List<Book> booksList = bookService.searchForBook(searchedBook);
	
		model.addAttribute("foundBook", booksList);
		
		return "bookDetailPage";
	}
}
