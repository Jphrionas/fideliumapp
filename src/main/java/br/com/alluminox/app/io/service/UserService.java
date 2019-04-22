package br.com.alluminox.app.io.service;

import java.io.Serializable;
import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import br.com.alluminox.app.data.model.User;
import br.com.alluminox.app.io.transform.UserDTO;

public interface UserService extends  UserDetailsService, Serializable {

	User save(UserDTO userDTO);
	User findByPublicId(String publicId);
	User findByCpf(String cpf);
	void remove(Long id);
	Iterable<User> findAll(); // Apenas ADMIN'S
	Iterable<User> findUsersComum();
	void remove(String publicId);
	User findByEmail(String username);
	List<User> findAllUsers();
	User findUserDisabled(String publicId);
	void enableUser(User user);
}
