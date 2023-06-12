package guru.springframework.msscbrewery.web.controller.v2;

import com.sun.net.httpserver.Headers;
import guru.springframework.msscbrewery.services.BeerService;
import guru.springframework.msscbrewery.services.v2.BeerServiceV2;
import guru.springframework.msscbrewery.web.model.BeerDto;
import guru.springframework.msscbrewery.web.model.v2.BeerDtoV2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

/**
 * Created by jt on 2019-04-20.
 */
@RequestMapping("/api/v2/beer")
@RestController
public class BeerControllerV2 {

    private final BeerServiceV2 beerServiceV2;

    public BeerControllerV2(BeerServiceV2 beerServiceV2) {
        this.beerServiceV2 = beerServiceV2;
    }

    @GetMapping({"/{beerId}"})
    public ResponseEntity<BeerDtoV2> getBeer(@PathVariable("beerId") UUID beerId){
        return new ResponseEntity<>(beerServiceV2.getBeerById(beerId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity saveBeer(@RequestBody BeerDtoV2 beerDto){
        BeerDtoV2 saveDto = beerServiceV2.saveBeer(beerDto);
        Headers headers = new Headers();
//        Todo: Add the host name
        headers.add("Location", "/api/v1/beer" + saveDto.getId().toString());
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    @PutMapping("/{beerId}")
    public ResponseEntity updateBeer(@PathVariable("beerId") UUID beerId, @RequestBody BeerDtoV2 beerDto){
        beerServiceV2.updateBeer(beerId, beerDto);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{beerId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBeer(@PathVariable("beerId") UUID beerId){
        beerServiceV2.deleteByID(beerId);
    }

}
