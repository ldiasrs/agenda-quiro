package br.com.agendaquiro.controller.professional;

import br.com.agendaquiro.controller.BaseController;
import br.com.agendaquiro.controller.MessageHttpResponse;
import br.com.agendaquiro.controller.professional.request.ProfessionalRequest;
import br.com.agendaquiro.domain.professsional.Professional;
import br.com.agendaquiro.domain.professsional.ProfessionalCrudService;
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
import java.util.stream.Collectors;

import static br.com.agendaquiro.config.PathMappings.*;
import static br.com.agendaquiro.controller.professional.request.ProfessionalRequest.convertToEntity;

@RestController
public class ProfessionalController extends BaseController {

    private ProfessionalCrudService professionalCrudService;

    public ProfessionalController(ProfessionalCrudService professionalCrudService) {
        this.professionalCrudService = professionalCrudService;
    }

    @PostMapping(PROFESSIONALS)
    public ResponseEntity<MessageHttpResponse> add(@Valid @RequestBody ProfessionalRequest professionalRequest) throws ParseException {
        Professional professional = this.professionalCrudService.add(convertToEntity(professionalRequest));
        return super.response(
                MessageHttpResponse
                        .builder()
                        .message("Customer created ID: " + professional.getId())
                        .build()
                        .addValue("id", String.valueOf(professional.getId()))
                ,
                HttpStatus.CREATED);
    }

    @DeleteMapping(PROFESSIONAL_DELETE)
    public ResponseEntity<MessageHttpResponse> delete(@PathVariable Long id) {
        this.professionalCrudService.delete(id);
        return super.response(
                MessageHttpResponse.builder()
                        .message("Deleted with success ID: " + id)
                        .build()
                        .addValue("id", String.valueOf(id)), HttpStatus.OK);
    }


//    @GetMapping(name=PROFESSIONAL_FILTER)
//    public Page<ProfessionalRequest> filter(@RequestParam(value ="searchTerm", required = false, defaultValue = "") String searchTerm,
//                                        @RequestParam(value = "page", defaultValue = "0") int page,
//                                        @RequestParam(value = "size", required = false, defaultValue = "10") int size) {
//        PageRequest pageRequest = PageRequest.of(
//                page,
//                size,
//                Sort.Direction.ASC,
//                "name");
//        Page<Professional> professionals = professionalCrudService.findByFilter(searchTerm, pageRequest);
//        List<ProfessionalRequest> professionalRequests = professionals.stream()
//                .map(ProfessionalRequest::convertToRequestDto)
//                .collect(Collectors.toList());
//        Page<ProfessionalRequest> pages =
//                new PageImpl<>(professionalRequests, pageRequest, professionals.getTotalElements());
//        return pages;
//    }
}
