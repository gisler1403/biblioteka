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
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
@Table(name = "library_users")
public class LibraryUser {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@Column(name = "username")
	@NotNull(message="To pole nie mo¿e byæ puste")
	private String username;
	
	@Column(name = "email")
	@NotNull(message="To pole nie mo¿e byæ puste")
	@Pattern(regexp = "[\\w]+[@]{1}[\\w]+[\\.]{1}[\\w]+", message="Format maila xxxx@xx.xxx")
	private String email;
	
	@Transient
	@Size(min = 4, message = "Has³o min. 4")
	private String password;

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "borrowingUser")
	@Cascade({CascadeType.SAVE_UPDATE, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH,
		CascadeType.DETACH })
	private List<Book> borrowedBooks;
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "reservationUser")
	@Cascade({CascadeType.SAVE_UPDATE, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH,
		CascadeType.DETACH })
	private List<Book> reservedBooks;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Book> getBorrowedBooks() {
		return borrowedBooks;
	}

	public void setBorrowedBooks(List<Book> borrowedBooks) {
		this.borrowedBooks = borrowedBooks;
	}
	
	public List<Book> getReservedBooks() {
		return reservedBooks;
	}

	public void setReservedBooks(List<Book> reservedBooks) {
		this.reservedBooks = reservedBooks;
	}

	public void addBorrowedBook(Book book) {

		if (borrowedBooks == null) {

			borrowedBooks = new ArrayList<>();
		}

		borrowedBooks.add(book);
	
	}
	
	public void addReservedBook(Book book) {
		
		if(reservedBooks == null) {
			
			reservedBooks = new ArrayList<>();
		}
		
		reservedBooks.add(book);
	}

	public void removeResevedBook(Book book) {
		if (reservedBooks.isEmpty()) {
			System.out.println("No books");
		}else {
			Book bookTemp = new Book();
			Iterator<Book> iter = reservedBooks.iterator();
			while(iter.hasNext()) {
				bookTemp = (Book) iter.next();
				if(book.getId() == bookTemp.getId()) {
					iter.remove();
				}
			}
			
		}
		
		if(reservedBooks == null) {
			
		}else {
			
			reservedBooks.remove(book);
		}
	}
}
