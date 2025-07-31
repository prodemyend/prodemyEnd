package za.ac.cput.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import za.ac.cput.domain.Course;

public interface CourseReposistory  extends JpaRepository<Course,Long> {
}

