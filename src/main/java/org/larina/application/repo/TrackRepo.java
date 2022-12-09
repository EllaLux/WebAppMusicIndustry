package org.larina.application.repo;

import org.larina.application.entities.Track;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrackRepo extends JpaRepository<Track,Long> {
    Track findTrackByActivityId(Long id);
}
