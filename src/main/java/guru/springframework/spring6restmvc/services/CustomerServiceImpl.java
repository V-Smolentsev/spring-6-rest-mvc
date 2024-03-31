package guru.springframework.spring6restmvc.services;

import guru.springframework.spring6restmvc.model.Customer;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalTime;
import java.util.*;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final Map<UUID, Customer> customerMap = new HashMap<>();

    public CustomerServiceImpl() {
        Customer first = Customer.builder()
                .id(UUID.randomUUID())
                .customerName("first")
                .version(1)
                .createdDate(LocalTime.now())
                .lastModifiedDate(LocalTime.now())
                .build();

        Customer second = Customer.builder()
                .id(UUID.randomUUID())
                .customerName("second")
                .version(12)
                .createdDate(LocalTime.now())
                .lastModifiedDate(LocalTime.now())
                .build();

        Customer third = Customer.builder()
                .id(UUID.randomUUID())
                .customerName("third")
                .version(33)
                .createdDate(LocalTime.now())
                .lastModifiedDate(LocalTime.now())
                .build();

        customerMap.put(first.getId(), first);
        customerMap.put(second.getId(), second);
        customerMap.put(third.getId(), third);
    }

    @Override
    public List<Customer> listAllCustomers() {
        return new ArrayList<>(customerMap.values());
    }

    @Override
    public Customer getCustomerById(UUID id) {
        return customerMap.get(id);
    }

    @Override
    public Customer saveNewCustomer(Customer customer) {
        Customer newCustomer = Customer.builder()
                .id(UUID.randomUUID())
                .customerName(customer.getCustomerName())
                .version(customer.getVersion())
                .createdDate(LocalTime.now())
                .lastModifiedDate(LocalTime.now())
                .build();
        customerMap.put(newCustomer.getId(), newCustomer);
        return customerMap.get(newCustomer.getId());
    }

    @Override
    public void updateById(UUID customerId, Customer customer) {
        Customer existingCustomer = customerMap.get(customerId);
        if (existingCustomer == null) {
            Customer newCustomer = Customer.builder()
                    .id(customerId)
                    .customerName(customer.getCustomerName())
                    .version(customer.getVersion())
                    .createdDate(LocalTime.now())
                    .lastModifiedDate(LocalTime.now())
                    .build();
            customerMap.put(newCustomer.getId(), newCustomer);
        } else {
            existingCustomer.setCustomerName(customer.getCustomerName());
            existingCustomer.setVersion(customer.getVersion());
        }
    }

    @Override
    public boolean deleteById(UUID customerId) {
        return customerMap.remove(customerId) != null;
    }

    @Override
    public boolean patchById(UUID customerId, Customer body) {
        Customer customer = customerMap.get(customerId);
        if (customer == null) {
            return false;
        }
        if (StringUtils.hasText(body.getCustomerName())) {
            customer.setCustomerName(body.getCustomerName());
        }
        if (body.getVersion() != null) {
            customer.setVersion(body.getVersion());
        }

        return true;
    }
}
