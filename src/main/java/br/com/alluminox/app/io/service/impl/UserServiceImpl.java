package br.com.alluminox.app.io.service.impl;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.alluminox.app.data.model.Role;
import br.com.alluminox.app.data.model.User;
import br.com.alluminox.app.data.repository.UserRepository;
import br.com.alluminox.app.io.service.RoleService;
import br.com.alluminox.app.io.service.UserService;
import br.com.alluminox.app.io.transform.UserDTO;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor(onConstructor=@__(@Autowired))
public class UserServiceImpl implements UserService {
	private static final long serialVersionUID = 1L;
	
	private final UserRepository userRepository;
	private final RoleService roleService;
	private final ModelMapper mapper;
	private final BCryptPasswordEncoder passwordEncoder;
	

	public User findByCpfOrEmail(String cpf, String email) {
		return Optional.ofNullable(this.userRepository.findByCpfOrEmail(cpf, email)).orElse(null);
	}
	
	@Override
	public User save(UserDTO userDTO) {
		
		User userDB = findByCpfOrEmail(userDTO.getCpf(), userDTO.getEmail());
		Role role = roleService.findByName(userDTO.getRole().getNome());
		String password = this.encryptPass(userDTO.getPassword());
		if(userDB == null) {
			User userSave = mapper.map(userDTO, User.class);
			userSave.setRole(role);
			userSave.setPassword(password);						
			return userRepository.save(userSave);			
		}
		
		userDB.setRole(role);
		userDB.setNome(userDTO.getNome());
		userDB.setEmail(userDTO.getEmail());
		userDB.setPassword(password);
		userDB.setCelular(userDTO.getCelular());
		userDB.setCpf(userDB.getCpf());
		userDB.setEstado(userDB.getEstado());
		userDB.setDob(userDB.getDob());
		userDB.setGenero(userDB.getGenero());
		
		return userDB;
		
		
	}
	
	protected String encryptPass(String password) {
		return passwordEncoder.encode(password);
	}
	
	

	@Override
	public User findByPublicId(String publicId) {
		return Optional.ofNullable(this.userRepository.findByPublicId(publicId)).orElse(null);
	}
	
	@Override
	public User findByCpf(String cpf) {
		return Optional.ofNullable(this.userRepository.findByCpf(cpf)).orElse(null);
	}

	@Override
	public void remove(Long id) {
		userRepository.deleteById(id);
	}

	@Override
	public Iterable<User> findAll() {
		return userRepository.findAll();

	}
	
	@Override
	public List<User> findAllUsers() {
		return userRepository.findAllUsers();
	}

	@Override
	public Iterable<User> findUsersComum() {
		return userRepository.findAllComum();
	}

	@Override
	public void remove(String publicId) {
		User user = this.findByPublicId(publicId);
		if(user != null) {
			user.setDisabled(true);
		}
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		return Optional.ofNullable(findByEmail(email))
		.orElseThrow(() -> new UsernameNotFoundException("User not exists!"));
	}

	@Override
	public User findByEmail(String email) {
		return this.userRepository.findByEmail(email);
		
	}
}
