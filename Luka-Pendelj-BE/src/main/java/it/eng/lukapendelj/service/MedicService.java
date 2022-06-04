package it.eng.lukapendelj.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;

import it.eng.lukapendelj.entity.MedicEntity;
import it.eng.lukapendelj.entity.OrganizationEntity;


public interface MedicService {
	
	List<MedicEntity> findAll();
	
	Page<MedicEntity> findAll(Integer pageNo, Integer pageSize);
	
	//Page<OrganizationEntity> findAll(Integer pageNo, Integer pageSize, String sortBy);

	Optional<MedicEntity> findById(Long id);
	
	List<MedicEntity> findByName(String name, String lastname);
	
	MedicEntity update(MedicEntity medic) throws RuntimeException;

	MedicEntity save(MedicEntity medic);

	void deleteById(Long id); // throws InvalidEntityException
	
	void setOrganizationNull(OrganizationEntity organizationInactive);
	
	List<MedicEntity> findByOrganization(Long id);
}
