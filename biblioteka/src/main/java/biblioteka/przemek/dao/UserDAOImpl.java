package biblioteka.przemek.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import biblioteka.przemek.model.LibraryUser;

@Repository
public class UserDAOImpl implements UserDAO {

	@Autowired
	SessionFactory sessionFactory;

	@Override
	public void addUser(LibraryUser user) {
		Session session = sessionFactory.getCurrentSession();

		session.save(user);
	}

	@Override
	public void deleteUser(LibraryUser user) {
		Session session = sessionFactory.getCurrentSession();

		session.delete(user);

	}

	@Override
	public void updateUser(LibraryUser user) {
		Session session = sessionFactory.getCurrentSession();

		session.update(user);

	}

	@Override
	public List<LibraryUser> getUserList() {
		Session session = sessionFactory.getCurrentSession();

		List<LibraryUser> userList = session.createQuery("from LibraryUser", LibraryUser.class).getResultList();

		return userList;
	}

	@Override
	public LibraryUser getUserByName(String name) {
		Session session = sessionFactory.getCurrentSession();

		LibraryUser user = session.createQuery("from LibraryUser u where u.username =:name", LibraryUser.class)
				.setParameter("name", name).getSingleResult();
		return user;
	}

	@Override
	public LibraryUser findUserById(int userId) {
	
		Session session = sessionFactory.getCurrentSession();
		
		LibraryUser user = session.get(LibraryUser.class, userId);
		return user;
	}

}
