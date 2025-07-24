package com.erudio.respository;

import com.erudio.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PersonRepository extends JpaRepository<Person, Long> {


    /*
     * @Modifying - indica que a consulta altera dados, por padrao o spring acha que @query e apenas para "select" sem esta antoção
     * uma exception e lançada.
     * */
    @Modifying
    @Query("UPDATE Person p SET p.status = false WHERE p.id = :id")
    void disbalePerson(@Param("id") Long id);

}
