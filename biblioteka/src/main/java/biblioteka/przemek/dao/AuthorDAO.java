package biblioteka.przemek.dao;

import biblioteka.przemek.model.Author;

public interface AuthorDAO {
	
	public void addAuthor(Author author);

	public void updateAuthor(Author author);

	public Author checkIfAuthorExist(Author author);

	public Author findAuthorById(int id);
}
