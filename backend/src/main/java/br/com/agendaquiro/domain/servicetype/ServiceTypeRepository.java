package br.com.agendaquiro.domain.servicetype;



import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ServiceTypeRepository extends CrudRepository<ServiceType, Long> {

    @Query("FROM ServiceType p " +
            "WHERE LOWER(p.description) like %:searchTerm% ")
    Page<ServiceType> search(String searchTerm, Pageable page);
}
