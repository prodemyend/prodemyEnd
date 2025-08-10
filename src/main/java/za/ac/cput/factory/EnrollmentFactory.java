package za.ac.cput.factory;

import za.ac.cput.domain.Customer;
import za.ac.cput.domain.Course;
import za.ac.cput.domain.Enrollment;

public class EnrollmentFactory {

    public static Enrollment createEnrollment(Customer customer, Course course) {
        if (customer == null || course == null) {
            throw new IllegalArgumentException("Customer and Course must not be null");
        }

        return new Enrollment(customer, course, Enrollment.Status.PENDING);
    }
}
