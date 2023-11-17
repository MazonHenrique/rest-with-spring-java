package br.com.henrique.repositories;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Optional;

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

	@DisplayName("Given Person List when findAll then Return Person list")
	@Test
	void testGivenPersonList_whenFindAll_thenReturnPersonList() {
		//Given / Arrange
		Person person0 = new Person("Henrique", "Mazon", "hmazon87@yahoo.com", "Lauro Muller -SC", "Male");
		Person person1 = new Person("Cintia", "Danielski", "cintiaarthur@yahoo.com", "Lauro Muller -SC", "Female");
		
		repository.save(person0);
		repository.save(person1);
		
		//When / Act
		List<Person> personList = repository.findAll();
		
		//then / Assert
		assertNotNull(personList);
		assertEquals(2, personList.size());
		
	}
	
	@DisplayName("Given Person Object when findByID then Return Person object")
	@Test
	void testGivenPersonObject_whenFindById_thenReturnPersonObject () {
		//Given / Arrange
		Person person0 = new Person("Henrique", "Mazon", "hmazon87@yahoo.com", "Lauro Muller -SC", "Male");
		repository.save(person0);
		
		//When / Act
		Person savedPerson = repository.findById(person0.getId()).get();
		
		//then / Assert
		assertNotNull(savedPerson);
		assertEquals(person0.getId(), savedPerson.getId());
	}

	@DisplayName("Given Person Object when findByEmail then Return Person object")
	@Test
	void testGivenPersonObject_whenFindByEmail_thenReturnPersonObject () {
		//Given / Arrange
		Person person0 = new Person("Henrique", "Mazon", "hmazon87@yahoo.com", "Lauro Muller -SC", "Male");
		repository.save(person0);
		
		//When / Act
		Person savedPerson = repository.findByEmail(person0.getEmail()).get();
		
		//then / Assert
		assertNotNull(savedPerson);
		assertEquals(person0.getId(), savedPerson.getId());
	}

	@DisplayName("Given Person Object when update Person then Return Person object")
	@Test
	void testGivenPersonObject_whenUpdatePerson_thenReturnPersonObject () {
		//Given / Arrange
		Person person0 = new Person("Henrique", "Mazon", "hmazon87@yahoo.com", "Lauro Muller -SC", "Male");
		repository.save(person0);
		
		//When / Act
		Person savedPerson = repository.findByEmail(person0.getEmail()).get();
		savedPerson.setFirstName("Jose");
		savedPerson.setEmail("jose.mazon@gmail.com");
		
		Person updatePerson = repository.save(savedPerson);
		
		//then / Assert
		assertNotNull(updatePerson);
		assertEquals("Jose", updatePerson.getFirstName());
		assertEquals("jose.mazon@gmail.com", updatePerson.getEmail());
	}

	@DisplayName("Given Person Object when delete then remove object")
	@Test
	void testGivenPersonObject_whenDelete_thenRemovePerson() {
		//Given / Arrange
		Person person0 = new Person("Henrique", "Mazon", "hmazon87@yahoo.com", "Lauro Muller -SC", "Male");
		repository.save(person0);
		
		//When / Act
		repository.deleteById(person0.getId());
		
		Optional<Person> perOptional = repository.findById(person0.getId());
		
		//then / Assert
		assertTrue(perOptional.isEmpty());
	}
	
	@DisplayName("Given firstName and lastName when findByJPQL then Return Person object")
	@Test
	void testGivenFirstNameAndLastName_whenFindJPQL_thenReturnPersonObject () {
		//Given / Arrange
		Person person0 = new Person("Henrique", "Mazon", "hmazon87@yahoo.com", "Lauro Muller -SC", "Male");
		repository.save(person0);
		
		String firstName = "Henrique";
		String lastName = "Mazon";
		
		//When / Act
		Person savedPerson = repository.findByJPQL(firstName, lastName);
		
		//then / Assert
		assertNotNull(savedPerson);
		assertEquals(firstName, savedPerson.getFirstName());
		assertEquals(lastName, savedPerson.getLastName());
	}
	
	@DisplayName("Given firstName and lastName when findByJPQL named parameters then Return Person object")
	@Test
	void testGivenFirstNameAndLastName_whenFindByJPQLNamedParameters_thenReturnPersonObject () {
		//Given / Arrange
		Person person0 = new Person("Henrique", "Mazon", "hmazon87@yahoo.com", "Lauro Muller -SC", "Male");
		repository.save(person0);
		
		String firstName = "Henrique";
		String lastName = "Mazon";
		
		//When / Act
		Person savedPerson = repository.findByJPQLNamedParameters(firstName, lastName);
		
		//then / Assert
		assertNotNull(savedPerson);
		assertEquals(firstName, savedPerson.getFirstName());
		assertEquals(lastName, savedPerson.getLastName());
	}
}
