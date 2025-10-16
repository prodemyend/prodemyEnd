package za.ac.cput.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import za.ac.cput.domain.Course;
import za.ac.cput.factory.CourseFactory;
import za.ac.cput.service.CourseService;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/courses")
public class CourseController {

    private final CourseService courseService;

    @Autowired
    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @PostMapping(value = "/create", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> create(
            @RequestParam("title") String title,
            @RequestParam("description") String description,
            @RequestParam("image") MultipartFile imageFile
    ) {
        try {
            if (imageFile.isEmpty()) {
                return ResponseEntity.badRequest().body("Image file is required.");
            }

            byte[] imageBytes = imageFile.getBytes();
            String contentType = imageFile.getContentType();

            // Create course using builder directly since factory might be causing issues
            Course course = new Course.Builder()
                    .setTitle(title)
                    .setDescription(description)
                    .setImage(imageBytes)
                    .setContentType(contentType)
                    .build();

            Course saved = courseService.create(course);
            return ResponseEntity.ok(saved);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to process image file.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error creating course: " + e.getMessage());
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<Course>> getAll() {
        try {
            List<Course> courses = courseService.getAll();
            return ResponseEntity.ok(courses);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> get(@PathVariable Long id) {
        try {
            Optional<Course> course = courseService.read(id);
            if (course.isEmpty()) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(course.get());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error fetching course: " + e.getMessage());
        }
    }

    @PutMapping(value = "/update", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> update(
            @RequestParam("id") Long id,
            @RequestParam("title") String title,
            @RequestParam("description") String description,
            @RequestParam(value = "image", required = false) MultipartFile imageFile
    ) {
        try {
            // Check if course exists
            Optional<Course> existingCourseOpt = courseService.read(id);
            if (existingCourseOpt.isEmpty()) {
                return ResponseEntity.badRequest().body("Course with ID " + id + " does not exist.");
            }

            Course existingCourse = existingCourseOpt.get();
            byte[] imageBytes = existingCourse.getImage();
            String contentType = existingCourse.getContentType();

            // If new file provided, use it
            if (imageFile != null && !imageFile.isEmpty()) {
                imageBytes = imageFile.getBytes();
                contentType = imageFile.getContentType();
            }

            // Build updated course
            Course updatedCourse = new Course.Builder()
                    .setId(id)
                    .setTitle(title)
                    .setDescription(description)
                    .setImage(imageBytes)
                    .setContentType(contentType)
                    .build();

            Course saved = courseService.update(updatedCourse);
            if (saved == null) {
                return ResponseEntity.badRequest().body("Failed to update course.");
            }

            return ResponseEntity.ok(saved);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to process image file.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error updating course: " + e.getMessage());
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        try {
            // Check if course exists first
            Optional<Course> course = courseService.read(id);
            if (course.isEmpty()) {
                return ResponseEntity.notFound().build();
            }

            courseService.delete(id);
            return ResponseEntity.ok().body("Course deleted successfully");
        } catch (Exception e) {
            // Check if it's a foreign key constraint violation
            if (e.getMessage() != null && e.getMessage().contains("constraint")) {
                return ResponseEntity.status(HttpStatus.CONFLICT)
                        .body("Cannot delete course. It may have existing enrollments. Delete enrollments first.");
            }
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error deleting course: " + e.getMessage());
        }
    }

    @GetMapping("/ping")
    public String ping() {
        return "Course backend running";
    }

    @GetMapping("/media/{id}")
    public ResponseEntity<byte[]> getFile(@PathVariable Long id) {
        try {
            Optional<Course> courseOpt = courseService.read(id);
            if (courseOpt.isEmpty()) {
                return ResponseEntity.notFound().build();
            }

            Course course = courseOpt.get();
            byte[] file = course.getImage();

            HttpHeaders headers = new HttpHeaders();

            // Use stored content type
            if (course.getContentType() != null && !course.getContentType().isEmpty()) {
                headers.setContentType(MediaType.parseMediaType(course.getContentType()));
            } else {
                // Fallback detection
                headers.setContentType(detectMediaType(file));
            }

            return new ResponseEntity<>(file, headers, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    private MediaType detectMediaType(byte[] file) {
        if (file == null || file.length < 4) {
            return MediaType.APPLICATION_OCTET_STREAM;
        }

        // JPEG
        if (file[0] == (byte)0xFF && file[1] == (byte)0xD8) {
            return MediaType.IMAGE_JPEG;
        }
        // PNG
        if (file[0] == (byte)0x89 && file[1] == (byte)0x50 &&
                file[2] == (byte)0x4E && file[3] == (byte)0x47) {
            return MediaType.IMAGE_PNG;
        }
        // GIF
        if (file[0] == (byte)0x47 && file[1] == (byte)0x49 && file[2] == (byte)0x46) {
            return MediaType.IMAGE_GIF;
        }
        // PDF
        if (file[0] == (byte)0x25 && file[1] == (byte)0x50 &&
                file[2] == (byte)0x44 && file[3] == (byte)0x46) {
            return MediaType.APPLICATION_PDF;
        }

        return MediaType.APPLICATION_OCTET_STREAM;
    }
}