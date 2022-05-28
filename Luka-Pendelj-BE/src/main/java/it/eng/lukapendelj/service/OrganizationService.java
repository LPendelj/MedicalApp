package it.eng.lukapendelj.service;

import java.util.List;
import java.util.Optional;

//import org.springframework.data.domain.Page;

import it.eng.lukapendelj.entity.OrganizationEntity;



public interface OrganizationService {
	
	List<OrganizationEntity> findAll();
	
	//Page<OrganizationEntity> findAll(Integer pageNo, Integer pageSize, String sortBy);

	Optional<OrganizationEntity> findById(Long id);
	
	List<OrganizationEntity> findByName(String name);
	
	OrganizationEntity update(OrganizationEntity organization) throws RuntimeException;

	OrganizationEntity save(OrganizationEntity organization);

	void deleteById(Long id); // throws InvalidEntityException
	
	void delete(OrganizationEntity organization);
	
}
