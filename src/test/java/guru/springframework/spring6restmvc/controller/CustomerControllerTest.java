package guru.springframework.spring6restmvc.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import guru.springframework.spring6restmvc.model.Customer;
import guru.springframework.spring6restmvc.services.CustomerService;
import guru.springframework.spring6restmvc.services.CustomerServiceImpl;
import org.apiguardian.api.API;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CustomerController.class)
class CustomerControllerTest {

    private static final String API_V_1 = "/api/v1";

    @Autowired
    MockMvc mockMvc;
    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    CustomerService customerService;

    CustomerServiceImpl customerServiceImpl;

    @Captor
    ArgumentCaptor<UUID> uuidArgumentCaptor;
    @Captor
    ArgumentCaptor<Customer> customerArgumentCaptor;

    @BeforeEach
    void setUp() {
        customerServiceImpl = new CustomerServiceImpl();
    }

    @Test
    void testListAllCustomers() throws Exception {
        given(customerService.listAllCustomers()).willReturn(customerServiceImpl.listAllCustomers());

        mockMvc.perform(MockMvcRequestBuilders.get(API_V_1 + "/customer")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.length()", is(3)));
    }

    @Test
    void testGetById() throws Exception {
        UUID id = UUID.randomUUID();
        Customer testCustomer = Customer.builder().id(id).customerName("name").build();
        given(customerService.getCustomerById(id)).willReturn(testCustomer);

        mockMvc.perform(MockMvcRequestBuilders.get(API_V_1 + "/customer/" + testCustomer.getId())
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.customerName", is(testCustomer.getCustomerName())))
                .andExpect(jsonPath("$.version", is(testCustomer.getVersion())));
    }

    @Test
    void handlePost() throws Exception {
        Customer testCustomer = customerServiceImpl.listAllCustomers().get(0);
        testCustomer.setId(null);

        given(customerService.saveNewCustomer(any(Customer.class))).willReturn(customerServiceImpl.listAllCustomers().get(1));

        mockMvc.perform(MockMvcRequestBuilders.post(API_V_1 + "/customer")
                .content(objectMapper.writeValueAsBytes(testCustomer))
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(header().exists("Location"));
    }

    @Test
    void updateById() throws Exception {
        Customer testCustomer = customerServiceImpl.listAllCustomers().get(0);

        mockMvc.perform(MockMvcRequestBuilders.put(API_V_1 + "/customer/" + testCustomer.getId())
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(testCustomer)))
                .andExpect(status().isNoContent());

        verify(customerService).updateById(any(UUID.class), any(Customer.class));
    }

    @Test
    void deleteById() throws Exception {
        Customer testCustomer = customerServiceImpl.listAllCustomers().get(0);

        mockMvc.perform(MockMvcRequestBuilders.delete(API_V_1 + "/customer/" + testCustomer.getId())
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());

        verify(customerService).deleteById(uuidArgumentCaptor.capture());
        assertThat(testCustomer.getId()).isEqualTo(uuidArgumentCaptor.getValue());
    }

    @Test
    void patchById() throws Exception {
        Customer testCustomer = customerServiceImpl.listAllCustomers().get(0);
        Map<String, Object> propsMap = new HashMap<>();
        propsMap.put("customerName", "New test name");

        mockMvc.perform(MockMvcRequestBuilders.patch(API_V_1 + "/customer/" + testCustomer.getId())
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(propsMap)))
                .andExpect(status().isNoContent());

        verify(customerService).patchById(uuidArgumentCaptor.capture(), customerArgumentCaptor.capture());
        assertThat(testCustomer.getId()).isEqualTo(uuidArgumentCaptor.getValue());
        assertThat(propsMap.get("customerName")).isEqualTo(customerArgumentCaptor.getValue().getCustomerName());
    }
}