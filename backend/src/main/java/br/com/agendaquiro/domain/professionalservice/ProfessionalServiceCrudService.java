package br.com.agendaquiro.domain.professionalservice;

import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProfessionalServiceCrudService {

    private ProfessionalServiceRepository professionalServiceRepository;

    public ProfessionalServiceCrudService(ProfessionalServiceRepository professionalServiceRepository) {
        this.professionalServiceRepository = professionalServiceRepository;
    }

    public Optional<ProfessionalService> getProfessionalServiceById(Long id) {
        return professionalServiceRepository.findById(id);
    }

    public Optional<ProfessionalService> findById(Long professionalServiceId) {
        return professionalServiceRepository.findById(professionalServiceId);
    }
}
