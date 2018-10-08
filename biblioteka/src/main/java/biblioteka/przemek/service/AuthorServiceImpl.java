package biblioteka.przemek.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import biblioteka.przemek.dao.AuthorDAO;
import biblioteka.przemek.model.Author;
import biblioteka.przemek.model.BookToPage;

@Service
public class AuthorServiceImpl implements AuthorService {
	
	
	@Autowired
	AuthorDAO authorDAO;
	
	@Override
	@Transactional
	public void addAuthor(Author author) {
			authorDAO.addAuthor(author);

	}
	@Override
	@Transactional
	public void updateAuthor(Author author) {
		authorDAO.updateAuthor(author);
		
	}
	@Override
	@Transactional
	public Author checkIfAuthorExist(Author author) {
		
		return authorDAO.checkIfAuthorExist(author);
		
	}
	@Override
	@Transactional
	public Author findAuthorById(int id) {
		
		return authorDAO.findAuthorById(id);
	}
	@Override
	public Author getAuthorFromForm(BookToPage book) {
		Author author = new Author();
		
		author.setFirstName(book.getFirstNameAuthor());
		author.setLastName(book.getLastNameAuthor());
		author.setId(book.getAuthorId());
		
		return author;
	}

}
