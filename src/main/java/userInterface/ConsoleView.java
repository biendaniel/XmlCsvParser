package userInterface;

import com.mysql.cj.exceptions.NumberOutOfRange;
import model.Contact;
import model.Customer;

import java.util.Scanner;

public class ConsoleView implements View {
    private Scanner input;
    private final static String DASH = " - ";

    public ConsoleView() {
        this.input = new Scanner(System.in);
    }

    @Override
    public void printCustomer(Customer customer) {
        display("ID: " + customer.getId());
        display("Imię: " + customer.getName());
        display("Nazwisko: " + customer.getSurname());
        display("Wiek: " + customer.getAge());
        display("Kontakty: ");
    }

    @Override
    public void printCustomerContacts(Iterable<Contact> contacts) {
        contacts.forEach(c -> {
            display("--  " + c.getValue() + " - " +  c.getType().getName());
        });
    }

    @Override
    public String readString(String label) {
        display(label);
        return input.nextLine();
    }

    @Override
    public int readInt(String label) {
        display(label);
        int value = -1;
        String line = input.nextLine();
            try {
                value = Integer.valueOf(line);
                return value;
            } catch (NumberFormatException | NumberOutOfRange e) {
                return value;
        }
    }
}
