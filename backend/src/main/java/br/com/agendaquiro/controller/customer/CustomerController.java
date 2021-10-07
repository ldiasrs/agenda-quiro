package br.com.agendaquiro.controller.customer;

import br.com.agendaquiro.controller.BaseController;
import br.com.agendaquiro.controller.MessageHttpResponse;
import br.com.agendaquiro.controller.customer.request.CustomerRequest;
import br.com.agendaquiro.domain.customer.Customer;
import br.com.agendaquiro.domain.customer.CustomerCrudService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.ParseException;
import java.util.List;
import java.util.stream.Collectors;

import static br.com.agendaquiro.config.PathMappings.*;
import static br.com.agendaquiro.controller.customer.request.CustomerRequest.convertToEntity;

@RestController
public class CustomerController extends BaseController {

    private CustomerCrudService customerService;

    public CustomerController(CustomerCrudService customerService) {
        this.customerService = customerService;
    }

    @PostMapping(CUSTOMER)
    public ResponseEntity<MessageHttpResponse> add(@Valid @RequestBody CustomerRequest customerRequest) throws ParseException {
        Customer customer = this.customerService.add(convertToEntity(customerRequest));
        return super.response(
                MessageHttpResponse
                        .builder()
                        .message("Customer created ID: " + customer.getId()).build(),
                HttpStatus.CREATED);
    }

    @PutMapping(CUSTOMER_EDIT)
    public ResponseEntity<MessageHttpResponse> edit(@Valid @RequestBody CustomerRequest customerRequest) {
        this.customerService.edit(convertToEntity(customerRequest));
        return super.response(MessageHttpResponse.builder().build(), HttpStatus.OK);
    }

    @DeleteMapping(CUSTOMER_DELETE)
    public ResponseEntity<MessageHttpResponse> delete(@PathVariable Long id) {
        this.customerService.delete(id);
        return super.response(MessageHttpResponse.builder().build(), HttpStatus.OK);
    }

    @GetMapping(CUSTOMER_FILTER)
    public ResponseEntity<?> filter(@PathVariable String name, Pageable pageable) {
        Page<Customer> customers = customerService.findByFilter(name, pageable);
        List<CustomerRequest> customerRequests = customers.stream()
                .map(CustomerRequest::convertToRequestDto)
                .collect(Collectors.toList());
        Page<CustomerRequest> pages =
                new PageImpl<>(customerRequests, pageable, customers.getTotalElements());
        return super.pageResult(pages);
    }
}
