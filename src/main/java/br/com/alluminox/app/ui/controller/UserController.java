package br.com.alluminox.app.ui.controller;

import java.io.Serializable;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.alluminox.app.data.model.Genero;
import br.com.alluminox.app.data.model.User;
import br.com.alluminox.app.io.service.RoleService;
import br.com.alluminox.app.io.service.UserService;
import br.com.alluminox.app.io.transform.UserDTO;
import br.com.alluminox.app.io.transform.request.UserRequestModel;
import lombok.RequiredArgsConstructor;

@Controller
@SessionAttributes("user")
@RequestMapping("/user")
@RequiredArgsConstructor(onConstructor=@__(@Autowired))
public class UserController implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private final UserService userService;
	private final RoleService roleService;
	private final ModelMapper mapper;
	
	
	@GetMapping
	@Transactional(readOnly=true)
	public String listUsers(Model model) {
		model.addAttribute("title", "Usuários do Sistema");
		model.addAttribute("users", this.userService.findUsersComum());
		return "pages/user/list";
	}
	
	@PostMapping("/new")
	@Transactional
	public String newUser(@Valid UserRequestModel user, BindingResult result , Model model, RedirectAttributes scope, SessionStatus status) {
		if(result.hasErrors()) return formUser(user, model);
		
		this.userService.save(mapper.map(user, UserDTO.class));		
		scope.addFlashAttribute("successMessage", String.format("Usuário %s foi salvo com sucesso!" , user.getNome()));
		
		
		status.setComplete();
		return "redirect:/user";
	}
	
	@GetMapping("/new")
	@Transactional(readOnly=true)
	public String formUser(UserRequestModel user,Model model ) {
		model.addAttribute("user", user);
		model.addAttribute("generos",Genero.values());
		model.addAttribute("roles", roleService.findAll());
		model.addAttribute("title", "Novo Usuário");
		return "pages/user/form";
	}
	
	@GetMapping("/admin/profile/{publicId}")
	@Transactional(readOnly = true)
	public String viewProfileAdmin(@PathVariable("publicId") String publicId, Model model, Authentication authentication) {
		findUser(publicId, model);
		return "pages/user/profile";
	}
	
	@PostMapping("/admin/enable/{publicId}")
	@Transactional
	public String habilitar(@PathVariable("publicId") String publicId, Model model, RedirectAttributes flash, SessionStatus status) {
		User finded = this.userService.findUserDisabled(publicId);
		if(finded == null) {
			return "redirect:/home";
		}
		
		this.userService.enableUser(finded);
		flash.addFlashAttribute("successMessage", "User was enabled!");
		
		status.setComplete();
		return "redirect:/user";
		
	}
	
	@GetMapping("/profile/{publicId}")
	@Transactional(readOnly=true)
	public String viewProfile(@PathVariable("publicId") String publicId, Model model, Authentication authentication) {
		User user = (User) authentication.getPrincipal();		
		if(user.getPublicId().equals(publicId)) {
			findUser(publicId, model);
			return "pages/user/profile";
		}
		
		return "redirect:/home";
	}
	
	private Model findUser(String publicId, Model model) {
		model.addAttribute("title", "Your profile");
		model.addAttribute("user", this.userService.findByPublicId(publicId));
		return model;
	}
	
	
	@GetMapping("edit/{publicId}") 
	@Transactional(readOnly=true)
	public String edit(@PathVariable("publicId") String publicId, Model model) {
		User user = this.userService.findByPublicId(publicId);
		
		if(user != null) {
			return this.formUser(mapper.map(user, UserRequestModel.class), model);
		}
		
		return "redirect:/user/new";
			
	}
	
	@PostMapping("/admin/delete/{publicId}")
	@Transactional
	public String remover(@PathVariable("publicId") String publicId, Model model) {
		this.userService.remove(publicId);
		return "redirect:/user";
	}

	
}
