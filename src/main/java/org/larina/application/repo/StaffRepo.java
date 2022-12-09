package org.larina.application.repo;

import org.larina.application.entities.Artist;
import org.larina.application.entities.Staff;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StaffRepo extends JpaRepository<Staff,Long> {
    Staff findStaffByUserId(Long userId);
}
