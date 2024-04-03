package guru.springframework.spring6restmvc.services;

import guru.springframework.spring6restmvc.model.CustomerDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class CustomerServiceImplTest {

    @Autowired
    CustomerService customerService;

    @Test
    void listAllCustomers() {
        System.out.println(customerService.listAllCustomers());
    }

    @Test
    void getCustomerById() {
        List<CustomerDTO> customers = customerService.listAllCustomers();
        System.out.println(customerService.getCustomerById(customers.get(0).getId()));
    }
}