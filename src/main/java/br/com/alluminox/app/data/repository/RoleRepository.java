package br.com.alluminox.app.data.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import br.com.alluminox.app.data.model.Role;

@Repository
public interface RoleRepository extends PagingAndSortingRepository<Role, Long>{

	@Query("select r from Role r where nome = ?1 and r.disabled = false ")
	Role findByNome(String name);
}
