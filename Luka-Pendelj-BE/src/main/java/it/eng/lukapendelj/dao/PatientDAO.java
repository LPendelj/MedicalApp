package it.eng.lukapendelj.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import it.eng.lukapendelj.entity.MedicEntity;
import it.eng.lukapendelj.entity.OrganizationEntity;
import it.eng.lukapendelj.entity.PatientEntity;

@Transactional
public interface PatientDAO extends JpaRepository<PatientEntity, Long> {
	
	 List<PatientEntity> findByOrganization(OrganizationEntity organizationEntity);
	 
	 List<PatientEntity> findByMainMedic(MedicEntity medicEntity);
	 
	// List<PatientEntity> findByFirstnameContainingOrLastnameContaining(String name);
	 
	// Page<PatientEntity> findByFirstnameContainingOrLastnameContaining(String name, Pageable p);
	
	 //////////////////////////////
	 
	 Page<PatientEntity> findByOrganizationContaining(String name, Pageable p);
	 
	 List<PatientEntity> findByOrganizationContaining(String name);

	 List<PatientEntity> findByGenderContaining(Character gender);

	List<PatientEntity> findByPatientCodeContaining(String code);
	
	//List<PatientEntity>  findByPatientCode(String code);

	List<PatientEntity> findByPatientCode(String patientCode);
	

}
 