package za.ac.cput.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.Objects;

@Entity
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String contactNumber;
    private String role  = "USER";


    public Customer() {}

    public Customer(Builder builder) {
        this.id = builder.id;
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.email = builder.email;
        this.password = builder.password;
        this.contactNumber = builder.contactNumber;
        this.role = builder.role;

    }

    //getters
    public Long getId() {
        return id;
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

    public String getContactNumber() {
        return contactNumber;
    }

    public String getRole() {return role;}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return Objects.equals(id, customer.id) &&
                Objects.equals(firstName, customer.firstName) &&
                Objects.equals(lastName, customer.lastName) &&
                Objects.equals(email, customer.email) &&
                Objects.equals(password, customer.password) &&
                Objects.equals(contactNumber, customer.contactNumber) &&
                Objects.equals(role, customer.role);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, email, password, contactNumber, role);
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", contactNumber='" + contactNumber + '\'' +
                ", role='" + role + '\'' +
                '}';
    }

    public static class Builder {
        private Long id;
        private String firstName;
        private String lastName;
        private String email;
        private String password;
        private String contactNumber;
        private String role;


        public Builder setId(Long id) {
            this.id = id;
            return this;
        }

        public Builder SetFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }
        public Builder SetLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }
        public Builder SetEmail(String email) {
            this.email = email;
            return this;
        }
        public Builder SetPassword(String password) {
            this.password = password;
            return this;
        }
        public Builder SetContactNumber(String contactNumber) {
            this.contactNumber = contactNumber;
            return this;
        }
        public Builder SetRole(String role) {
            this.role = role;
            return this;
        }

        public Builder copy(Customer customer) {
            this.id = customer.id;
            this.firstName = customer.firstName;
            this.lastName = customer.lastName;
            this.email = customer.email;
            this.password = customer.password;
            this.contactNumber = customer.contactNumber;
            this.role = customer.role;
            return this;
        }

        public Customer build() {
            return new Customer(this);
        }
    }
}

