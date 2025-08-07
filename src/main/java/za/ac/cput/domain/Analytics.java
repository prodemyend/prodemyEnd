package za.ac.cput.domain;

import java.util.Objects;

public class Analytics {
    private final int totalUsers;
    private final int totalQuizzes;
    private final int totalAdmins;
    private final int totalContactSupportMessages;
    private final int totalCourses; //

    private Analytics(Builder builder) {
        this.totalUsers = builder.totalUsers;
        this.totalQuizzes = builder.totalQuizzes;
        this.totalAdmins = builder.totalAdmins;
        this.totalContactSupportMessages = builder.totalContactSupportMessages;
        this.totalCourses = builder.totalCourses;
    }

    public int getTotalUsers() {
        return totalUsers;
    }

    public int getTotalQuizzes() {
        return totalQuizzes;
    }

    public int getTotalAdmins() {
        return totalAdmins;
    }

    public int getTotalContactSupportMessages() {
        return totalContactSupportMessages;
    }

    public int getTotalCourses() {
        return totalCourses; //
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Analytics)) return false;
        Analytics that = (Analytics) o;
        return totalUsers == that.totalUsers &&
                totalQuizzes == that.totalQuizzes &&
                totalAdmins == that.totalAdmins &&
                totalContactSupportMessages == that.totalContactSupportMessages &&
                totalCourses == that.totalCourses;
    }

    @Override
    public int hashCode() {
        return Objects.hash(totalUsers, totalQuizzes, totalAdmins, totalContactSupportMessages, totalCourses);
    }

    @Override
    public String toString() {
        return "Analytics{" +
                "totalUsers=" + totalUsers +
                ", totalQuizzes=" + totalQuizzes +
                ", totalAdmins=" + totalAdmins +
                ", totalContactSupportMessages=" + totalContactSupportMessages +
                ", totalCourses=" + totalCourses +
                '}';
    }

    public static class Builder {
        private int totalUsers;
        private int totalQuizzes;
        private int totalAdmins;
        private int totalContactSupportMessages;
        private int totalCourses;

        public Builder setTotalUsers(int totalUsers) {
            this.totalUsers = totalUsers;
            return this;
        }

        public Builder setTotalQuizzes(int totalQuizzes) {
            this.totalQuizzes = totalQuizzes;
            return this;
        }

        public Builder setTotalAdmins(int totalAdmins) {
            this.totalAdmins = totalAdmins;
            return this;
        }

        public Builder setTotalContactSupportMessages(int totalContactSupportMessages) {
            this.totalContactSupportMessages = totalContactSupportMessages;
            return this;
        }

        public Builder setTotalCourses(int totalCourses) {
            this.totalCourses = totalCourses;
            return this;
        }

        public Analytics build() {
            return new Analytics(this);
        }
    }

    public static Builder builder() {
        return new Builder();
    }
}
