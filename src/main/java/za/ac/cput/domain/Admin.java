package za.ac.cput.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.Objects;

@Entity
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long adminId;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String confirmPassword;
    private String role = "ADMIN";

    protected Admin() {
    }

    public Admin(Builder builder) {
        this.adminId = builder.adminId;
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.email = builder.email;
        this.password = builder.password;
        this.confirmPassword = builder.confirmPassword;
        this.role = builder.role;
    }

    // ✅ Getter methods
    public long getAdminId() {
        return adminId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public String getRole() {
        return role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Admin admin = (Admin) o;
        return adminId == admin.adminId &&
                Objects.equals(firstName, admin.firstName) &&
                Objects.equals(lastName, admin.lastName) &&
                Objects.equals(email, admin.email) &&
                Objects.equals(password, admin.password) &&
                Objects.equals(confirmPassword, admin.confirmPassword) &&
                Objects.equals(role, admin.role);
    }

    @Override
    public int hashCode() {
        return Objects.hash(adminId, firstName, lastName, email, password, confirmPassword, role);
    }

    @Override
    public String toString() {
        return "Admin{" +
                "adminId=" + adminId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", confirmPassword='" + confirmPassword + '\'' +
                ", role='" + role + '\'' +
                '}';
    }

    // Builder class
    public static class Builder {
        private long adminId;
        private String firstName;
        private String lastName;
        private String email;
        private String password;
        private String confirmPassword;
        private String role;

        public Builder setAdminId(long adminId) {
            this.adminId = adminId;
            return this;
        }

        public Builder setFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public Builder setLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public Builder setEmail(String email) {
            this.email = email;
            return this;
        }

        public Builder setPassword(String password) {
            this.password = password;
            return this;
        }

        public Builder setConfirmPassword(String confirmPassword) {
            this.confirmPassword = confirmPassword;
            return this;
        }

        public Builder setRole(String role) {
            this.role = role;
            return this;
        }

        public Builder copy(Admin admin) {
            this.adminId = admin.adminId;
            this.firstName = admin.firstName;
            this.lastName = admin.lastName;
            this.email = admin.email;
            this.password = admin.password;
            this.confirmPassword = admin.confirmPassword;
            this.role = admin.role;
            return this;
        }

        public Admin build() {
            return new Admin(this);
        }
    }
}
