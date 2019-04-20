package br.com.alluminox.app.io.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.alluminox.app.data.model.Reward;
import br.com.alluminox.app.data.repository.RewardRepository;
import br.com.alluminox.app.io.service.RewardService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor(onConstructor=@__(@Autowired))
public class RewardServiceImpl implements RewardService{
	private static final long serialVersionUID = 1L;
	
	private final RewardRepository rewardRepository;

	@Override
	public Reward save(Reward reward) {		
		return rewardRepository.save(reward);
	}

	@Override
	public Reward findByPublicId(String rewardPublicId) {
		return Optional
				.ofNullable(rewardRepository.findByPublicId(rewardPublicId))
				.orElse(null);
	}

	@Override
	public void delete(String rewardPublicId) {
		findByPublicId(rewardPublicId).setDisabled(true);
	}

	@Override
	public List<Reward> fidAll(String userPublicId) { 
		return this.rewardRepository.findAllByUserId(userPublicId);
	}

}
