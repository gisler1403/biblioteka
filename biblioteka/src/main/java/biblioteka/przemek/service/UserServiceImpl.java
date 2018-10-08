package biblioteka.przemek.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import biblioteka.przemek.dao.UserDAO;
import biblioteka.przemek.model.LibraryUser;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	UserDAO userDAO;
	
	@Override
	@Transactional
	public void addUser(LibraryUser user) {
		userDAO.addUser(user);		
	}

	@Override
	@Transactional
	public void deleteUser(LibraryUser user) {
		userDAO.deleteUser(user);
	}

	@Override
	@Transactional
	public void updateUser(LibraryUser user) {
		userDAO.updateUser(user);
	}

	@Override
	@Transactional
	public List<LibraryUser> getUserList() {
		return userDAO.getUserList();
	}

	@Override
	@Transactional
	public LibraryUser getUserByName(String name) {
		
		return userDAO.getUserByName(name);
	}

	@Override
	@Transactional
	public LibraryUser findUserById(int userId) {
		
		return userDAO.findUserById(userId);
	}



}
