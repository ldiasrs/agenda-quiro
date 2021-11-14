package br.com.agendaquiro.domain.professsional;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ProfessionalRepository extends CrudRepository<Professional, Long> {

    @Query("FROM Professional p " +
            "WHERE LOWER(p.name) like %:searchTerm% ")
    Page<Professional> search(String searchTerm, Pageable pageable);
}
