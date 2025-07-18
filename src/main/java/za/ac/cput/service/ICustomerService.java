package za.ac.cput.service;

import za.ac.cput.domain.Customer;

import java.util.List;

public interface ICustomerService {
    Customer create(Customer customer);
    Customer read(Long id);
    Customer update(Customer customer);
    void delete(long id);
    List<Customer> getAll();
    String verify(Customer customer);
}
