package za.ac.cput.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.ac.cput.domain.Customer;

import java.util.Optional;

@Repository
public interface customerRepository extends JpaRepository<Customer, Long> {
    Customer findByEmail(String email);
    Optional<Customer> findByFirstName(String firstName);
    Optional<Customer> findByFirstNameAndLastName(String firstName, String lastName);
}
