package za.ac.cput.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import za.ac.cput.domain.Course;

import java.util.Optional;

public interface CourseRepository extends JpaRepository<Course,Long> {
    Course findCourseById(Long id);

    Optional<Course> findByTitle(String title);
}

