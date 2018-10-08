package biblioteka.przemek.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import biblioteka.przemek.model.Book;


@Repository
public class BooksDAOImpl implements BooksDAO {

	@Autowired
	SessionFactory sessionFactory;

	@Override
	public List<Book> booksList() {
		Session session = sessionFactory.getCurrentSession();

		List<Book> booksList = session.createQuery("from Book", Book.class).getResultList();

		return booksList;
	}

	@Override
	public void addBook(Book book) {

		Session session = sessionFactory.getCurrentSession();

		session.saveOrUpdate(book);

	}

	@Override
	public void deleteBook(Book book) {

		Session session = sessionFactory.getCurrentSession();

		session.delete(book);
	}

	@Override
	public List<Book> findBookByTitle(String title) {

		Session session = sessionFactory.getCurrentSession();

		List<Book> bookListByTitle = session.createQuery("from Book b where b.title =:temptitle", Book.class).setParameter("temptitle", title)
				.getResultList();

		return bookListByTitle;
	}

	@Override
	public List<Book> findBookByAuthor(String authorName) {
		
		String[] tempName = authorName.trim().split("\\s+");
		
		Session session = sessionFactory.getCurrentSession();

		List<Book> bookListByAuthor = session.createQuery("from Book b where b.author.firstName =:tempfirstName OR b.author.lastName =:templastName", Book.class)
				.setParameter("tempfirstName", tempName[0]).setParameter("templastName", tempName[1])
				.getResultList();

		return bookListByAuthor;
	}

	@Override
	public Book findBookById(int id) {
		Session session = sessionFactory.getCurrentSession();
		
		Book book = session.get(Book.class, id);
		return book;
	}

	@Override
	public void updateBook(Book book) {
		Session session = sessionFactory.getCurrentSession();
		session.update(book);
		
		
	}

	@Override
	public List<Book> getBorrowedBooks() {
		Session session = sessionFactory.getCurrentSession();

		List<Book> booksList = session.createQuery("from Book where borrowingDate is not null", Book.class).getResultList();

		return booksList;
	}

}
