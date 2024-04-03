package guru.springframework.spring6restmvc.controller;

import guru.springframework.spring6restmvc.model.CustomerDTO;
import guru.springframework.spring6restmvc.services.CustomerService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Slf4j
@RestController
@AllArgsConstructor
public class CustomerController {
    public static final String CUSTOMER_ID = "customerId";
    public static final String CUSTOMER_PATH = "/api/v1/customer";
    public static final String CUSTOMER_ID_PATH = CUSTOMER_PATH + "/{" + CUSTOMER_ID + "}";
    private final CustomerService customerService;

    @GetMapping(CUSTOMER_PATH)
    public List<CustomerDTO> listAllCustomers() {
        log.debug("Get all customers!!!!!");
        return customerService.listAllCustomers();
    }

    @GetMapping(CUSTOMER_ID_PATH)
    public CustomerDTO getCustomerById(@PathVariable(CUSTOMER_ID) UUID customerId) {
        return customerService.getCustomerById(customerId);
    }

    @PostMapping(CUSTOMER_PATH)
    public ResponseEntity<Void> handlePost(@RequestBody CustomerDTO customer) {
        CustomerDTO savedCustomer = customerService.saveNewCustomer(customer);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Location", CUSTOMER_PATH + "/" + savedCustomer.getId());

        return new ResponseEntity<>(httpHeaders, HttpStatus.CREATED);
    }

    @PutMapping(CUSTOMER_ID_PATH)
    public ResponseEntity<Void> updateById(@PathVariable(CUSTOMER_ID) UUID customerId, @RequestBody CustomerDTO customer) {
        customerService.updateById(customerId, customer);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping(CUSTOMER_ID_PATH)
    public ResponseEntity<Void> deleteById(@PathVariable(CUSTOMER_ID) UUID customerId) {
        boolean isExisted = customerService.deleteById(customerId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PatchMapping(CUSTOMER_ID_PATH)
    public ResponseEntity<Void> patchById(@PathVariable(CUSTOMER_ID) UUID customerId, @RequestBody CustomerDTO body) {
        boolean isExisted = customerService.patchById(customerId, body);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
