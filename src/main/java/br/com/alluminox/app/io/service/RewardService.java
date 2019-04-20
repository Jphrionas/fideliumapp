package br.com.alluminox.app.io.service;

import java.io.Serializable;
import java.util.List;

import br.com.alluminox.app.data.model.Reward;

public interface RewardService extends Serializable {

	Reward save(Reward reward);
	Reward findByPublicId(String rewardPublicId);
	void delete(String rewardPublicId);
	List<Reward> fidAll(String userPublicId);
}
