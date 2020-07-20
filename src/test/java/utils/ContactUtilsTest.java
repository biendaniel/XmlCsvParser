package utils;

import model.ContactType;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ContactUtilsTest {

    @Test
    void shouldAssignContactTypePhone() {
        assertEquals(ContactType.PHONE, ContactUtils.createContact("654765765", 1L).getType());
    }

    @Test
    void shouldAssignContactTypePhone_WithSpace() {
        assertEquals(ContactType.PHONE, ContactUtils.createContact("654 765 765", 1L).getType());
    }

    @Test
    void shouldAssignContactTypePhone_WithDash() {
        assertEquals(ContactType.PHONE, ContactUtils.createContact("654-765-765", 1L).getType());
    }

    @Test
    void shouldAssignContactTypePhone_WithPrefix() {
        assertEquals(ContactType.PHONE, ContactUtils.createContact("+48654-765-765", 1L).getType());
    }

    @Test
    void shouldAssignContactTypeEmail() {
        assertEquals(ContactType.EMAIL, ContactUtils.createContact("kowalski@gmail.com", 1L).getType());
    }

    @Test
    void shouldAssignContactTypeJabber() {
        assertEquals(ContactType.JABBER, ContactUtils.createContact("jbr", 1L).getType());
    }

    @Test
    void shouldAssignContactTypeUnknown() {
        assertEquals(ContactType.UNKNOWN, ContactUtils.createContact("1211212121", 1L).getType());
    }
}
