package guru.springframework.spring6restmvc.controller;

import guru.springframework.spring6restmvc.model.Customer;
import guru.springframework.spring6restmvc.services.CustomerService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.event.ListDataEvent;
import java.util.List;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/api/v1/customer")
@AllArgsConstructor
public class CustomerController {
    private final CustomerService customerService;

    @RequestMapping(method = RequestMethod.GET)
    public List<Customer> listAllCustomers() {
        log.debug("Get all customers!!!!!");
        return customerService.listAllCustomers();
    }

    @RequestMapping(value = "{customerId}", method = RequestMethod.GET)
    public Customer getCustomerById(@PathVariable("customerId") UUID customerId) {
        return customerService.getCustomerById(customerId);
    }

    @PostMapping
    public ResponseEntity<Void> handlePost(@RequestBody Customer customer) {
        Customer savedCustomer = customerService.saveNewCustomer(customer);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Location", "/api/v1/customer/" + savedCustomer.getId());

        return new ResponseEntity<>(httpHeaders, HttpStatus.CREATED);
    }

    @PutMapping(path = "{customerId}")
    public ResponseEntity<Void> updateById(@PathVariable("customerId") UUID customerId, @RequestBody Customer customer) {
        customerService.updateById(customerId, customer);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("{customerId}")
    public ResponseEntity<Void> deleteById(@PathVariable("customerId") UUID customerId) {
        boolean isExisted = customerService.deleteById(customerId);
        return new ResponseEntity<>(isExisted ? HttpStatus.NO_CONTENT : HttpStatus.NOT_FOUND);
    }

    @PatchMapping("{customerId}")
    public ResponseEntity<Void> patchById(@PathVariable("customerId") UUID customerId, @RequestBody Customer body) {
        boolean isExisted = customerService.patchById(customerId, body);
        return new ResponseEntity<>(isExisted ? HttpStatus.NO_CONTENT : HttpStatus.NOT_FOUND);
    }
}
