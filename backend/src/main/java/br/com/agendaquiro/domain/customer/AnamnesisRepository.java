package br.com.agendaquiro.domain.customer;



import br.com.agendaquiro.domain.customer.Anamnesis;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AnamnesisRepository extends JpaRepository<Anamnesis, Long> {

}