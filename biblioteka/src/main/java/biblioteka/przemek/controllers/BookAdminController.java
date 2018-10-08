package biblioteka.przemek.controllers;



import java.time.LocalDate;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import biblioteka.przemek.model.Author;
import biblioteka.przemek.model.Book;
import biblioteka.przemek.model.BookToPage;
import biblioteka.przemek.model.LibraryUser;
import biblioteka.przemek.service.AuthorService;
import biblioteka.przemek.service.BookService;
import biblioteka.przemek.service.UserService;

@Controller
@RequestMapping(path="/bookAdmin")
public class BookAdminController {

	@Autowired
	AuthorService authorService;

	@Autowired
	BookService bookService;

	@Autowired
	UserService userService;

	@RequestMapping(path="/goAddBook")
	public String goAddBook(Model model) {

		model.addAttribute("newBook", new BookToPage());
		return "addBookPage";
	}

	@RequestMapping(path="/addBook")
	public String addBook(@Valid @ModelAttribute("newBook") BookToPage bookPage, BindingResult bindingResult, Model model) {
		
		if(bindingResult.hasErrors()) {
			
			return "addBookPage";
			
		} else {
		
		Book book = new Book();

		Author author = authorService.getAuthorFromForm(bookPage);
		Author authorExisted = authorService.checkIfAuthorExist(author);

		if (authorExisted == null) {
			book = bookService.getBookFromForm(bookPage, author);

		} else {
			book = bookService.getBookFromForm(bookPage, authorExisted);

		}

		bookService.addBook(book);

		return "redirect:/";
		}
	}

	@RequestMapping(path = "/goUpdateBook")
	public String goUpdateBook(@RequestParam("bookId") int id, Model model) {

		Book book = bookService.findBookById(id);
		BookToPage bookPage = bookService.getBookToForm(book);

		model.addAttribute("bookToUpdate", bookPage);
		return "updateBookPage";
	}

	@RequestMapping(path="/updateBook")
	public String updateBook(@ModelAttribute("bookToUpdate") BookToPage bookTemp, Model model) {

		Author author = authorService.getAuthorFromForm(bookTemp);
		Book book = bookService.getBookFromForm(bookTemp, author);

		bookService.updatekBook(book);

		return "redirect:/bookUser/listBooks";
	}

	@RequestMapping(path = "/deleteBook")
	public String deleteBook(@RequestParam("bookId") int id, Model model) {
		
		Book book = bookService.findBookById(id);
		Author author = authorService.findAuthorById(book.getAuthor().getId());
		author.removeBook(book);
		book.setAuthor(null);
		book.setBorrowingUser(null);
		book.setReservationUser(null);
		bookService.deleteBook(book);

		return "redirect:/bookUser/listBooks";
	}

	@RequestMapping(path = "/lendBook")
	public String lendBook(@RequestParam("bookId") int bookId) {

		Book book = bookService.findBookById(bookId);
		LibraryUser user = book.getReservationUser();
		book.setBorrowingUser(user);
		book.setReservationUser(null);
		book.setBorrowingDate(LocalDate.now());
		bookService.updatekBook(book);

		return "redirect:/bookUser/listBooks";
	}

	@RequestMapping(path = "/authorDetail")
	public String authorDetail(@RequestParam("authorId") int authorId, Model model) {
		
		Author author = authorService.findAuthorById(authorId);
		
		model.addAttribute("authorDetail", author);
		
		return "authorDetailPage";
	}
	
	@RequestMapping(path="/giveBackBook")
	public String giveBackBook(@RequestParam("bookId") int bookId) {
		
		Book book = bookService.findBookById(bookId);
		book.setBorrowingUser(null);
		book.setBorrowingDate(null);
		bookService.updatekBook(book);
		
		return "redirect:/bookUser/listBooks";
	}
	
	
	
	@InitBinder
	public void initBinder(WebDataBinder webDataBinder) {
		StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
		
		webDataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
		
	}
	
	
}
