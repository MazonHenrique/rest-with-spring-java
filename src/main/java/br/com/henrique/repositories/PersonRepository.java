package br.com.henrique.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.henrique.model.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long>{

}
