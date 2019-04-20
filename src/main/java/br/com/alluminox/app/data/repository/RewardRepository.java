package br.com.alluminox.app.data.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import br.com.alluminox.app.data.model.Reward;

@Repository
public interface RewardRepository extends PagingAndSortingRepository<Reward, Long>{

	Reward findByPublicId(String publicId);

	@Query("select r from Reward r join fetch r.user u where u.publicId = ?1 and r.disabled = false")
	List<Reward> findAllByUserId(String userPublicId);
}
