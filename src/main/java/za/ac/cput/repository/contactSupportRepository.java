package za.ac.cput.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.ac.cput.domain.ContactSupport;

import java.util.List;

@Repository
public interface contactSupportRepository extends JpaRepository<ContactSupport, Long> {
List<ContactSupport> findByEmail(String email);
}

