package za.ac.cput.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.ac.cput.domain.Customer;
import za.ac.cput.service.CustomerService;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
@CrossOrigin(origins = "http://localhost:8080")
public class customerController {

    private final CustomerService customerService;
    @Autowired

    public customerController(CustomerService customerService) {
        this.customerService = customerService;
    }




    @PostMapping("/register")
    public Customer register(@RequestBody Customer customer) {
        return customerService.create(customer);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody Customer customer) {
        try {
            String token = customerService.verify(customer);
            if ("fail".equals(token)) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid email or password");
            }
            return ResponseEntity.ok(token);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred");
        }
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
