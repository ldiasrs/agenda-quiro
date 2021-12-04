package br.com.agendaquiro.controller.servicetype;

import br.com.agendaquiro.domain.exception.NotFoundException;
import br.com.agendaquiro.controller.BaseController;
import br.com.agendaquiro.controller.MessageHttpResponse;
import br.com.agendaquiro.controller.servicetype.request.ServiceTypeRequest;
import br.com.agendaquiro.domain.servicetype.ServiceType;
import br.com.agendaquiro.domain.servicetype.ServiceTypeCrudService;
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
import static br.com.agendaquiro.controller.servicetype.request.ServiceTypeRequest.convertToEntity;

@RestController
public class ServiceTypeController extends BaseController {

    private ServiceTypeCrudService serviceTypeService;

    public ServiceTypeController(ServiceTypeCrudService serviceTypeService) {
        this.serviceTypeService = serviceTypeService;
    }

    @PostMapping(value=SERVICES)
    public ResponseEntity<MessageHttpResponse> add(@Valid @RequestBody ServiceTypeRequest serviceTypeRequest) throws ParseException {
        ServiceType serviceType = this.serviceTypeService.add(convertToEntity(serviceTypeRequest));
        return super.response(
                MessageHttpResponse
                        .builder()
                        .message("ServiceType created ID: " + serviceType.getId())
                        .build()
                        .addValue("id", String.valueOf(serviceType.getId()))
                ,
                HttpStatus.CREATED);
    }

    @PutMapping(value=SERVICE_EDIT)
    public ResponseEntity<ServiceType> edit(@Valid @RequestBody ServiceTypeRequest serviceTypeRequest, @PathVariable Long id) {
        ServiceType serviceType = this.serviceTypeService.edit(convertToEntity(serviceTypeRequest), id);
        return super.response(serviceType, HttpStatus.OK);
    }

    @DeleteMapping(value=SERVICE_DELETE)
    public ResponseEntity<MessageHttpResponse> delete(@PathVariable Long id) {
        this.serviceTypeService.delete(id);
        return super.response(
                MessageHttpResponse.builder()
                        .message("Deleted with success ID: " + id)
                        .build()
                        .addValue("id", String.valueOf(id)), HttpStatus.OK);
    }

    @GetMapping(value=SERVICE_GET)
    public ResponseEntity<ServiceType> get(@PathVariable Long id) throws NotFoundException {
        Optional<ServiceType> serviceTypeOptional = this.serviceTypeService.findById(id);
        ResponseEntity<ServiceType> response = serviceTypeOptional.map(
                serviceType -> super.response(serviceType, HttpStatus.OK)
        ).orElseThrow(() -> new NotFoundException("ServiceType not found with ID: " + id));
        return response;
    }

    @GetMapping(value=SERVICE_FILTER)
    public Page<ServiceTypeRequest> filter(@RequestParam(value ="searchTerm", required = false, defaultValue = "") String searchTerm,
                                           @RequestParam(value = "page", defaultValue = "0") int page,
                                           @RequestParam(value = "size", required = false, defaultValue = "10") int size) {
        PageRequest pageRequest = PageRequest.of(
                page,
                size,
                Sort.Direction.ASC,
                "description");
        Page<ServiceType> serviceTypes = serviceTypeService.findByFilter(searchTerm, pageRequest);
        List<ServiceTypeRequest> serviceTypeRequests = serviceTypes.stream()
                .map(ServiceTypeRequest::convertToRequestDto)
                .collect(Collectors.toList());
        Page<ServiceTypeRequest> pages =
                new PageImpl<>(serviceTypeRequests, pageRequest, serviceTypes.getTotalElements());
        return pages;
    }
}
