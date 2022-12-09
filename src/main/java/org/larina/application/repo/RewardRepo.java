package org.larina.application.repo;

import org.larina.application.entities.Reward;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RewardRepo extends JpaRepository<Reward,Long> {
    Reward findRewardByActivityId(Long id);
}
