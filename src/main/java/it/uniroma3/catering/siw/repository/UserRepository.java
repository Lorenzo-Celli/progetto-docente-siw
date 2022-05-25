package it.uniroma3.catering.siw.repository;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.catering.siw.model.User;


public interface UserRepository extends CrudRepository<User, Long> {

}