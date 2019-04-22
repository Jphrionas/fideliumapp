package br.com.alluminox.app.util;

import org.springframework.security.core.Authentication;

import br.com.alluminox.app.data.model.User;

public class UserAction {
	
	public static boolean isUserLogged(String publicId, Authentication authentication) {		
		return ((User) authentication.getPrincipal()).getPublicId().equals(publicId);
	}
	
	public static boolean isUserAdmin(Authentication authentication) {		
		return ((User) authentication.getPrincipal()).getRole().getNome().equals("ROLE_ADMIN");
	}
}
