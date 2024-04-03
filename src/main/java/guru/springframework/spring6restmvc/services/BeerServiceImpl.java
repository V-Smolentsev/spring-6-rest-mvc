package guru.springframework.spring6restmvc.services;

import guru.springframework.spring6restmvc.model.BeerDTO;
import guru.springframework.spring6restmvc.model.BeerStyle;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;

/**
 * Created by jt, Spring Framework Guru.
 */
@Slf4j
@Service
public class BeerServiceImpl implements BeerService {

    private final Map<UUID, BeerDTO> beerMap;

    public BeerServiceImpl() {
        this.beerMap = new HashMap<>();

        BeerDTO beer1 = BeerDTO.builder()
                .id(UUID.randomUUID())
                .version(1)
                .beerName("Galaxy Cat")
                .beerStyle(BeerStyle.PALE_ALE)
                .upc("12356")
                .price(new BigDecimal("12.99"))
                .quantityOnHand(122)
                .createdDate(LocalDateTime.now())
                .updateDate(LocalDateTime.now())
                .build();

        BeerDTO beer2 = BeerDTO.builder()
                .id(UUID.randomUUID())
                .version(1)
                .beerName("Crank")
                .beerStyle(BeerStyle.PALE_ALE)
                .upc("12356222")
                .price(new BigDecimal("11.99"))
                .quantityOnHand(392)
                .createdDate(LocalDateTime.now())
                .updateDate(LocalDateTime.now())
                .build();

        BeerDTO beer3 = BeerDTO.builder()
                .id(UUID.randomUUID())
                .version(1)
                .beerName("Sunshine City")
                .beerStyle(BeerStyle.IPA)
                .upc("12356")
                .price(new BigDecimal("13.99"))
                .quantityOnHand(144)
                .createdDate(LocalDateTime.now())
                .updateDate(LocalDateTime.now())
                .build();

        beerMap.put(beer1.getId(), beer1);
        beerMap.put(beer2.getId(), beer2);
        beerMap.put(beer3.getId(), beer3);
    }

    @Override
    public List<BeerDTO> listBeers(){
        return new ArrayList<>(beerMap.values());
    }

    @Override
    public Optional<BeerDTO> getBeerById(UUID id) {

        log.debug("Get Beer by Id - in service. Id: " + id.toString());

        return Optional.of(beerMap.get(id));
    }

    @Override
    public BeerDTO saveNewBeer(BeerDTO beer) {
        BeerDTO newBeer = BeerDTO.builder()
                .id(UUID.randomUUID())
                .createdDate(LocalDateTime.now())
                .updateDate(LocalDateTime.now())
                .beerName(beer.getBeerName())
                .upc(beer.getUpc())
                .version(beer.getVersion())
                .beerStyle(beer.getBeerStyle())
                .build();
        beerMap.put(newBeer.getId(), newBeer);
        return newBeer;
    }

    @Override
    public BeerDTO update(UUID beerId, BeerDTO beer) {
        BeerDTO newBeer = BeerDTO.builder()
                .id(beerId)
                .createdDate(LocalDateTime.now())
                .updateDate(LocalDateTime.now())
                .beerName(beer.getBeerName())
                .upc(beer.getUpc())
                .version(beer.getVersion())
                .beerStyle(beer.getBeerStyle())
                .build();
        beerMap.put(newBeer.getId(), newBeer);
        return newBeer;
    }

    @Override
    public void deleteBeer(UUID beerId) {
        beerMap.remove(beerId);
    }

    @Override
    public void patchById(UUID beerId, BeerDTO beer) {
        BeerDTO savedBeer = beerMap.get(beerId);

        if (StringUtils.hasText(beer.getBeerName())) {
            savedBeer.setBeerName(beer.getBeerName());
        }
        if (beer.getBeerStyle() != null) {
            savedBeer.setBeerStyle(beer.getBeerStyle());
        }
        if (StringUtils.hasText(beer.getUpc())) {
            savedBeer.setUpc(beer.getUpc());
        }
        if (beer.getVersion() != null) {
            savedBeer.setVersion(beer.getVersion());
        }
        if (beer.getQuantityOnHand() != null) {
            savedBeer.setQuantityOnHand(beer.getQuantityOnHand());
        }
        if (beer.getPrice() != null) {
            savedBeer.setPrice(beer.getPrice());
        }
    }
}
