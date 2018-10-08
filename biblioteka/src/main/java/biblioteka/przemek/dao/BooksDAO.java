package biblioteka.przemek.dao;

import java.util.List;


import biblioteka.przemek.model.Book;

public interface BooksDAO {

	public List<Book> booksList();
	
	public List<Book> getBorrowedBooks();
	
	public void addBook(Book book);
	
	public void deleteBook(Book book);
	
	public List<Book> findBookByTitle(String title);
	
	public List<Book> findBookByAuthor(String author);

	public Book findBookById(int id);

	public void updateBook(Book book);
	
	
}
