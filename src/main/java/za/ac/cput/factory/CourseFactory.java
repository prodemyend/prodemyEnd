package za.ac.cput.factory;

import za.ac.cput.domain.Course;
import za.ac.cput.util.Helper;

public class CourseFactory {

    public static Course buildCourse(String title, String description, byte[] image, String contentType) {
        if (Helper.isNullorEmpty(title)
                || Helper.isNullorEmpty(description)
                || image == null
                || image.length == 0) {
            return null;
        }

        return new Course.Builder()
                .setTitle(title)
                .setDescription(description)
                .setImage(image)
                .setContentType(contentType) // ADD THIS
                .build();
    }
}