package br.com.alluminox.app.io.service;

import java.io.Serializable;

import br.com.alluminox.app.data.model.Role;

public interface RoleService extends Serializable {

	Role findById(Long id);
	Role findByName(String name);
	Iterable<Role> findAll();
}
