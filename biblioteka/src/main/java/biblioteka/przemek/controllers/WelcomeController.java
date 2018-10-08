package biblioteka.przemek.controllers;

import java.util.LinkedHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import biblioteka.przemek.model.SearchedBook;

@Controller
public class WelcomeController {
	
	@Autowired
	LinkedHashMap<String, String> categoryList;
	
	@RequestMapping("/")
	public String goWelcomePage(Model model) {
		
		SearchedBook sBook = new SearchedBook();
		sBook.setCategoryList(categoryList);
		model.addAttribute("searchedBook", sBook);
		
		return "welcomePage";
	}
	
	@RequestMapping("/customLogin")
	public String loginPage() {
		
		return "loginPage";
	}
}
