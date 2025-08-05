package za.ac.cput.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import za.ac.cput.domain.Course;

public interface CourseRepository extends JpaRepository<Course,Long> {
    Course findCourseById(Long id);
}

