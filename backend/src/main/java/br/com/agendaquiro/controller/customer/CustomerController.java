package br.com.agendaquiro.controller.customer;

import br.com.agendaquiro.common.utils.DataTransferObjectUtil;
import br.com.agendaquiro.domain.customer.Customer;
import br.com.agendaquiro.domain.to.CustomerTO;
import br.com.agendaquiro.domain.to.HttpResponseTO;
import br.com.agendaquiro.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import java.text.ParseException;

import static br.com.agendaquiro.config.PathMappings.CALENDAR_FREE_SLOTS;
import static br.com.agendaquiro.config.PathMappings.CUSTOMER;

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
}
