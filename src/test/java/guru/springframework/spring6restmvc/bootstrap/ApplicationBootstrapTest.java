package guru.springframework.spring6restmvc.bootstrap;

import guru.springframework.spring6restmvc.repositories.BeerRepository;
import guru.springframework.spring6restmvc.repositories.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class ApplicationBootstrapTest {

    @Autowired
    BeerRepository beerRepository;
    @Autowired
    CustomerRepository customerRepository;

    ApplicationBootstrap applicationBootstrap;

    @BeforeEach
    void setUp() {
        applicationBootstrap = new ApplicationBootstrap(beerRepository, customerRepository);
    }

    @Test
    void testRun() throws Exception {
        applicationBootstrap.run(null);

        assertThat(customerRepository.count()).isEqualTo(3);
        assertThat(beerRepository.count()).isEqualTo(3);
    }
}