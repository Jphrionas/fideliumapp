package br.com.alluminox.app.ui.controller;

import java.io.Serializable;
import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/login")
public class LoginController implements Serializable {
	private static final long serialVersionUID = 1L;

	@GetMapping
	public String form(
			@RequestParam(name="error", required = false) String error,
			@RequestParam(name="logout", required = false) String logout,
			Model model, Principal principal, RedirectAttributes flash) {
		
		// Siginifica que ele está logado
		if(principal != null) {
			flash.addFlashAttribute("infoMessage", "You session is stating");
			return "redirect:/";
		}
		
		if(logout != null) {
			model.addAttribute("infoMessage", "Enter with account");
		}
		
		if(error != null) {
			model.addAttribute("errorMessage", "Username/Password don't exists!, Please try");
		}
		
		return "pages/security/login";
	}
}
