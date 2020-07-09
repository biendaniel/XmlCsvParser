package utils;

import model.Contact;
import model.ContactType;

public class ContactUtils {

    private ContactUtils() {
    }

    public static Contact createContact(String contactValue, Long customerId) {
        return new Contact(customerId, getContactType(contactValue), contactValue);
    }

    private static ContactType getContactType(String contact) {
        contact = contact.strip();
        contact = contact.replace(" ", "");
        if (contact.matches("^(\\+48)?\\d{3}-?\\d{3}-?\\d{3}$")) {
            return ContactType.PHONE;
        } else if (contact.matches("^([\\w\\.\\-]+)@([\\w\\-]+)((\\.(\\w){2,4}))$")) {
            return ContactType.EMAIL;
        } else if (contact.matches("^[a-zA-Z]+(\\w){2,}$")) {
            return ContactType.JABBER;
        }
            return ContactType.UNKNOWN;
    }
}
