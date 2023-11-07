package br.com.henrique.services;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.henrique.exceptions.ResourceNotFoundException;
import br.com.henrique.model.Person;
import br.com.henrique.repositories.PersonRepository;

@Service
public class PersonServices {
	
	private Logger logger = Logger.getLogger(PersonServices.class.getName());
	
	@Autowired
	PersonRepository personRepository;
	
	public List<Person> findAll() {
		return personRepository.findAll();
	}

	public Person findById(Long id) {
		return personRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No records found this ID!"));
	}
	
	public Person create(Person person) {
		return personRepository.save(person);
	}

	public Person update(Person person) {
		var entity = personRepository.findById(person.getId()).orElseThrow(() -> new ResourceNotFoundException("No records found this ID!"));

		entity.setFirstName(person.getFirstName());
		entity.setLastName(person.getLastName());
		entity.setAdrees(person.getAdrees());
		entity.setGender(person.getGender());

		return personRepository.save(entity);
	}

	public void delete(Long id) {
		var entity = personRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No records found this ID!"));
		personRepository.delete(entity);
	}	

}
