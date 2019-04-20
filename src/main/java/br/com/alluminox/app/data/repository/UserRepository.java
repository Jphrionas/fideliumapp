package br.com.alluminox.app.data.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import br.com.alluminox.app.data.model.User;

@Repository
public interface UserRepository extends  PagingAndSortingRepository<User, Long> {

	User findByPublicId(String publicId);
	User findByCpf(String cpf);
	
	@Modifying
	@Query("select u from User u where u.disabled=false")
	Iterable<User> findAll();
	
	@Query("select u from User u")
	List<User> findAllUsers();
	
	@Modifying
	@Query("update User u set u.disabled = true where id = ?1")
	void deleteById(Long id);
	
	@Query("select u from User u where u.disabled = false and (cpf = ?1 or email = ?2)")
	User findByCpfOrEmail(String cpf, String email);
	
	@Query("select u from User u join fetch u.role r where u.disabled = false and r.nome <> 'ADMIN'")
	Iterable<User> findAllComum();
	
	@Query("select u from User u join fetch u.role r where u.disabled = false and r.nome = 'ADMIN'")
	Iterable<User> findAllAdmin();
	
	@Query("select u from User u join fetch u.role r where u.email = ?1 and u.disabled = false")
	User findByEmail(String email);
}
