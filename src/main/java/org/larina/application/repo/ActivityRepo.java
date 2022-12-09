package org.larina.application.repo;

import org.larina.application.entities.Activity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ActivityRepo extends JpaRepository<Activity,Long> {
    List<Activity> findByArtistId(Long id);
}
