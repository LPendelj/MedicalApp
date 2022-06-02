package it.eng.lukapendelj.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.eng.lukapendelj.dao.PatientDAO;
import it.eng.lukapendelj.entity.MedicEntity;
import it.eng.lukapendelj.entity.OrganizationEntity;
import it.eng.lukapendelj.entity.PatientEntity;
import it.eng.lukapendelj.service.PatientService;


@Service
public class PatientServiceImpl implements PatientService {
	
	PatientDAO patientDao;
	
	
	
	@Autowired
	public PatientServiceImpl(PatientDAO patientDao) {
		super();
		this.patientDao = patientDao;
	}

	@Override
	public List<PatientEntity> findAll() {
		// TODO Auto-generated method stub
		return patientDao.findAll();
	}

	@Override
	public Optional<PatientEntity> findById(Long id) {
		// TODO Auto-generated method stub
		return patientDao.findById(id);
	}

	@Override
	public List<PatientEntity> findByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PatientEntity update(PatientEntity patient) throws RuntimeException {
		// TODO Auto-generated method stub
		
		Optional<PatientEntity> patEntity = patientDao.findById(patient.getPatientId());
		
		if(!patEntity.isEmpty()) {
			return patientDao.save(patient);
		}
		
		throw new RuntimeException("Medic does not exist:" + patient.getPatientCode());
		
		
	}
	
	//check code!!!

	@Override
	public PatientEntity save(PatientEntity patient) {
		// TODO Auto-generated method stub
		return patientDao.save(patient);
	}

	@Override
	public void deleteById(Long id) {
		patientDao.deleteById(id);
	}

	@Override
	public void delete(PatientEntity patient) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setOrganizationNull(OrganizationEntity organizationInactive) {
		List<PatientEntity> patientList = patientDao.findByOrganization(organizationInactive);
		
		patientList.forEach(patientItem -> {
			patientItem.setOrganization(null);
			//System.out.println("organizacije za brisanje: " + medicItem.getOrganization().getName());
			patientDao.save(patientItem);
		}
		);
		
	}

	@Override
	public void setMedicNull(MedicEntity medicInactive) {
		List<PatientEntity> patientList = patientDao.findByMainMedic(medicInactive);
		patientList.forEach(patient -> {
			patient.setMainMedic(null);
			patientDao.save(patient);
		});
	}

}
