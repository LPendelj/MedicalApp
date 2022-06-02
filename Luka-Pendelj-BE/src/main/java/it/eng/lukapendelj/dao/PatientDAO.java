package it.eng.lukapendelj.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import it.eng.lukapendelj.entity.MedicEntity;
import it.eng.lukapendelj.entity.OrganizationEntity;
import it.eng.lukapendelj.entity.PatientEntity;

public interface PatientDAO extends JpaRepository<PatientEntity, Long> {
	
	 List<PatientEntity> findByOrganization(OrganizationEntity organizationEntity);
	 
	 List<PatientEntity> findByMainMedic(MedicEntity medicEntity);
	

}
