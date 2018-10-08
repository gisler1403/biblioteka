package biblioteka.przemek.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


import biblioteka.przemek.model.Author;

@Repository
public class AuthorDAOImpl implements AuthorDAO {
	
	@Autowired
	SessionFactory sessionFactory;
	
	@Override
	public void addAuthor(Author author) {
		Session session = sessionFactory.getCurrentSession();
		
		session.saveOrUpdate(author);
	}

	@Override
	public void updateAuthor(Author author) {
		Session session = sessionFactory.getCurrentSession();
		
		session.update(author);
		
	}

	@Override
	public Author checkIfAuthorExist(Author author) {
		Author authorLocal = new Author();
		
		Session session = sessionFactory.getCurrentSession();
		
		
		authorLocal = session.createQuery("from Author a where a.firstName =:fNameTemp and a.lastName =:lNameTemp", Author.class)
				.setParameter("fNameTemp", author.getFirstName()).setParameter("lNameTemp", author.getLastName()).uniqueResult();
		
		return authorLocal;
		
		
	}

	@Override
	public Author findAuthorById(int id) {
		Session session = sessionFactory.getCurrentSession();
		Author author = session.get(Author.class, id);
		return author;
	}

}
