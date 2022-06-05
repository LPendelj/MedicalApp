package it.eng.lukapendelj.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import it.eng.lukapendelj.dao.OrganizationDAO;
import it.eng.lukapendelj.dao.PatientDAO;
import it.eng.lukapendelj.entity.MedicEntity;
import it.eng.lukapendelj.entity.OrganizationEntity;
import it.eng.lukapendelj.entity.PatientEntity;
import it.eng.lukapendelj.service.OrganizationService;
import it.eng.lukapendelj.service.PatientService;


@Service
public class PatientServiceImpl implements PatientService {
	
	PatientDAO patientDao;
	
	//OrganizationService organizationService;
	
	HelperServiceClass helperService;
	
	
	@Autowired
	public PatientServiceImpl(PatientDAO patientDao, HelperServiceClass helperService) {
		super();
		this.patientDao = patientDao;
		//this.organizationService = organizationService;
		this.helperService = helperService;
	}

	@Override
	public List<PatientEntity> findAll() {
		// TODO Auto-generated method stub
		return patientDao.findAll();
	}
	
	
	@Override
	public Page<PatientEntity> findAll(Integer pageNo, Integer pageSize) {
		// TODO Auto-generated method stub
		Pageable pageable = PageRequest.of(pageNo, pageSize);
		
		return patientDao.findAll(pageable);
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
			if(!helperService.getExaminationDao().findByPatient(patientDao.findById(id).get()).isEmpty()) {
			
			System.out.println("pacijent ima aktivne preglede!");
			throw new RuntimeException("Patient cannot be deleted!");
			//System.err.println("Organization cannot be deleted!");
		} else {
			System.out.println("Organization deleted");
			patientDao.deleteById(id);
		}
		
		
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

	@Override
	public List<PatientEntity> findByOrganization(Long id) {
		// TODO Auto-generated method stub
		List<PatientEntity> patientList = patientDao.findByOrganization(helperService.getOrganizationDao().findById(id).get());
		
		return patientList;
	}

	
	
	///////////////////////////////////////////////////////////////////////////
	
	@Override
	public List<PatientEntity> findByOrganizationName(String name) {
			
			List<OrganizationEntity> listOrganization = helperService.getOrganizationDao().findByNameContaining(name);
			
			List<PatientEntity> patientList = new ArrayList<PatientEntity>();
			
			System.out.println("listOrganization is " + listOrganization );
			
			for(OrganizationEntity org: listOrganization) {
				System.out.print(org.getOrganizationId() + ": ");
				patientList.addAll(patientDao.findByOrganization(org));
			}
			
		
			//System.out.println("From service " + patientList);
		return patientList;
	}

	

}
