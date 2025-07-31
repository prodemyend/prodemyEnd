package za.ac.cput.factory;
import za.ac.cput.domain.Course;
import za.ac.cput.util.Helper;

public class CourseFactory {
    public static Course create(String title, String description, String imageUrl) {
        if (Helper.isNullorEmpty(title) || Helper.isNullorEmpty(description)) {
            throw new IllegalArgumentException("Title or description cannot be null or empty");
        }

        return new Course.Builder()
                .title(title)
                .description(description)
                .imageUrl(imageUrl)
                .build();
    }
}
