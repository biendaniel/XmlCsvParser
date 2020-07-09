package model;

import java.util.Objects;

public class Customer {

    private Long id;
    private String name;
    private String surname;
    private Integer age;

    public Customer() {

    }

    public Customer(long id, String name, String surname, int age) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.age = age;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return id.equals(customer.id) &&
                name.equals(customer.name) &&
                surname.equals(customer.surname) &&
                Objects.equals(age, customer.age);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, surname, age);
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", age=" + age +
                '}';
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private String name;
        private String surname;
        private Integer age;

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder surname(String surname) {
            this.surname = surname;
            return this;
        }

        public Builder age(Integer age) {
            this.age = age;
            return this;
        }

        public Customer build() {
            if(name.isBlank()) {
                throw new IllegalStateException("Imię nie może być puste");
            }
            if(surname.isBlank()) {
                throw new IllegalStateException("Nazwisko nie może być puste");
            }

            Customer customer = new Customer();
            customer.name = this.name;
            customer.surname = this.surname;
            customer.age = this.age;
            return customer;
        }
    }
}
