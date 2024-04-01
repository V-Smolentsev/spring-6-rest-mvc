package guru.springframework.spring6restmvc.controller;

import guru.springframework.spring6restmvc.model.Beer;
import guru.springframework.spring6restmvc.services.BeerService;
import lombok.AllArgsConstructor;
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
@RequestMapping("/api/v1/beer")
public class BeerController {
    private final BeerService beerService;

    @PutMapping(path = "{beerId}")
    public ResponseEntity<Void> updateById(@PathVariable("beerId") UUID beerId, @RequestBody Beer beer) {
        HttpStatus httpStatus = null;
        HttpHeaders headers = new HttpHeaders();
        beerService.update(beerId, beer);
        httpStatus = HttpStatus.OK;
        return new ResponseEntity<>(headers, httpStatus);
    }

    @PostMapping
    public ResponseEntity handlePost(@RequestBody Beer beer) {
        Beer savedBeer = beerService.saveNewBeer(beer);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", "/api/v1/beer/" + savedBeer.getId().toString());

        return new ResponseEntity(null, headers, HttpStatus.CREATED);
    }
    @GetMapping
    public List<Beer> listBeers(){

        return beerService.listBeers();
    }

    @RequestMapping(path = "{beerId}", method = RequestMethod.GET)
    public Beer getBeerById(@PathVariable("beerId") UUID beerId){

        log.debug("Get Beer by Id - in controller");

        return beerService.getBeerById(beerId);
    }

    @DeleteMapping(path = "{beerId}")
    public ResponseEntity<Void> deleteById(@PathVariable("beerId") UUID beerId) {
        boolean isExisted = beerService.deleteBeer(beerId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PatchMapping("{beerId}")
    public ResponseEntity<Void> patchBeerById(@PathVariable("beerId") UUID beerId, @RequestBody Beer beer) {
        boolean isPatched = beerService.patchById(beerId, beer);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
