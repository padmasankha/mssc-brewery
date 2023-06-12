package guru.springframework.msscbrewery.web.controller;


import guru.springframework.msscbrewery.services.CustomerService;
import guru.springframework.msscbrewery.web.model.CustomerDto;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/customer")
@Deprecated
public class CustomerController {


    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }


    @GetMapping("/{customerId}")
    ResponseEntity<CustomerDto> getCustomerByID(@PathVariable("customerId")UUID customerId){
        return new ResponseEntity<>(customerService.getCustomerById(customerId), HttpStatus.OK);
    }

    @PostMapping
    ResponseEntity saveCustomer(@RequestBody CustomerDto customerDto){
        CustomerDto saveCustomer = customerService.saveCustomer(customerDto);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", "/api/v1/customer" + saveCustomer.getUuid().toString());
        return new ResponseEntity<>(headers,HttpStatus.CREATED);
    }

    @PutMapping("/{customerId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void updateCustomer(@PathVariable("customerId")UUID customerId, @RequestBody CustomerDto customerDto){
        customerService.updateCustomerByID(customerId, customerDto);
    }

    @DeleteMapping("/{customerId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteCustomer(@PathVariable("customerId")UUID customerId){
        customerService.deleteCustomerByID(customerId);
    }
}
