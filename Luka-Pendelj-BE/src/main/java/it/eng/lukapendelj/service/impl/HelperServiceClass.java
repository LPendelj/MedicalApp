package it.eng.lukapendelj.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.eng.lukapendelj.dao.ExaminationDAO;
import it.eng.lukapendelj.dao.MedicDAO;
import it.eng.lukapendelj.dao.OrganizationDAO;
import it.eng.lukapendelj.dao.PatientDAO;
import it.eng.lukapendelj.entity.MedicEntity;
import it.eng.lukapendelj.entity.OrganizationEntity;
import it.eng.lukapendelj.entity.PatientEntity;

/**Helper class made to avoid circular bean references
 * 
 * @author Acer
 *
 */

@Service
public class HelperServiceClass {
	
	private MedicDAO medicDao;
	private OrganizationDAO organizationDao;
	private ExaminationDAO examinationDao;
	private PatientDAO patientDao;
	
	
	@Autowired
	public HelperServiceClass(MedicDAO medicDao, OrganizationDAO organizationDao,
			ExaminationDAO examinationDao, PatientDAO patientDao) {
		super();
		this.patientDao = patientDao;
		this.examinationDao = examinationDao;
		this.organizationDao = organizationDao;
		this.medicDao = medicDao;
	}


	public MedicDAO getMedicDao() {
		return medicDao;
	}


	public void setMedicDao(MedicDAO medicDao) {
		this.medicDao = medicDao;
	}


	public OrganizationDAO getOrganizationDao() {
		return organizationDao;
	}
	//getOrganizationDao

	public void setOrganizationDao(OrganizationDAO organizationDao) {
		this.organizationDao = organizationDao;
	}


	public ExaminationDAO getExaminationDao() {
		return examinationDao;
	}


	public void setExaminationDao(ExaminationDAO examinationDao) {
		this.examinationDao = examinationDao;
	}


	public PatientDAO getPatientDao() {
		return patientDao;
	}


	public void setPatientDao(PatientDAO patientDao) {
		this.patientDao = patientDao;
	}

	/**
	 * Method which is used before Organization removal.
	 * It serves to set all medics' references of concerned organization to null
	 * @param organizationInactive
	 */
	public void setMedicOrganizationNull(OrganizationEntity organizationInactive) {
		List<MedicEntity> medicList = medicDao.findByOrganization(organizationInactive);
		
		System.out.println("Pozvan setOrgNull");
		
		medicList.forEach(medicItem -> {
			medicItem.setOrganization(null);
			//System.out.println("organizacije za brisanje: " + medicItem.getOrganization().getName());
			medicDao.save(medicItem);
		}
		);
		
	}
	
	/**Method which is used before Organization removal.
	 * It serves to set all patients' references of concerned organization to null
	 * 
	 * @param organizationInactive
	 */
	public void setPatientOrganizationNull(OrganizationEntity organizationInactive) {
		List<PatientEntity> patientList = patientDao.findByOrganization(organizationInactive);
		
		patientList.forEach(patientItem -> {
			patientItem.setOrganization(null);
			//System.out.println("organizacije za brisanje: " + medicItem.getOrganization().getName());
			patientDao.save(patientItem);
		}
		);
		
	}

	/**
	 * Method which is used before Medic removal
	 * It sets all patients references of concerned medics to null
	 * @param medicInactive
	 */
	public void setPatientMedicNull(MedicEntity medicInactive) {
		List<PatientEntity> patientList = patientDao.findByMainMedic(medicInactive);
		patientList.forEach(patient -> {
			patient.setMainMedic(null);
			patientDao.save(patient);
		});
	}

	
	
	
	
	
}
