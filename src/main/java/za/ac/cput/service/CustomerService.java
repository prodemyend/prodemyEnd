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
        Customer newCustomer = new Customer.Builder()
                .setId(customer.getId())
                .SetFirstName(customer.getFirstName())
                .SetLastName(customer.getLastName())
                .SetEmail(customer.getEmail())
                .SetPassword(customer.getPassword())
                .SetRole("USER")
                .build();

        return repository.save(newCustomer);
    }

    @Override
    public Customer read(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public Customer update(Customer customer) {
        if (repository.existsById(customer.getId())) {
            Customer updatedCustomer = new Customer.Builder()
                    .setId(customer.getId())
                    .SetFirstName(customer.getFirstName())
                    .SetLastName(customer.getLastName())
                    .SetEmail(customer.getEmail())
                    .SetPassword(customer.getPassword())
                    .SetRole("USER")
                    .build();

            return repository.save(updatedCustomer);
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
}
