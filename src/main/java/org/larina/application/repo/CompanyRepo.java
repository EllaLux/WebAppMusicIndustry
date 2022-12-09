package org.larina.application.repo;

import org.larina.application.entities.Company;
import org.larina.application.entities.Link;
import org.larina.application.entities.Staff;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface CompanyRepo extends JpaRepository<Company,Long> {
    List<Company> findByName(String name);

    Company findCompanyByNameAndLinkId(String name, Long linkId);

    Company findByLinkId(Long linkId);

    Company findCompanyByName(String name);

    Company findCompanyById(Long companyId);
}
