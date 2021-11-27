package br.com.agendaquiro.domain.professsional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProfessionalCrudService {

    private ProfessionalRepository professionalRepository;

    public ProfessionalCrudService(ProfessionalRepository professionalRepository) {
        this.professionalRepository = professionalRepository;
    }

    public Page<Professional> findByFilter(String searchTerm, PageRequest pageRequest) {
        return this.professionalRepository.search(searchTerm.toLowerCase(), pageRequest);
    }

    public Professional add(Professional professional) {
        return professionalRepository.save(professional);
    }

    public void delete(Long id) {
        this.professionalRepository.deleteById(id);
    }

    public Optional<Professional> findById(Long id) {
        return professionalRepository.findById(id);
    }

}
