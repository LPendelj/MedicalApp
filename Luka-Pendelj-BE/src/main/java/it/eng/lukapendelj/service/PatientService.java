package it.eng.lukapendelj.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import it.eng.lukapendelj.entity.MedicEntity;
import it.eng.lukapendelj.entity.OrganizationEntity;
import it.eng.lukapendelj.entity.PatientEntity;


public interface PatientService {
	
	List<PatientEntity> findAll();
	
	Page<PatientEntity> findAll(Integer pageNo, Integer pageSize);
	
	//String sortBy

	Optional<PatientEntity> findById(Long id);
	
	List<PatientEntity> findByName(String name);
	
	//Page<PatientEntity> findByName(String name, Integer pageNo, Integer pageSize);
	
	//Page<PatientEntity> findByName(String name);
	
	List<PatientEntity> findByOrganization(Long id);
	
	//Page<PatientEntity> findByOrganizationName(String name, Integer pageNo, Integer pageSize);
	
	List<PatientEntity> findByOrganizationName(String name);
	
	//Page<PatientEntity> findByGender(Character gender, Pageable p);
	
	List<PatientEntity> findByGender(Character gender);
	
	PatientEntity update(PatientEntity patient) throws RuntimeException;

	PatientEntity save(PatientEntity patient);

	void deleteById(Long id); // throws InvalidEntityException
	
	void delete(PatientEntity patient);
	
	void setOrganizationNull(OrganizationEntity organizationInactive);
	
	void setMedicNull(MedicEntity medicEntity);

	Page<PatientEntity> findByMedicName(String string,  int parseInt, int parseInt2);

	List<PatientEntity> findByPatientCode(String string);
}
