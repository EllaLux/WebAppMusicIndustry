package org.larina.application.repo;

import org.larina.application.entities.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepo extends JpaRepository<Movie,Long> {
    Movie findMovieByActivityId(Long id);
}
