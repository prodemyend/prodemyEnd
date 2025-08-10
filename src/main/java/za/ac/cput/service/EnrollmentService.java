package za.ac.cput.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.cput.domain.Enrollment;
import za.ac.cput.domain.Customer;
import za.ac.cput.domain.Course;
import za.ac.cput.repository.EnrollmentRepository;
import za.ac.cput.repository.customerRepository;
import za.ac.cput.repository.CourseRepository;

import java.util.List;
import java.util.Optional;

@Service
public class EnrollmentService implements IEnrollmentService {

    private final EnrollmentRepository enrollmentRepository;
    private final customerRepository customerRepository;
    private final CourseRepository courseRepository;

    @Autowired
    public EnrollmentService(
            EnrollmentRepository enrollmentRepository,
            customerRepository customerRepository,
            CourseRepository courseRepository
    ) {
        this.enrollmentRepository = enrollmentRepository;
        this.customerRepository = customerRepository;
        this.courseRepository = courseRepository;
    }

    @Override
    public Enrollment create(Enrollment enrollment) {
        // Fetch customer from DB
        Customer customer = customerRepository.findByFirstName(enrollment.getCustomer().getFirstName())
                .orElseThrow(() -> new IllegalArgumentException("Customer not found: " + enrollment.getCustomer().getFirstName()));

        // Fetch course from DB
        Course course = courseRepository.findByTitle(enrollment.getCourse().getTitle())
                .orElseThrow(() -> new IllegalArgumentException("Course not found: " + enrollment.getCourse().getTitle()));

        // Check if already enrolled
        if (enrollmentRepository.existsByCustomerAndCourse(customer, course)) {
            throw new IllegalStateException("Customer is already enrolled in this course.");
        }

        // Set fetched entities
        enrollment.setCustomer(customer);
        enrollment.setCourse(course);
        enrollment.setStatus(Enrollment.Status.PENDING);

        return enrollmentRepository.save(enrollment);
    }

    @Override
    public Optional<Enrollment> read(Long id) {
        return enrollmentRepository.findById(id);
    }

    @Override
    public Enrollment update(Enrollment enrollment) {
        if (enrollment.getId() != null && enrollmentRepository.existsById(enrollment.getId())) {
            return enrollmentRepository.save(enrollment);
        }
        throw new IllegalArgumentException("Enrollment not found with id: " + enrollment.getId());
    }

    @Override
    public void delete(Long id) {
        enrollmentRepository.deleteById(id);
    }

    @Override
    public List<Enrollment> getAll() {
        return enrollmentRepository.findAll();
    }

    @Override
    public List<Enrollment> getEnrollmentsByCustomer(Customer customer) {
        return enrollmentRepository.findByCustomer(customer);
    }

    @Override
    public List<Enrollment> getEnrollmentsByCourse(Course course) {
        return enrollmentRepository.findByCourse(course);
    }

    @Override
    public List<Enrollment> getEnrollmentsByStatus(Enrollment.Status status) {
        return enrollmentRepository.findByStatus(status);
    }

    @Override
    public Enrollment approveEnrollment(Long id) {
        Enrollment enrollment = enrollmentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Enrollment not found with id: " + id));
        enrollment.setStatus(Enrollment.Status.APPROVED);
        return enrollmentRepository.save(enrollment);
    }

    @Override
    public Enrollment rejectEnrollment(Long id) {
        Enrollment enrollment = enrollmentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Enrollment not found with id: " + id));
        enrollment.setStatus(Enrollment.Status.REJECTED);
        return enrollmentRepository.save(enrollment);
    }
}
