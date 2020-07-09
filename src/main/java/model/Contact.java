package model;

import java.util.Objects;

public class Contact {

    private Long id;
    private Long customerId;
    private ContactType type;
    private String value;

    public Contact( Long customerId, ContactType type, String value) {
        this.customerId = customerId;
        this.type = type;
        this.value = value;
    }

    public Contact(ContactType type, String value) {
        this.type = type;
        this.value = value;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public ContactType getType() {
        return type;
    }

    public void setType(ContactType type) {
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Contact contact1 = (Contact) o;
        return id.equals(contact1.id) &&
                customerId.equals(contact1.customerId) &&
                type == contact1.type &&
                value.equals(contact1.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, customerId, type, value);
    }

    @Override
    public String toString() {
        return "Contact{" +
                "id=" + id +
                ", customerId=" + customerId +
                ", contactType=" + type +
                ", contact='" + value + '\'' +
                '}';
    }
}
