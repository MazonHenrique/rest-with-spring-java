package br.com.henrique.repositories;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import br.com.henrique.model.Person;

@DataJpaTest
class PersonRepositoryTest {
	
	@Autowired
	private PersonRepository repository;

	@DisplayName("Given Person Object when Save then Return Saved Person")
	@Test
	void testGivenPersonObject_whenSave_thenReturnSavedPerson() {
		//Given / Arrange
		Person person0 = new Person("Henrique", "Mazon", "hmazon87@yahoo.com", "Lauro Muller -SC", "Male");
		
		//When / Act
		Person savedPerson = repository.save(person0);
		
		//then / Assert
		assertNotNull(savedPerson);
		assertTrue(savedPerson.getId() > 0);
		
	}

}
