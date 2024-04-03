package guru.springframework.spring6restmvc.services;

import guru.springframework.spring6restmvc.mapper.BeerMapper;
import guru.springframework.spring6restmvc.mapper.CustomerMapper;
import guru.springframework.spring6restmvc.model.CustomerDTO;
import guru.springframework.spring6restmvc.repositories.BeerRepository;
import guru.springframework.spring6restmvc.repositories.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Primary
@RequiredArgsConstructor
public class CustomerServiceJpa implements CustomerService {
    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;
    @Override
    public List<CustomerDTO> listAllCustomers() {
        return null;
    }

    @Override
    public CustomerDTO getCustomerById(UUID id) {
        return null;
    }

    @Override
    public CustomerDTO saveNewCustomer(CustomerDTO customer) {
        return null;
    }

    @Override
    public void updateById(UUID customerId, CustomerDTO customer) {

    }

    @Override
    public boolean deleteById(UUID customerId) {
        return false;
    }

    @Override
    public boolean patchById(UUID customerId, CustomerDTO body) {
        return false;
    }
}
