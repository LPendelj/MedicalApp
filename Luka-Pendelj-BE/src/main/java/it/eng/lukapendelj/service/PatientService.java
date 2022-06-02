package it.eng.lukapendelj.service;

import java.util.List;
import java.util.Optional;

import it.eng.lukapendelj.entity.OrganizationEntity;
import it.eng.lukapendelj.entity.PatientEntity;


public interface PatientService {
	
	List<PatientEntity> findAll();
	
	//Page<OrganizationEntity> findAll(Integer pageNo, Integer pageSize, String sortBy);

	Optional<PatientEntity> findById(Long id);
	
	List<PatientEntity> findByName(String name);
	
	PatientEntity update(PatientEntity patient) throws RuntimeException;

	PatientEntity save(PatientEntity patient);

	void deleteById(Long id); // throws InvalidEntityException
	
	void delete(PatientEntity patient);
	
	void setOrganizationNull(OrganizationEntity organizationInactive);
}
