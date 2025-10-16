package za.ac.cput.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.cput.domain.Customer;
import za.ac.cput.repository.customerRepository;

import java.util.List;

@Service
public class CustomerService implements ICustomerService {

    private final customerRepository repository;

    @Autowired
    public CustomerService(customerRepository repository) {
        this.repository = repository;
    }

    @Override
    public Customer create(Customer customer) {
        // Use the customer directly, no need to rebuild with Builder
        return repository.save(customer);
    }

    @Override
    public Customer read(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public Customer update(Customer customer) {
        if (repository.existsById(customer.getId())) {
            // Use the customer directly, no need to rebuild with Builder
            return repository.save(customer);
        } else {
            System.out.println("Customer with ID " + customer.getId() + " does not exist.");
            return null;
        }
    }

    @Override
    public void delete(long id) {
        repository.deleteById(id);
    }

    @Override
    public List<Customer> getAll() {
        return repository.findAll();
    }

    public String verify(Customer customer) {
        Customer foundCustomer = repository.findById(customer.getId()).orElse(null);

        if (foundCustomer != null && foundCustomer.getPassword().equals(customer.getPassword())) {
            return "success";
        } else {
            return "fail";
        }
    }

    public Customer findByEmail(String email) {
        // Fixed: Return the actual found customer instead of always null
        return repository.findByEmail(email);
    }
}