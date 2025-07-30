package za.ac.cput.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.ac.cput.domain.Customer;
import za.ac.cput.factory.customerFactory;
import za.ac.cput.service.CustomerService;

import java.util.List;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/customers")
public class customerController {

    private final CustomerService customerService;
    @Autowired

    public customerController(CustomerService customerService) {
        this.customerService = customerService;
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

            Customer saved = customerService.create(newCustomer);
            return ResponseEntity.ok(saved);

        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


    @PostMapping("/login")
    private ResponseEntity<String> handleLogin(String token) {
        if ("fail".equals(token)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid email or password");
        }
        return ResponseEntity.ok(token);
    }


    @GetMapping("/all")
    public List<Customer> getAll() {
        return customerService.getAll();
    }

    @GetMapping("/read/{id}")
    public Customer read(@PathVariable Long id) {
        return customerService.read(id);
    }

    @PostMapping("/update")
    public Customer update(@RequestBody Customer customer) {
        return customerService.update(customer);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable long id) {
        customerService.delete(id);
    }

    @GetMapping("/ping")
    public String ping() {
        return "Customer backend is running!";
    }
}
