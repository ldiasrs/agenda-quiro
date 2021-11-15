package br.com.agendaquiro.domain.servicetype;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class ServiceTypeCrudService {

	private ServiceTypeRepository serviceTypeRepository;

	public ServiceTypeCrudService(ServiceTypeRepository serviceTypeRepository) {
		this.serviceTypeRepository = serviceTypeRepository;
	}

	public ServiceType add(ServiceType serviceType) {
		return this.serviceTypeRepository.save(serviceType);
	}

	public ServiceType edit(ServiceType serviceType, Long id) {
		serviceType.setId(id);
		return this.serviceTypeRepository.save(serviceType);
	}

	public Optional<ServiceType> findById(Long id) {
		return this.serviceTypeRepository.findById(id);
	}

	public void delete(Long id) {
		this.serviceTypeRepository.deleteById(id);
	}

	public Iterable<ServiceType> findAll() {
		return this.serviceTypeRepository.findAll();
	}

	public Page<ServiceType> findByFilter(String searchTerm, PageRequest pageRequest) {
		return this.serviceTypeRepository.search(searchTerm.toLowerCase(), pageRequest);
	}
}
