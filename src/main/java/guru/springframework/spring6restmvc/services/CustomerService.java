package guru.springframework.spring6restmvc.services;

import guru.springframework.spring6restmvc.model.Customer;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public interface CustomerService {
    List<Customer> listAllCustomers();

    Customer getCustomerById(UUID id);

    Customer saveNewCustomer(Customer customer);

    void updateById(UUID customerId, Customer customer);

    boolean deleteById(UUID customerId);

    boolean patchById(UUID customerId, Customer body);
}
