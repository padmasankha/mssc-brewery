package guru.springframework.msscbrewery.services;

import guru.springframework.msscbrewery.web.model.CustomerDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;


@Service
@Slf4j
public class CustomerServiceImpl implements CustomerService{

    @Override
    public CustomerDto getCustomerById(UUID customerId) {
        return CustomerDto.builder().uuid(customerId).name("Padmasankha").build();
    }

    @Override
    public CustomerDto saveCustomer(CustomerDto customerDto) {
        return CustomerDto.builder().uuid(UUID.randomUUID()).build();
    }

    @Override
    public void updateCustomerByID(UUID customerId, CustomerDto customerDto) {

    }

    @Override
    public void deleteCustomerByID(UUID customerId) {
        log.info("Deleted customer on id: {}", customerId);
    }
}
