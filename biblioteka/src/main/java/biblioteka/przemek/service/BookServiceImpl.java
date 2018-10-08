package biblioteka.przemek.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import biblioteka.przemek.dao.BooksDAO;
import biblioteka.przemek.model.Author;
import biblioteka.przemek.model.Book;
import biblioteka.przemek.model.BookToPage;
import biblioteka.przemek.model.SearchedBook;

@Service
public class BookServiceImpl implements BookService {

	@Autowired
	BooksDAO booksDAO;

	@Override
	@Transactional
	public List<Book> booksList() {

		return booksDAO.booksList();
	}

	@Override
	@Transactional
	public void addBook(Book book) {
		booksDAO.addBook(book);

	}

	@Override
	@Transactional
	public void deleteBook(Book book) {
		booksDAO.deleteBook(book);

	}

	@Override
	@Transactional
	public List<Book> findBookByTitle(String title) {

		return booksDAO.findBookByTitle(title);
	}

	@Override
	@Transactional
	public List<Book> findBookByAuthor(String author) {

		return null;
	}

	@Override
	@Transactional
	public Book findBookById(int id) {

		return booksDAO.findBookById(id);
	}

	@Override
	@Transactional
	public void updatekBook(Book book) {

		booksDAO.updateBook(book);
	}

	@Override
	@Transactional
	public List<Book> searchForBook(SearchedBook searchedBook) {
		if (searchedBook.getCategory().equals("Title")) {
			
			return booksDAO.findBookByTitle(searchedBook.getSearchedName());
		
		} else if (searchedBook.getCategory().equals("Author")) {
		
			return booksDAO.findBookByAuthor(searchedBook.getSearchedName());
		
		} else {
			
			return null;

		}
	}

	@Override
	public Book getBookFromForm(BookToPage bookTemp, Author author) {
		
		Book book = new Book();

		book.setId(bookTemp.getId());
		book.setTitle(bookTemp.getTitle());
		book.setIsbnNumber(bookTemp.getIsbnNumber());
		book.setPublicationDate(bookTemp.getDateFormat(bookTemp.getPublicationDate()));
		book.setAuthor(author);

		return book;
	}

	@Override
	public BookToPage getBookToForm(Book book) {
		BookToPage bookTemp = new BookToPage();

		bookTemp.setId(book.getId());
		bookTemp.setTitle(book.getTitle());
		bookTemp.setIsbnNumber(book.getIsbnNumber());
		bookTemp.setPublicationDate(bookTemp.getDateString(book.getPublicationDate()));
		bookTemp.setFirstNameAuthor(book.getAuthor().getFirstName());
		bookTemp.setLastNameAuthor(book.getAuthor().getLastName());
		bookTemp.setAuthorId(book.getAuthor().getId());
		return bookTemp;
	}

	@Override
	@Transactional
	public List<Book> getBorrowedBooks() {
		
		return booksDAO.getBorrowedBooks();
	}
	
	
}
