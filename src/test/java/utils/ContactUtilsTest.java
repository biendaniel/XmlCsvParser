package utils;

import model.ContactType;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ContactUtilsTest {

    long customerId = 1L;

    @Test
    void shouldAssignContactTypePhone() {
        //given
        String contactValue = "654765765";
        //when
        ContactType contactType = ContactUtils.createContact(contactValue, customerId).getType();
        //then
        assertEquals(ContactType.PHONE, contactType);
    }

    @Test
    void shouldAssignContactTypePhone_WithSpace() {
        //given
        String contactValue = "654 765 765";
        //when
        ContactType contactType = ContactUtils.createContact(contactValue, customerId).getType();
        //then
        assertEquals(ContactType.PHONE, contactType);
    }

    @Test
    void shouldAssignContactTypePhone_WithDash() {
        //given
        String contactValue = "654-765-765";
        //when
        ContactType contactType = ContactUtils.createContact(contactValue, customerId).getType();
        //then
        assertEquals(ContactType.PHONE, contactType);
    }

    @Test
    void shouldAssignContactTypePhone_WithPrefix() {
        //given
        String contactValue = "+48654-765-765";
        //when
        ContactType contactType = ContactUtils.createContact(contactValue, customerId).getType();
        //then
        assertEquals(ContactType.PHONE, contactType);
    }

    @Test
    void shouldAssignContactTypeEmail() {
        //given
        String contactValue = "kowalski@gmail.com";
        //when
        ContactType contactType = ContactUtils.createContact(contactValue, customerId).getType();
        //then
        assertEquals(ContactType.EMAIL, contactType);
    }

    @Test
    void shouldAssignContactTypeJabber() {
        //given
        String contactValue = "jbr";
        //when
        ContactType contactType = ContactUtils.createContact(contactValue, customerId).getType();
        //then
        assertEquals(ContactType.JABBER, contactType);
    }

    @Test
    void shouldAssignContactTypeUnknown() {
        //given
        String contactValue = "1211212121";
        //when
        ContactType contactType = ContactUtils.createContact(contactValue, customerId).getType();
        //then
        assertEquals(ContactType.UNKNOWN, contactType);
    }
}
