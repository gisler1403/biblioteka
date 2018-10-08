package biblioteka.przemek.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
@Table(name = "authors")
public class Author {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name = "first_name")
	private String firstName;
	@Column(name = "last_name")
	private String lastName;

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "author")
	@Cascade({ CascadeType.SAVE_UPDATE, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH,
			CascadeType.DETACH })
	private List<Book> bookList;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public List<Book> getBookList() {
		return bookList;
	}

	public void setBookList(List<Book> bookList) {
		this.bookList = bookList;
	}

	public Author() {

	}

	public Author(int id, String firstName, String lastName) {

		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public void addBookToAuthor(Book book) {

		if (bookList == null) {

			bookList = new ArrayList<>();
		}

		bookList.add(book);

	}

	public void removeBook(Book book) {
		if (bookList.isEmpty()) {
			System.out.println("No books");
		}else {
			Book bookTemp = new Book();
			Iterator<Book> iter = bookList.iterator();
			while(iter.hasNext()) {
				bookTemp = (Book) iter.next();
				if(book.getId() == bookTemp.getId()) {
					iter.remove();
				}
			}
			
		}
		
	}

}
