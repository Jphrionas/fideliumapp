package br.com.alluminox.app.ui.controller;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import br.com.alluminox.app.data.model.Reward;
import br.com.alluminox.app.data.model.User;
import br.com.alluminox.app.io.service.RewardService;
import br.com.alluminox.app.io.service.UserService;
import br.com.alluminox.app.io.transform.response.HomeReponse;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class HomeController implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private final RewardService rewardService;
	private final UserService userService;

	@GetMapping({"/", "/home"})
	public String home(Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = (User) auth.getPrincipal();
		
		model.addAttribute("title", "Bem vindo ao sistema " + user.getNome());
		
		if(user.getRole().getNome().equals("ROLE_ADMIN")) {
			model.addAttribute("homeModel", createHomeModel(user.getPublicId()));
		} 
		else {
			
			List<Reward> rewards = findRewardsList(user.getPublicId());
			model.addAttribute("totalOfPoints", this.totalOfPoints(rewards));
			model.addAttribute("rewards", rewards);
			
		}
		
		return "pages/home";
	}
	
	
	public List<Reward> findRewardsList(String publicId) {
		return rewardService.fidAll(publicId);
	}
	
	public HomeReponse createHomeModel(String publicId) {
		HomeReponse homeModel = new HomeReponse();		
		List<Reward> rewards = findRewardsList(publicId);
		List<User> users = userService.findAllUsers();
			
		users.forEach(user -> {
			homeModel.setTotalOfUsers(homeModel.getTotalOfUsers() + 1);
			if(user.getRole().getNome().equals("ROLE_ADMIN")) {					
				homeModel.setTotalOfAdmins(homeModel.getTotalOfAdmins() + 1);
			}
			
			if(!user.getDisabled()) {
				homeModel.setUsersActives(homeModel.getUsersActives() + 1);
			}
		});		

		
		homeModel.setTotalOfPoints(totalOfPoints(rewards));
		homeModel.setUsersDisabled(getUsersDisabled(users));
		homeModel.setRewardOfUser(rewards);
		
		return homeModel;
	}
		
	private Integer totalOfPoints(List<Reward> rewards) {
		Integer totalOfPoints = 0;
		for (Reward reward : rewards) {
			totalOfPoints += reward.getQuantidadePontos();
		}
		return totalOfPoints;
	}
	
	private List<User> getUsersDisabled(List<User> users) {
		return users.stream().filter(user -> user.getDisabled()).collect(Collectors.toList());
	}

}
