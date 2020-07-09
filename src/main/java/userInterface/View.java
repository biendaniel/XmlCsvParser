package userInterface;

import model.Contact;
import model.Customer;

public interface View {
    void printCustomer(Customer customer);
    void printCustomerContacts(Iterable<Contact> contacts);
    default void display(String message) {
        System.out.println(message);
    }
    default void error(String message) {
        System.err.println(message);
    }
    String readString(String label);
    int readInt(String label);
}
