package biblioteka.przemek.service;

import java.util.List;

import biblioteka.przemek.model.LibraryUser;

public interface UserService {

	public void addUser(LibraryUser user);

	public void deleteUser(LibraryUser user);

	public void updateUser(LibraryUser user);

	public List<LibraryUser> getUserList();

	public LibraryUser getUserByName(String name);

	public LibraryUser findUserById(int userId);

}
