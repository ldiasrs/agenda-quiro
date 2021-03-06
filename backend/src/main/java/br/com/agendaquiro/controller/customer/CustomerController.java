package br.com.agendaquiro.controller.customer;

import br.com.agendaquiro.domain.exception.NotFoundException;
import br.com.agendaquiro.controller.BaseController;
import br.com.agendaquiro.controller.MessageHttpResponse;
import br.com.agendaquiro.controller.customer.request.CustomerRequest;
import br.com.agendaquiro.domain.customer.Customer;
import br.com.agendaquiro.domain.customer.CustomerCrudService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.ParseException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static br.com.agendaquiro.config.PathMappings.*;
import static br.com.agendaquiro.controller.customer.request.CustomerRequest.convertToEntity;

@RestController
public class CustomerController extends BaseController {

    private CustomerCrudService customerService;

    public CustomerController(CustomerCrudService customerService) {
        this.customerService = customerService;
    }

    @PostMapping(value=CUSTOMERS)
    public ResponseEntity<MessageHttpResponse> add(@Valid @RequestBody CustomerRequest customerRequest) throws ParseException {
        Customer customer = this.customerService.add(convertToEntity(customerRequest));
        return super.response(
                MessageHttpResponse
                        .builder()
                        .message("Customer created ID: " + customer.getId())
                        .build()
                        .addValue("id", String.valueOf(customer.getId()))
                ,
                HttpStatus.CREATED);
    }

    @PutMapping(value=CUSTOMER_EDIT)
    public ResponseEntity<Customer> edit(@Valid @RequestBody CustomerRequest customerRequest, @PathVariable Long id) {
        Customer customer = this.customerService.edit(convertToEntity(customerRequest), id);
        return super.response(customer, HttpStatus.OK);
    }

    @DeleteMapping(value=CUSTOMER_DELETE)
    public ResponseEntity<MessageHttpResponse> delete(@PathVariable Long id) {
        this.customerService.delete(id);
        return super.response(
                MessageHttpResponse.builder()
                        .message("Deleted with success ID: " + id)
                        .build()
                        .addValue("id", String.valueOf(id)), HttpStatus.OK);
    }

    @GetMapping(value=CUSTOMER_GET)
    public ResponseEntity<Customer> get(@PathVariable Long id) throws NotFoundException {
        Optional<Customer> customerOptional = this.customerService.findById(id);
        ResponseEntity<Customer> response = customerOptional.map(
                customer -> super.response(customer, HttpStatus.OK)
        ).orElseThrow(() -> new NotFoundException("Customer not found with ID: " + id));
        return response;
    }

//    @GetMapping(CUSTOMERS)
//    public ResponseEntity<Iterable<Customer>> all()  {
//        return super.response(this.customerService.findAll(), HttpStatus.OK);
//    }

    @GetMapping(value=CUSTOMER_FILTER)
    public Page<CustomerRequest> filter(@RequestParam(value ="searchTerm", required = false, defaultValue = "") String searchTerm,
                                        @RequestParam(value = "page", defaultValue = "0") int page,
                                        @RequestParam(value = "size", required = false, defaultValue = "10") int size) {
        PageRequest pageRequest = PageRequest.of(
                page,
                size,
                Sort.Direction.ASC,
                "name");
        Page<Customer> customers = customerService.findByFilter(searchTerm, pageRequest);
        List<CustomerRequest> customerRequests = customers.stream()
                .map(CustomerRequest::convertToRequestDto)
                .collect(Collectors.toList());
        Page<CustomerRequest> pages =
                new PageImpl<>(customerRequests, pageRequest, customers.getTotalElements());
        return pages;
    }
}
