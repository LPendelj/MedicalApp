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
	
	
	public void setPatientOrganizationNull(OrganizationEntity organizationInactive) {
		List<PatientEntity> patientList = patientDao.findByOrganization(organizationInactive);
		
		patientList.forEach(patientItem -> {
			patientItem.setOrganization(null);
			//System.out.println("organizacije za brisanje: " + medicItem.getOrganization().getName());
			patientDao.save(patientItem);
		}
		);
		
	}

	
	public void setPatientMedicNull(MedicEntity medicInactive) {
		List<PatientEntity> patientList = patientDao.findByMainMedic(medicInactive);
		patientList.forEach(patient -> {
			patient.setMainMedic(null);
			patientDao.save(patient);
		});
	}

	
	
	
	
	
}
