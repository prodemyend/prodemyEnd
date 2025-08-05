package za.ac.cput.domain;

import jakarta.persistence.*;
import java.util.Arrays;
import java.util.Objects;

@Entity
@Table
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;

    @Lob
    @Column(length = 100000)
    private byte[] image; // required like Van.image

    protected Course() {}

    public Course(Builder builder) {
        this.id = builder.id;
        this.title = builder.title;
        this.description = builder.description;
        this.image = builder.image;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public byte[] getImage() {
        return image;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Course course = (Course) o;
        return Objects.equals(id, course.id)
                && Objects.equals(title, course.title)
                && Objects.equals(description, course.description)
                && Arrays.equals(image, course.image);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(id, title, description);
        result = 31 * result + Arrays.hashCode(image);
        return result;
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", image=" + Arrays.toString(image) +
                '}';
    }

    public static class Builder {
        private Long id;
        private String title;
        private String description;
        private byte[] image;

        public Builder setId(Long id) {
            this.id = id;
            return this;
        }

        public Builder setTitle(String title) {
            this.title = title;
            return this;
        }

        public Builder setDescription(String description) {
            this.description = description;
            return this;
        }

        public Builder setImage(byte[] image) {
            this.image = image;
            return this;
        }

        public Builder copy(Course course) {
            this.id = course.id;
            this.title = course.title;
            this.description = course.description;
            this.image = course.image;
            return this;
        }

        public Course build() {
            return new Course(this);
        }
    }
}
