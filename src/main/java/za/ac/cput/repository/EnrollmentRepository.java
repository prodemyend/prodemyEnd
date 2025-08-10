package za.ac.cput.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.ac.cput.domain.Enrollment;
import za.ac.cput.domain.Customer;
import za.ac.cput.domain.Course;

import java.util.List;

@Repository
public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {

    // Find all enrollments for a specific customer
    List<Enrollment> findByCustomer(Customer customer);

    // Find all enrollments for a specific course
    List<Enrollment> findByCourse(Course course);

    // Find all enrollments by status (PENDING, APPROVED, REJECTED)
    List<Enrollment> findByStatus(Enrollment.Status status);

    // Check if a customer is already enrolled in a specific course
    boolean existsByCustomerAndCourse(Customer customer, Course course);
}
