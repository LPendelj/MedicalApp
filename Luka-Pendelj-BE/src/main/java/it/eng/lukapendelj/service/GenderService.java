package it.eng.lukapendelj.service;

import java.util.List;
import java.util.Optional;

import it.eng.lukapendelj.entity.Gender;

public interface GenderService {
	
	Optional<Gender> findById(Character id);
	
	List<Gender> findAll();
}
