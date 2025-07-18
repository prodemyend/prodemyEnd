package za.ac.cput.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.ac.cput.domain.Customer;

@Repository
public interface customerRepository extends JpaRepository<Customer, Long> {
    static Customer findByEmail(String email) {
        return null;
    }
}
