package guru.springframework.spring6restmvc.controller;

import guru.springframework.spring6restmvc.model.BeerDTO;
import guru.springframework.spring6restmvc.services.BeerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

/**
 * Created by jt, Spring Framework Guru.
 */
@Slf4j
@RequiredArgsConstructor
@RestController
public class BeerController {
    public static final String BEER_PATH = "/api/v1/beer";
    public static final String BEER_PATH_ID = BEER_PATH + "/{beerId}";
    public static final String BEER_ID = "beerId";
    private final BeerService beerService;

    @PutMapping(BEER_PATH_ID)
    public ResponseEntity<Void> updateById(@PathVariable(BEER_ID) UUID beerId, @RequestBody BeerDTO beer) {
        HttpStatus httpStatus = null;
        HttpHeaders headers = new HttpHeaders();
        beerService.update(beerId, beer);
        httpStatus = HttpStatus.OK;
        return new ResponseEntity<>(headers, httpStatus);
    }

    @PostMapping(BEER_PATH)
    public ResponseEntity<Void> handlePost(@RequestBody BeerDTO beer) {
        BeerDTO savedBeer = beerService.saveNewBeer(beer);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Location",  BEER_PATH + "/" + savedBeer.getId().toString());

        return new ResponseEntity<>(null, headers, HttpStatus.CREATED);
    }

    @GetMapping(BEER_PATH)
    public List<BeerDTO> listBeers(){

        return beerService.listBeers();
    }

    @GetMapping(BEER_PATH_ID)
    public BeerDTO getBeerById(@PathVariable(BEER_ID) UUID beerId){

        log.debug("Get Beer by Id - in controller");

        return beerService.getBeerById(beerId).orElseThrow(NotFoundException::new);
    }

    @DeleteMapping(BEER_PATH_ID)
    public ResponseEntity<Void> deleteById(@PathVariable(BEER_ID) UUID beerId) {
        beerService.deleteBeer(beerId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PatchMapping(BEER_PATH_ID)
    public ResponseEntity<Void> patchBeerById(@PathVariable(BEER_ID) UUID beerId, @RequestBody BeerDTO beer) {
        beerService.patchById(beerId, beer);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
