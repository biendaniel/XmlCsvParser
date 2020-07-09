package service;

import model.Contact;
import model.Customer;
import model.repository.ContactRepository;
import model.repository.CustomerRepository;
import utils.ContactUtils;
import utils.FileLineCounter;

import java.util.List;
import java.util.stream.Collectors;

public class CsvFileService implements FileService<List<String>>{

    private Customer extractCustomer(List<String> fileLineElement) {
        return Customer.builder()
                .name(fileLineElement.get(LineElement.NAME))
                .surname(fileLineElement.get(LineElement.SURNAME))
                .age(fileLineElement.get(LineElement.AGE).length() > 0 ? Integer.valueOf(fileLineElement.get(LineElement.AGE)) : null)
                .build();
    }

    private List<Contact> extractContacts(List<String> fileLineElement, Long customerId) {
        return fileLineElement.stream()
                .map(e -> ContactUtils.createContact(e, customerId))
                .collect(Collectors.toList());
    }

    @Override
    public void execute(List<String> fileLineElement, FileLineCounter counter) {
        try {
            Customer customer = extractCustomer(fileLineElement);
            CustomerRepository customerRepository = new CustomerRepository();
            customerRepository.save(customer);
            List<Contact> contacts = extractContacts(fileLineElement.subList(LineElement.CONTACTS, fileLineElement.size()), customer.getId());
            ContactRepository contactRepository = new ContactRepository();
            contacts.forEach(contactRepository::save);
            counter.incrementPositiveLoadLine();
        } catch (IllegalStateException e) {
            System.err.println("[" + counter.getCurrentLine() + "] Nie udało się dodać linii - " + e.getMessage());
        }
    }

    private interface LineElement {
        Integer NAME = 0;
        Integer SURNAME = 1;
        Integer AGE = 2;
        Integer CITY = 3;
        Integer CONTACTS = 4;
    }
}
