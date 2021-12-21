package br.com.agendaquiro.domain.audit;


import br.com.agendaquiro.domain.customer.Anamnesis;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AuditRepository extends JpaRepository<Audit, Long> {

}
