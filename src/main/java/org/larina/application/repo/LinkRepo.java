package org.larina.application.repo;

import org.larina.application.entities.Company;
import org.larina.application.entities.Link;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LinkRepo extends JpaRepository<Link,Long> {
}
