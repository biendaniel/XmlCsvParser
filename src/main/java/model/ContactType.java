package model;

public enum ContactType {

    UNKNOWN(0, "Unknown"),
    EMAIL(1, "Email"),
    PHONE(2, "Phone"),
    JABBER(3, "Jabber"),
    ;

    private String name;
    private int value;
    private static final ContactType[] values = ContactType.values();

    ContactType(int value, String name) {
        this.value = value;
        this.name = name;
    }

    public static ContactType get(int type) {
        return  values[type];
    }

    public int getValue() {
        return value;
    }

    public String getName() {
        return name;
    }

}
