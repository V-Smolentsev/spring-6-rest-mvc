package guru.springframework.spring6restmvc.controller;

import guru.springframework.spring6restmvc.model.BeerDTO;
import guru.springframework.spring6restmvc.repositories.BeerRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BeerControllerIntegrationTest {

    @Autowired
    BeerController beerController;
    @Autowired
    BeerRepository beerRepository;

    @Test
    void updateById() {
    }

    @Test
    void handlePost() {
    }

    @Test
    void testListBeers() {
        List<BeerDTO> beerDTOS = beerController.listBeers();

        assertThat(beerDTOS.size()).isEqualTo(3);
    }

    @Rollback
    @Transactional
    @Test
    void testEmptyList() {
        beerRepository.deleteAll();
        List<BeerDTO> beerDTOS = beerController.listBeers();

        assertThat(beerDTOS.size()).isEqualTo(0);
    }

    @Test
    void getBeerById() {
    }

    @Test
    void deleteById() {
    }

    @Test
    void patchBeerById() {
    }
}