package biblioteka.przemek.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import biblioteka.przemek.model.LibraryUser;
import biblioteka.przemek.service.UserService;

@Controller
@RequestMapping("/userAdmin")
public class UserAdminController {

	@Autowired
	UserDetailsManager getUserDetailsManager;

	@Autowired
	UserService userService;

	@GetMapping("/goRegisterUser")
	public String showUserRegistrationForm(Model model) {

		model.addAttribute("newUser", new LibraryUser());

		return "registrationFormPage";
	}

	@PostMapping("/registerUser")
	public String registerUser(@Valid @ModelAttribute("newUser") LibraryUser libraryUser, BindingResult bindingResult,
			Model model) {

		if (bindingResult.hasErrors()) {
			
			return "registrationFormPage";

		} else {

			BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();

			if (getUserDetailsManager.userExists(libraryUser.getUsername())) {
				model.addAttribute("newUser", new LibraryUser());
				return "registrationFormPage";
			} else {

				String encodedPassword = "{bcrypt}" + bcrypt.encode(libraryUser.getPassword());

				List<GrantedAuthority> authorities = AuthorityUtils.createAuthorityList("ROLE_USER");

				User user = new User(libraryUser.getUsername(), encodedPassword, authorities);

				getUserDetailsManager.createUser(user);

				userService.addUser(libraryUser);

				return "redirect:/";
			}
		}
	}

	@RequestMapping(path = "/userList")
	public String getUserList(Model model) {

		List<LibraryUser> userList = userService.getUserList();

		model.addAttribute("userList", userList);

		return "userListPage";
	}

	@RequestMapping(path = "/userDetails")
	public String getUserDetail(@RequestParam("userId") int userId, Model model) {

		LibraryUser user = userService.findUserById(userId);
		model.addAttribute("userDetail", user);

		return "userDetailPage";
	}
	
	
	@InitBinder
	public void initBinder(WebDataBinder webDataBinder) {
		StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
		
		webDataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
		
	}
}
