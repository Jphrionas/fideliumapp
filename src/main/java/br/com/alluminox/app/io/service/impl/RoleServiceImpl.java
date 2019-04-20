package br.com.alluminox.app.io.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.alluminox.app.data.model.Role;
import br.com.alluminox.app.data.repository.RoleRepository;
import br.com.alluminox.app.io.service.RoleService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor(onConstructor=@__(@Autowired))
public class RoleServiceImpl implements RoleService {
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private final RoleRepository roleRepository;

	@Override
	public Role findById(Long id) {
		return roleRepository.findById(id).get();
	}

	@Override
	public Role findByName(String name) {
		return roleRepository.findByNome(name);
	}

	@Override
	public Iterable<Role> findAll() {
		return roleRepository.findAll();
	}

}
