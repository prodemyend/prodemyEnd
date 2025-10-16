package za.ac.cput.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.ac.cput.domain.Customer;
import za.ac.cput.factory.customerFactory;
import za.ac.cput.repository.customerRepository;
import za.ac.cput.service.CustomerService;

import java.util.List;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/customers")
public class customerController {

    private final CustomerService customerService;
    private final customerRepository customerRepository;

    @Autowired
    public customerController(CustomerService customerService, customerRepository customerRepository) {
        this.customerService = customerService;
        this.customerRepository = customerRepository;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody Customer customerInput) {
        try {
            Customer newCustomer = customerFactory.buildCustomer(
                    customerInput.getFirstName(),
                    customerInput.getLastName(),
                    customerInput.getEmail(),
                    customerInput.getPassword()
            );

            if (newCustomer == null) {
                return ResponseEntity.badRequest().body("Invalid input fields");
            }

            // Check if email already exists
            Customer existingCustomer = customerRepository.findByEmail(customerInput.getEmail());
            if (existingCustomer != null) {
                return ResponseEntity.badRequest().body("Email already exists");
            }

            Customer saved = customerService.create(newCustomer);
            return ResponseEntity.ok(saved);

        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> handleLogin(@RequestBody Customer loginInput) {
        try {
            Customer customer = customerService.findByEmail(loginInput.getEmail());

            if (customer == null || !customer.getPassword().equals(loginInput.getPassword())) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                        .body("Invalid email or password");
            }

            return ResponseEntity.ok(customer);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Login error: " + e.getMessage());
        }
    }

    @GetMapping("/all")
    public List<Customer> getAll() {
        return customerService.getAll();
    }

    @GetMapping("/read/{id}")
    public ResponseEntity<Customer> read(@PathVariable Long id) {
        Customer customer = customerService.read(id);
        if (customer == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(customer);
    }

    @PutMapping("/update")  // Changed from @PostMapping to @PutMapping
    public ResponseEntity<?> update(@RequestBody Customer customer) {
        try {
            // Check if customer exists
            Customer existingCustomer = customerService.read(customer.getId());
            if (existingCustomer == null) {
                return ResponseEntity.notFound().build();
            }

            // Check if email is being changed and if it already exists for another user
            if (!existingCustomer.getEmail().equals(customer.getEmail())) {
                Customer customerWithEmail = customerService.findByEmail(customer.getEmail());
                if (customerWithEmail != null && !customerWithEmail.getId().equals(customer.getId())) {
                    return ResponseEntity.badRequest().body("Email already exists");
                }
            }

            Customer updated = customerService.update(customer);
            if (updated == null) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(updated);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Update error: " + e.getMessage());
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {  // Changed from long to Long
        try {
            Customer customer = customerService.read(id);
            if (customer == null) {
                return ResponseEntity.notFound().build();
            }
            customerService.delete(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Delete error: " + e.getMessage());
        }
    }

    @GetMapping("/ping")
    public String ping() {
        return "Customer backend is running!";
    }
}