package guru.springframework.spring6restmvc.services;

import guru.springframework.spring6restmvc.model.CustomerDTO;

import java.util.List;
import java.util.UUID;

public interface CustomerService {
    List<CustomerDTO> listAllCustomers();

    CustomerDTO getCustomerById(UUID id);

    CustomerDTO saveNewCustomer(CustomerDTO customer);

    void updateById(UUID customerId, CustomerDTO customer);

    boolean deleteById(UUID customerId);

    boolean patchById(UUID customerId, CustomerDTO body);
}
