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
		
	
	
	
	List<ExaminationEntity> findExaminationByPatient(Long id);
	
	//CHECK method names!!!
	
	List<ExaminationEntity> findByOrganization(OrganizationEntity organizationEntity);
	
	List<ExaminationEntity> findByPatient(PatientEntity patientEntity);
	
	//List<ExaminationEntity> findByMedic(Long id);
	
	//--------------------------IF I HAVE TIME
	
	List<ExaminationEntity> findByDiagnosis(String diagnosis);
	
	List<ExaminationEntity> findByServiceType(ServiceTypeEntity serviceType);
	
	
	
	//CHECK method arguments!!
	
	void deleteByOrganization(OrganizationEntity organizationEntity);
	
//	 <option value="organization">Organization</option>
//     <option value="code">Exmination code</option>
//     <option value="status">Status</option>
//     <option value="priority">Priority</option>
	
	List<ExaminationEntity> findByExaminationCodeContaining(String code);
	
	List<ExaminationEntity> findByStatusContaining(String status);
	
	List<ExaminationEntity> findByPriorityContaining(String priority);
	
}
