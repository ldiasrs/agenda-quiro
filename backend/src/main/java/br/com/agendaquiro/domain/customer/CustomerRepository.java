package br.com.agendaquiro.domain.customer;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CustomerRepository extends CrudRepository<Customer, Long> {

    @Query("FROM Customer c " +
            "WHERE LOWER(c.name) like %:searchTerm% " +
            " OR LOWER(c.email) like %:searchTerm%" +
            " OR LOWER(c.cpf) like %:searchTerm%" +
            " OR LOWER(c.phone) like %:searchTerm%"
    )
    Page<Customer> search(String searchTerm, Pageable pageable);
}
