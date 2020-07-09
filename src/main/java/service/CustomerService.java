package service;

import model.repository.CustomerRepository;
import model.repository.Repository;

public class CustomerService {
    CustomerRepository customerRepository = new CustomerRepository();

    public Iterable getAll() {
        return customerRepository.findAll();
    }

    public Iterable getCustomerContacts(long id) {
        return customerRepository.findContacts(id);
    }
}
