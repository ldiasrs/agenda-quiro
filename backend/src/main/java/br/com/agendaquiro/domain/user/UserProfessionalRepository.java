package br.com.agendaquiro.domain.user;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;


@Repository
public interface UserProfessionalRepository extends CrudRepository<UserProfessional, Long> {

    @Query("FROM UserProfessional p " +
            "WHERE p.user.id = :userID")
    List<UserProfessional> findByUserId(Long userID);

}
