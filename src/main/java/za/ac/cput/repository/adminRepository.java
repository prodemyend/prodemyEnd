package za.ac.cput.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.ac.cput.domain.Admin;

@Repository
public interface  adminRepository extends JpaRepository<Admin, Long> {
     Admin findByEmail(String email) ;
    }

