package biblioteka.przemek.controllers;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


import biblioteka.przemek.model.Book;
import biblioteka.przemek.service.BookService;

@Component
public class EmailScheduledClass {
	
	@Autowired
	BookService bookService;
	
	@Autowired
	JavaMailSender mailSender;
	
	
		@Scheduled(cron = "0 * 9 * * ?")
		public void doSchdule() {
					
			List<Book> borrowedBooksList = bookService.getBorrowedBooks();
			
			if(borrowedBooksList.isEmpty() == false) {
				
				for(Book tempBook : borrowedBooksList) {
					
					LocalDate dateOfBorrowing = tempBook.getBorrowingDate();
					
					LocalDate presentDay = LocalDate.now();
					
					long period = ChronoUnit.DAYS.between(dateOfBorrowing, presentDay);
					
					if(period >= 14) {
						
						sendMessage(tempBook);
							
					}
				}
			}
		}
		
		public void sendMessage(Book book) {
			
			SimpleMailMessage message = new SimpleMailMessage(); 
			
			message.setFrom("*********@gmail.com");
			message.setTo(book.getBorrowingUser().getEmail());
			message.setSubject("Return of the book : " + book.getTitle());
			message.setText("Proszê o zwrot ksi¹¿ki" + book.getTitle() +"która by³a wypo¿yczona dwa tygodnie temu");
			
			mailSender.send(message);
		}
	
}
