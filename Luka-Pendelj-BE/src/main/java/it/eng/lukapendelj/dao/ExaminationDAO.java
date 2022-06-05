package it.eng.lukapendelj.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import it.eng.lukapendelj.entity.ExaminationEntity;
import it.eng.lukapendelj.entity.OrganizationEntity;
import it.eng.lukapendelj.entity.PatientEntity;
import it.eng.lukapendelj.entity.ServiceTypeEntity;

@Transactional
public interface ExaminationDAO extends JpaRepository<ExaminationEntity, Long> {
		
	
	ExaminationEntity findByExaminationCode(String code);
	
	List<ExaminationEntity> findExaminationByPatient(Long id);
	
	//CHECK method names!!!
	
	List<ExaminationEntity> findByOrganization(OrganizationEntity organizationEntity);
	
	List<ExaminationEntity> findByPatient(PatientEntity patientEntity);
	
	//List<ExaminationEntity> findByMedic(Long id);
	
	//--------------------------IF I HAVE TIME
	
	List<ExaminationEntity> findByDiagnosis(String diagnosis);
	
	List<ExaminationEntity> findByServiceType(ServiceTypeEntity serviceType);
	
	List<ExaminationEntity> findByStatus(String status);
	
	//CHECK method arguments!!
	
	void deleteByOrganization(OrganizationEntity organizationEntity);
	
	
}
