package it.eng.lukapendelj.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;

import it.eng.lukapendelj.entity.ExaminationEntity;
import it.eng.lukapendelj.entity.OrganizationEntity;

//import org.springframework.stereotype.Service;




public interface ExaminationService {
	
	
	List<ExaminationEntity> findAll();
	
	
	
	Page<ExaminationEntity> findAll(Integer pageNo, Integer pageSize);

	Optional<ExaminationEntity> findById(Long id);
	
	List<ExaminationEntity> findByCode(String code);
	
	ExaminationEntity update(ExaminationEntity examination) throws RuntimeException;

	ExaminationEntity save(ExaminationEntity examination);

	void deleteById(Long id); // throws InvalidEntityException
	
	void delete(ExaminationEntity examination);
	
	void deleteByOrganization(OrganizationEntity organizationEntity);
}
