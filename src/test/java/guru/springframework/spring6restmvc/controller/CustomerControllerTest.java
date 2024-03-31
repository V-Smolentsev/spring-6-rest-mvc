package guru.springframework.spring6restmvc.controller;

import guru.springframework.spring6restmvc.model.Customer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CustomerControllerTest {

    @Autowired
    CustomerController controller;

    @Test
    void listAllCustomers() {
        System.out.println(controller.listAllCustomers());
    }

    @Test
    void getCustomerById() {
        List<Customer> customers = controller.listAllCustomers();
        System.out.println(controller.getCustomerById(customers.get(0).getId()));
    }
}