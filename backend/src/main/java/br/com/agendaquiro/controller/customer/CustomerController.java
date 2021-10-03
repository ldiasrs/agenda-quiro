package br.com.agendaquiro.controller.customer;

import br.com.agendaquiro.common.utils.DataTransferObjectUtil;
import br.com.agendaquiro.domain.customer.Customer;
import br.com.agendaquiro.domain.to.CustomerTO;
import br.com.agendaquiro.domain.to.HttpResponseTO;
import br.com.agendaquiro.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.util.List;

import static br.com.agendaquiro.config.PathMappings.*;

@RestController
public class CustomerController extends BaseController {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private DataTransferObjectUtil dataTransferObjectUtil;

    @PostMapping(CUSTOMER)
    public ResponseEntity<HttpResponseTO> add(@Valid @RequestBody CustomerTO customerTO) {

        HttpResponseTO responseTO = null;
        try {
            Customer customer = dataTransferObjectUtil.getFillCustomer(customerTO);
            responseTO = this.customerService.add(customer);
        } catch (final Exception e) {
            e.printStackTrace();
        }

        return super.response(responseTO, null != responseTO.getStatus() ? responseTO.getStatus() : HttpStatus.OK);
    }

    @PutMapping(CUSTOMER_EDIT)
    public ResponseEntity<HttpResponseTO> edit(@Valid @RequestBody CustomerTO customerTO) {

        HttpResponseTO responseTO = null;
        try {
            Customer customer = dataTransferObjectUtil.getFillCustomer(customerTO);
            responseTO = this.customerService.edit(customer);
        } catch (final Exception e) {
            e.printStackTrace();
        }

        return super.response(responseTO, null != responseTO.getStatus() ? responseTO.getStatus() : HttpStatus.OK);
    }

    @DeleteMapping(CUSTOMER_DELETE)
    public ResponseEntity<HttpResponseTO> delete(@PathVariable Long id) {

        HttpResponseTO responseTO = null;
        try {
            responseTO = this.customerService.delete(id);
        } catch (final Exception e) {
            e.printStackTrace();
        }

        return super.response(responseTO, null != responseTO.getStatus() ? responseTO.getStatus() : HttpStatus.OK);
    }

    @GetMapping(CUSTOMER_FILTER)
    public ResponseEntity<?> filter(@PathVariable String name, final Pageable pageable) {

        final Page<Customer> customers = customerService.findByFilter(name, pageable);
        final List<CustomerTO> customerTOs = dataTransferObjectUtil.getFillCustomerTOsWithoutList(customers.getContent());
        final Page<CustomerTO> customersTO = new PageImpl<CustomerTO>(customerTOs, pageable, customers.getTotalElements());

        return super.builder(customersTO);
    }
}
