package br.com.agendaquiro.repository;




import br.com.agendaquiro.domain.appointment.ServiceType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ServiceTypeRepository extends JpaRepository<ServiceType, Long> {


}
