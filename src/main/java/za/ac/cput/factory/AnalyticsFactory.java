package za.ac.cput.factory;

import org.springframework.stereotype.Component;
import za.ac.cput.domain.Analytics;
import za.ac.cput.repository.adminRepository;
import za.ac.cput.repository.contactSupportRepository;
import za.ac.cput.repository.customerRepository;
import za.ac.cput.repository.QuizRepository;
import za.ac.cput.repository.CourseRepository; // ✅ Make sure this exists

@Component
public class AnalyticsFactory {

    private final customerRepository customerRepository;
    private final QuizRepository quizRepository;
    private final adminRepository adminRepository;
    private final contactSupportRepository contactSupportRepository;
    private final CourseRepository courseRepository; // ✅ Injected here

    public AnalyticsFactory(customerRepository customerRepository,
                            QuizRepository quizRepository,
                            adminRepository adminRepository,
                            contactSupportRepository contactSupportRepository,
                            CourseRepository courseRepository) { // ✅ Added to constructor
        this.customerRepository = customerRepository;
        this.quizRepository = quizRepository;
        this.adminRepository = adminRepository;
        this.contactSupportRepository = contactSupportRepository;
        this.courseRepository = courseRepository;
    }

    public Analytics createAnalytics() {
        int totalUsers = (int) customerRepository.count();
        int totalQuizzes = (int) quizRepository.count();
        int totalAdmins = (int) adminRepository.count();
        int totalMessages = (int) contactSupportRepository.count();
        int totalCourses = (int) courseRepository.count(); // ✅ Added course count

        return Analytics.builder()
                .setTotalUsers(totalUsers)
                .setTotalQuizzes(totalQuizzes)
                .setTotalAdmins(totalAdmins)
                .setTotalContactSupportMessages(totalMessages)
                .setTotalCourses(totalCourses) // ✅ Added to builder
                .build();
    }
}
