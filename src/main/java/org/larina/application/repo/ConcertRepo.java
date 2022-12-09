package org.larina.application.repo;

import org.larina.application.entities.Concert;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConcertRepo extends JpaRepository<Concert,Long> {
    Concert findConcertByActivityId(Long id);
}
