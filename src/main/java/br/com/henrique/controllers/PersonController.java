package br.com.henrique.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.henrique.model.Person;
import br.com.henrique.services.PersonServices;

@RestController
@RequestMapping("/person")
public class PersonController {
	
	@Autowired
	PersonServices personServices;
	
	@GetMapping
	public List<Person> findAll()throws Exception{
        return  personServices.findAll();
    }

	@GetMapping(value = "/{id}")
	public Person findById(@PathVariable(value = "id")Long id)throws Exception{
        return personServices.findById(id);
    }
	
	@PostMapping
	public Person create(@RequestBody Person person) {
		return personServices.create(person);
	}
	
	@PutMapping
	public Person update(@RequestBody Person person) {
		return personServices.update(person);
	}
	
	@DeleteMapping(value = "/{id}")
	public String delete(@PathVariable(value = "id")Long id) {
		personServices.delete(id);
		return "Person removed!";
	}
}