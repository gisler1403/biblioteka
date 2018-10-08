package biblioteka.przemek.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;


public class BookToPage {

	private int id;

	@NotNull(message="To pole nie mo¿e byæ puste")
	private String title;
	
	@NotNull(message="To pole nie mo¿e byæ puste")
	@Pattern(regexp="[0-9]{3}[-]{1}[0-9]{1,5}[-]{1}[0-9]+[-]{1}[0-9]+[-]{1}[0-9]{1,6}",message="Z³y format")
	private String isbnNumber;

	@NotNull(message="To pole nie mo¿e byæ puste")
	@Pattern(regexp = "[0-9]{2}[/]{1}[0-9]{2}[/]{1}[0-9]{4}", message="Format daty DD/MM/RRRR")
	private String publicationDate;

	
	private int authorId;
	
	@NotNull(message="To pole nie mo¿e byæ puste")
	private String firstNameAuthor;

	@NotNull(message="To pole nie mo¿e byæ puste")
	private String lastNameAuthor;

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

	public String getPublicationDate() {
		return publicationDate;
	}

	public void setPublicationDate(String publicationDate) {
		this.publicationDate = publicationDate;
	}
	
	public int getAuthorId() {
		return authorId;
	}

	public void setAuthorId(int authorId) {
		this.authorId = authorId;
	}

	public String getFirstNameAuthor() {
		return firstNameAuthor;
	}

	public void setFirstNameAuthor(String firstNameAuthor) {
		this.firstNameAuthor = firstNameAuthor;
	}

	public String getLastNameAuthor() {
		return lastNameAuthor;
	}

	public void setLastNameAuthor(String lastNameAuthor) {
		this.lastNameAuthor = lastNameAuthor;
	}

	public BookToPage() {

	}

	@Override
	public String toString() {
		return "BookToPage [id=" + id + ", title=" + title + ", isbnNumber=" + isbnNumber + ", publicationDate="
				+ publicationDate + ", firstNameAuthor=" + firstNameAuthor + ", lastNameAuthor=" + lastNameAuthor + "]";
	}
	
	public LocalDate getDateFormat(String dateString) {
		
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		
			LocalDate date = LocalDate.parse(dateString, formatter);
			return date;
		
	}
	
	public String getDateString(LocalDate date) {
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		
		String dateString = date.format(formatter);
		return dateString;
		
	}
	
}
