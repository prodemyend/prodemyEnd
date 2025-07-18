package za.ac.cput.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.ac.cput.domain.Admin;

@Repository
public interface adminRepository extends JpaRepository<Admin, Long> {
    // I want to find an Admin by email, used for authentication nor validation
    Admin findByEmail(String email);

    // Method to find an Admin by both email and password, I use this method for login verification
    Admin findByEmailAndPassword(String email, String password);
}
