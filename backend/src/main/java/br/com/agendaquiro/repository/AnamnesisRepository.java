package br.com.agendaquiro.repository;



import br.com.agendaquiro.domain.customer.Anamnesis;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AnamnesisRepository extends JpaRepository<Anamnesis, Long> {

}