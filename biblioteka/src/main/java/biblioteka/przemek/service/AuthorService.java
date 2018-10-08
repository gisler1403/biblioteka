package biblioteka.przemek.service;

import biblioteka.przemek.model.Author;
import biblioteka.przemek.model.BookToPage;

public interface AuthorService {
	
	public void addAuthor(Author author);

	public void updateAuthor(Author author);
	
	public Author checkIfAuthorExist(Author author);

	public Author findAuthorById(int id);
	
	public Author getAuthorFromForm(BookToPage book);
}
