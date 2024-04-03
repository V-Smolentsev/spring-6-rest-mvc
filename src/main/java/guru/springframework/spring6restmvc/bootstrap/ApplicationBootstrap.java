package guru.springframework.spring6restmvc.bootstrap;

import guru.springframework.spring6restmvc.entities.Beer;
import guru.springframework.spring6restmvc.entities.Customer;
import guru.springframework.spring6restmvc.model.BeerStyle;
import guru.springframework.spring6restmvc.repositories.BeerRepository;
import guru.springframework.spring6restmvc.repositories.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Arrays;

@Component
@RequiredArgsConstructor
public class ApplicationBootstrap implements CommandLineRunner {
    private final BeerRepository beerRepository;
    private final CustomerRepository customerRepository;
    
    @Override
    public void run(String... args) throws Exception {
        populateBeers();
        populateCustomers();
    }

    private void populateBeers() {
        Beer beer1 = Beer.builder()
                .beerName("Galaxy Cat")
                .beerStyle(BeerStyle.PALE_ALE)
                .upc("12356")
                .price(new BigDecimal("12.99"))
                .quantityOnHand(122)
                .createdDate(LocalDateTime.now())
                .updateDate(LocalDateTime.now())
                .build();

        Beer beer2 = Beer.builder()
                .beerName("Crank")
                .beerStyle(BeerStyle.PALE_ALE)
                .upc("12356222")
                .price(new BigDecimal("11.99"))
                .quantityOnHand(392)
                .createdDate(LocalDateTime.now())
                .updateDate(LocalDateTime.now())
                .build();

        Beer beer3 = Beer.builder()
                .beerName("Sunshine City")
                .beerStyle(BeerStyle.IPA)
                .upc("12356")
                .price(new BigDecimal("13.99"))
                .quantityOnHand(144)
                .createdDate(LocalDateTime.now())
                .updateDate(LocalDateTime.now())
                .build();
        beerRepository.saveAll(Arrays.asList(beer1, beer2, beer3));
    }

    private void populateCustomers() {
        Customer first = Customer.builder()
                .version(1)
                .createdDate(LocalTime.now())
                .lastModifiedDate(LocalTime.now())
                .build();

        Customer second = Customer.builder()
                .version(12)
                .createdDate(LocalTime.now())
                .lastModifiedDate(LocalTime.now())
                .build();

        Customer third = Customer.builder()
                .version(33)
                .createdDate(LocalTime.now())
                .lastModifiedDate(LocalTime.now())
                .build();

        customerRepository.saveAll(Arrays.asList(first, second, third));
    }
}
