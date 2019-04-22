package br.com.alluminox.app.ui.controller;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import br.com.alluminox.app.data.model.Reward;
import br.com.alluminox.app.data.model.User;
import br.com.alluminox.app.io.service.RewardService;
import br.com.alluminox.app.io.service.UserService;
import br.com.alluminox.app.util.UserAction;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/reward/{publicId}")
@SessionAttributes("reward")
@RequiredArgsConstructor(onConstructor=@__(@Autowired))
public class RewardController implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private final UserService userService;
	private final RewardService rewardService;
	
	@GetMapping("new")
	@Transactional(readOnly=true)
	public String formUser(@PathVariable("publicId") String publicId, Reward reward, Model model,  SessionStatus status, Authentication authentication) {
		
		User authenticated = (User) authentication.getPrincipal();
		String roleName = authenticated.getRole().getNome();
		if(publicId.equals(authenticated.getPublicId())
				|| roleName.equals("ROLE_ADMIN")) {
			
			User user = userService.findByPublicId(publicId);
			if(user == null) { return "redirect:/user"; }
			reward.setUser(user);
			status.setComplete();
			model.addAttribute("title", "Create Reward Type");
			model.addAttribute("reward", reward);
			
			return "pages/reward/form" ;
			
		}
	
		return "redirect:/reward/"+publicId + "/list";
	}
	
	@GetMapping("list")
	@Transactional(readOnly=true)
	public String rewards(@PathVariable("publicId") String publicId, Model model) {
		User user = userService.findByPublicId(publicId);
		if(user == null) {
			return "redirect:/user";
		}

		model.addAttribute("title", "Listando Rewards");
		model.addAttribute("rewards", this.rewardService.fidAll(publicId));
		model.addAttribute("user", user);
		return "pages/reward/list";
	}

	
	@PostMapping("new")
	@Transactional
	public String createReward(@PathVariable("publicId") String publicId, Reward reward, SessionStatus status,
			Authentication authentication) { 
		
		if(UserAction.isUserLogged(publicId, authentication) || 
				UserAction.isUserAdmin(authentication)) {
			
					User user = userService.findByPublicId(publicId);
			if(user == null) {
				return "redirect:/user";
			}
			
			reward.setUser(user);
			rewardService.save(reward);
		}
		
		status.setComplete();
		return "redirect:/reward/" + publicId  + "/list" ;
	}
	
	@GetMapping("edit")
	@Transactional
	public String edit() {
		
		return "redirect:/home";
	}
	
	@GetMapping("remove")
	@Transactional
	public String remover(@PathVariable("publicId") String publicId)  {// publicId do Reward
		this.rewardService.delete(publicId);
		return "redirect:/reward/" + publicId + "/list"; 
	}
	

}
