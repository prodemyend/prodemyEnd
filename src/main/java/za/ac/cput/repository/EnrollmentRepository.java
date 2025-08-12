package za.ac.cput.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.ac.cput.domain.Enrollment;
import za.ac.cput.domain.Customer;
import za.ac.cput.domain.Course;

import java.util.List;

@Repository
public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {
    List<Enrollment> findByCustomer(Customer customer);
    List<Enrollment> findByCourse(Course course);
    List<Enrollment> findByStatus(Enrollment.Status status);
    boolean existsByCustomerAndCourse(Customer customer, Course course);
}
