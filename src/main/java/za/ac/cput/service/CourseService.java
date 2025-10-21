package za.ac.cput.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import za.ac.cput.domain.Course;
import za.ac.cput.repository.CourseRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CourseService implements ICourseService {

    private final CourseRepository repository;

    @Autowired
    public CourseService(CourseRepository repository) {
        this.repository = repository;
    }

    @Override
    public Course create(Course course) {
        try {
            return repository.save(course);
        } catch (Exception e) {
            throw new RuntimeException("Error creating course: " + e.getMessage(), e);
        }
    }

    @Override
    public Optional<Course> read(Long id) {
        try {
            return repository.findById(id);
        } catch (Exception e) {
            throw new RuntimeException("Error reading course: " + e.getMessage(), e);
        }
    }

    @Override
    public Course update(Course course) {
        try {
            if (course.getId() == null) {
                throw new IllegalArgumentException("Course ID cannot be null for update");
            }

            if (!repository.existsById(course.getId())) {
                return null; // Course doesn't exist
            }

            return repository.save(course);
        } catch (Exception e) {
            throw new RuntimeException("Error updating course: " + e.getMessage(), e);
        }
    }

    @Override
    public boolean delete(Long id) {
        try {
            if (!repository.existsById(id)) {
                throw new IllegalArgumentException("Course with ID " + id + " does not exist");
            }
            repository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Error deleting course: " + e.getMessage(), e);
        }
        return false;
    }

    @Override
    public List<Course> getAll() {
        try {
            return repository.findAll();
        } catch (Exception e) {
            throw new RuntimeException("Error fetching all courses: " + e.getMessage(), e);
        }
    }
}
