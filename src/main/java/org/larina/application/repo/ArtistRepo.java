package org.larina.application.repo;

import org.larina.application.entities.Artist;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface ArtistRepo extends JpaRepository<Artist,Long> {
    Artist findArtistByNicknameAndUserId(String nickname, Long userId);

    Artist findArtistByUserId(Long userId);

    Artist findArtistByNickname(String nickname);
}
