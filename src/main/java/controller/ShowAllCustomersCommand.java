package controller;

import model.Customer;
import service.CustomerService;
import userInterface.View;

public class ShowAllCustomersCommand implements Command {
    private View view;
    private CustomerService customerService;

    private static final String OPTION_NAME = "Wyświetl klientów.";

    public ShowAllCustomersCommand(View view, CustomerService customerService) {
        this.view = view;
        this.customerService = customerService;
    }

    @Override
    public void execute() {
        Iterable<Customer> customers = customerService.getAll();
            customers.forEach(c -> {
                view.printCustomer(c);
                view.printCustomerContacts(customerService.getCustomerContacts(c.getId()));
            });

    }

    @Override
    public String getLabel() {
        return OPTION_NAME;
    }
}
