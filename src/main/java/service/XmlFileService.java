package service;

import model.Contact;
import model.Customer;
import model.repository.ContactRepository;
import model.repository.CustomerRepository;
import org.apache.commons.lang3.StringUtils;
import utils.ContactUtils;
import utils.FileLineCounter;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class XmlFileService implements FileService<Scanner> {

    public boolean isOpenTag(String line, String tag) {
        line = line.strip();
        return (line.equals(createOpenTag(tag)));
    }

    private boolean isCloseTag(String line, String tag) {
        line = line.strip();
        return (line.equals(createCloseTag(tag)));
    }

    public interface Tags {
        String PERSONS = "persons";
        String PERSON = "person";
        String NAME = "name";
        String SURNAME = "surname";
        String AGE = "age";
        String CITY = "city";
        String CONTACTS = "contacts";
        String PHONE = "phone";
        String EMAIL = "email";
        String JABBER = "jabber";
    }

    private String createCloseTag(String tagValue) {
        return "</" +
                tagValue +
                ">";
    }

    private String createOpenTag(String tagValue) {
        return "<" +
                tagValue +
                ">";
    }

    private String getValueBetweenTags(String line, String tagValue) {
        return StringUtils.substringBetween(line, createOpenTag(tagValue), createCloseTag(tagValue));
    }

    private String getValueBetweenUnknownTag(String line) {
        return StringUtils.substringBetween(line, ">", "</");
    }

    private List<Contact> parseContacts(Scanner scanner, Long customerId) {
        String line;
        List<Contact> contacts = new LinkedList<>();
        while (scanner.hasNextLine() && !isCloseTag(line = scanner.nextLine(), Tags.CONTACTS)) {
            if (getValueBetweenTags(line, Tags.EMAIL) != null) {
                contacts.add(ContactUtils.createContact(getValueBetweenTags(line, Tags.EMAIL), customerId));
            }
            else if (getValueBetweenTags(line, Tags.PHONE) != null) {
                contacts.add(ContactUtils.createContact(getValueBetweenTags(line, Tags.PHONE), customerId));
            }
            else  if (getValueBetweenTags(line, Tags.JABBER) != null) {
                contacts.add(ContactUtils.createContact(getValueBetweenTags(line, Tags.JABBER), customerId));
            }
            else  if(getValueBetweenUnknownTag(line)!= null) {
                contacts.add(ContactUtils.createContact(getValueBetweenUnknownTag(line), customerId));
            }
        }
        return contacts;
    }

    @Override
    public void execute(Scanner scanner, FileLineCounter counter) {
        String line;
        Customer customer = new Customer();
        List<Contact> contacts = new LinkedList<>();
        CustomerRepository customerRepository = new CustomerRepository();
        ContactRepository contactRepository = new ContactRepository();
        while (scanner.hasNextLine() && !isCloseTag(line = scanner.nextLine(), Tags.PERSON)) {
            if (getValueBetweenTags(line, Tags.NAME) != null) {
                customer.setName(getValueBetweenTags(line, Tags.NAME));
            }
            else if (getValueBetweenTags(line, Tags.SURNAME) != null) {
                customer.setSurname(getValueBetweenTags(line, Tags.SURNAME));
            }
            else if (getValueBetweenTags(line, Tags.AGE) != null) {
                customer.setAge(Integer.valueOf(getValueBetweenTags(line, Tags.AGE)));
            }
            else if(isOpenTag(line, Tags.CONTACTS)) {
                customerRepository.save(customer);
               contacts = parseContacts(scanner, customer.getId());
            }
        }
        contacts.forEach(contactRepository::save);
    }
}
