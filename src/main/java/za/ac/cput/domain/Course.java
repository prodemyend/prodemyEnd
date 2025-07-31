package za.ac.cput.domain;
import jakarta.persistence.*;
import java.util.Objects;


@Entity
@Table

public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    private String imageUrl; // Store file name or URL of the image


    protected Course () {}
    public Course(Builder builder) {
        this.id = builder.id;
        this.title = builder.title;
        this.description = builder.description;
        this.imageUrl = builder.imageUrl;

    }

    public Long getId() {
        return id;
    }

    public String gettitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Course course = (Course) o;
        return Objects.equals(id, course.id) && Objects.equals(title, course.title) && Objects.equals(description, course.description) && Objects.equals(imageUrl, course.imageUrl);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, description, imageUrl);
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                '}';
    }
    public static class Builder {
        private Long id;
        private String title;
        private String description;
        private String imageUrl;


        public Builder id(Long id) {
            this.id = id;
            return this;

        }
        public Builder title(String title) {
            this.title = title;
            return this;
        }
        public Builder description(String description) {
            this.description = description;
            return this;
        }
        public Builder imageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
            return this;
        }
        public Course.Builder copy(Course course) {
            this.id = course.id;
            this.title = course.title;
            this.description = course.description;
            this.imageUrl = course.imageUrl;
            return this;
        }
        public Course build() {
            return new Course(this);
        }

        }
}

