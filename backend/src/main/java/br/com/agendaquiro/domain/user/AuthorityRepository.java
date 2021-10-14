package br.com.agendaquiro.domain.user;



import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AuthorityRepository extends CrudRepository<Authority, Long> {

}
