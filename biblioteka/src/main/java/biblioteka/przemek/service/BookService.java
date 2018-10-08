package biblioteka.przemek.service;

import java.util.List;

import biblioteka.przemek.model.Author;
import biblioteka.przemek.model.Book;
import biblioteka.przemek.model.BookToPage;
import biblioteka.przemek.model.SearchedBook;

public interface BookService {

	public List<Book> booksList();
	
	public List<Book> getBorrowedBooks();

	public void addBook(Book book);

	public void deleteBook(Book book);

	public List<Book> findBookByTitle(String title);

	public List<Book> findBookByAuthor(String author);

	public Book findBookById(int id);

	public void updatekBook(Book book);

	public List<Book> searchForBook(SearchedBook searchedBook);
	
	public Book getBookFromForm(BookToPage book, Author author);
	
	public BookToPage getBookToForm(Book book);
}
