package biblioteka.przemek.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
@Table(name = "books")
public class Book {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "title")
	private String title;

	@Column(name = "isbn_number")
	private String isbnNumber;

	@Column(name = "publication_date")
	private LocalDate publicationDate;

	@ManyToOne(fetch = FetchType.EAGER)
	@Cascade({ CascadeType.SAVE_UPDATE, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH,
			CascadeType.DETACH })
	@JoinColumn(name = "author_id")
	private Author author;

	@ManyToOne
	@Cascade({ CascadeType.SAVE_UPDATE, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH,
			CascadeType.DETACH })
	@JoinColumn(name = "user_id")
	private LibraryUser borrowingUser;

	@ManyToOne
	@Cascade({ CascadeType.SAVE_UPDATE, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH,
			CascadeType.DETACH })
	@JoinColumn(name = "user_id_reservation")
	private LibraryUser reservationUser;

	@Column(name = "borrowingDate")
	private LocalDate borrowingDate;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getIsbnNumber() {
		return isbnNumber;
	}

	public void setIsbnNumber(String isbnNumber) {
		this.isbnNumber = isbnNumber;
	}

	public LocalDate getPublicationDate() {
		return publicationDate;
	}

	public void setPublicationDate(LocalDate publicationDate) {
		this.publicationDate = publicationDate;
		
	}
	
	public Author getAuthor() {
		return author;
	}

	public void setAuthor(Author author) {
		this.author = author;
	}

	public LibraryUser getReservationUser() {
		return reservationUser;
	}

	public void setReservationUser(LibraryUser reservationUser) {
		this.reservationUser = reservationUser;
	}
	
	public LibraryUser getBorrowingUser() {
		return borrowingUser;
	}

	public void setBorrowingUser(LibraryUser borrowingUser) {
		this.borrowingUser = borrowingUser;
	}

	public LocalDate getBorrowingDate() {
		return borrowingDate;
	}

	public void setBorrowingDate(LocalDate borrowingDate) {
		this.borrowingDate = borrowingDate;
	}

	public Book(int id, String title, String isbnNumber, LocalDate publicationDate) {
		this.id = id;
		this.title = title;
		this.isbnNumber = isbnNumber;
		this.publicationDate = publicationDate;
	}

	public Book() {

	}

	@Override
	public String toString() {
		return "Book [id=" + id + ", title=" + title + ", isbnNumber=" + isbnNumber + ", publicationDate="
				+ publicationDate + ", author=" + author + ", borrowingUser=" + borrowingUser + ", reservationUser="
				+ reservationUser + "]";
	}
	
	


}
