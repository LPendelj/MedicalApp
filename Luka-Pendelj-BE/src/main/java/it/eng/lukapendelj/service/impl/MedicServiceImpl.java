package it.eng.lukapendelj.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.eng.lukapendelj.dao.MedicDAO;
import it.eng.lukapendelj.dao.OrganizationDAO;
import it.eng.lukapendelj.entity.MedicEntity;
import it.eng.lukapendelj.entity.OrganizationEntity;
import it.eng.lukapendelj.service.MedicService;
import it.eng.lukapendelj.service.PatientService;

@Service
public class MedicServiceImpl implements MedicService {
	
	
	MedicDAO medicDao;
	
	PatientService patientService;
	
	OrganizationDAO organizationDao;
	
	
	@Autowired
	public MedicServiceImpl(MedicDAO medicDao, OrganizationDAO organizationDao, PatientService patientService) {
		super();
		this.organizationDao = organizationDao;
		this.medicDao = medicDao;
		this.patientService = patientService;
	}

	@Override
	public List<MedicEntity> findAll() {
		
		return medicDao.findAll();
	}

	@Override
	public Optional<MedicEntity> findById(Long id) {
		// TODO Auto-generated method stub
		return medicDao.findById(id);
	}

	@Override
	public List<MedicEntity> findByName(String firstname, String lastname) {
		// TODO Auto-generated method stub
		return medicDao.findByFirstnameAndLastname(firstname, lastname);
	}

	@Override
	public MedicEntity update(MedicEntity medic) throws RuntimeException {
		
		Optional<MedicEntity> medicEntity = medicDao.findById(medic.getMedicId());
		if(!medicEntity.isPresent()) {
			throw new RuntimeException("Medic does not exist:" + medic.getMedicCode());
		}
		
		MedicEntity medicUpdated = medicDao.save(medic);
		
		return medicUpdated;
	}

	@Override
	public MedicEntity save(MedicEntity medic) {
		// TODO Auto-generated method stub
		return medicDao.save(medic);
	}

	@Override
	public void deleteById(Long id) {
		
		patientService.setMedicNull(medicDao.findById(id).get());
		
		medicDao.deleteById(id);

	}
	
	
	//MEthod by which we achieve to set inactive organizations' references at null
	@Override
	public void setOrganizationNull(OrganizationEntity organizationInactive) {
		List<MedicEntity> medicList = medicDao.findByOrganization(organizationInactive);
		
		System.out.println("Pozvan setOrgNull");
		
		medicList.forEach(medicItem -> {
			medicItem.setOrganization(null);
			//System.out.println("organizacije za brisanje: " + medicItem.getOrganization().getName());
			medicDao.save(medicItem);
		}
		);
		
	}

	@Override
	public List<MedicEntity> findByOrganization(Long id) {
		// TODO Auto-generated method stub
	System.out.println("organizacija je: " +  organizationDao.findById(id).get());
		
		System.out.println(medicDao.findByOrganization(organizationDao.findById(id).get()));
	
		return medicDao.findByOrganization(organizationDao.findById(id).get());
	}

}
