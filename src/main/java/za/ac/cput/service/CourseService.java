package za.ac.cput.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.cput.domain.Course;
import za.ac.cput.repository.CourseRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CourseService implements ICourseService {

    private final CourseRepository repository;

    @Autowired
    public CourseService(CourseRepository repository) {
        this.repository = repository;
    }

    @Override
    public Course create(Course course) {
        return repository.save(course);
    }

    @Override
    public Optional<Course> read(Long id) {
        return repository.findById(id);
    }

    @Override
    public Course update(Course course) {
        if (course.getId() != null && repository.existsById(course.getId())) {
            return repository.save(course);
        }
        return null;
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public List<Course> getAll() {
        return repository.findAll();
    }
}
