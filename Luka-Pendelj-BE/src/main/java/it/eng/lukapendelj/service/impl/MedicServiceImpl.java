package it.eng.lukapendelj.service.impl;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import it.eng.lukapendelj.dao.MedicDAO;
import it.eng.lukapendelj.dao.OrganizationDAO;
import it.eng.lukapendelj.entity.MedicEntity;
import it.eng.lukapendelj.entity.OrganizationEntity;
import it.eng.lukapendelj.entity.PatientEntity;
import it.eng.lukapendelj.service.MedicService;
import it.eng.lukapendelj.service.OrganizationService;
import it.eng.lukapendelj.service.PatientService;

@Service
public class MedicServiceImpl implements MedicService {
	
	
	private MedicDAO medicDao;
	
	private HelperServiceClass helperService;
	
	//private PatientService patientService;
	
	//private OrganizationService organizationService;
	
	
	@Autowired
	public MedicServiceImpl(MedicDAO medicDao, HelperServiceClass helperService) {
		super();
		//this.organizationService = organizationService;
		this.medicDao = medicDao;
		//this.patientService = patientService;
		this.helperService = helperService;
	}

	@Override
	public List<MedicEntity> findAll() {
		
		return medicDao.findAll();
	}
	
	@Override
	public Page<MedicEntity> findAll(Integer pageNo, Integer pageSize) {
		// TODO Auto-generated method stub
		
		Pageable pageable = PageRequest.of(pageNo, pageSize);
		
		return medicDao.findAll(pageable);
	}

	@Override
	public Optional<MedicEntity> findById(Long id) {
		// TODO Auto-generated method stub
		return medicDao.findById(id);
	}

	@Override
	public List<MedicEntity> findByName(String firstname, String lastname) {
		// TODO Auto-generated method stub
		return medicDao.findByFirstnameOrLastnameContaining(firstname, lastname);
	}

	@Override
	public MedicEntity update(MedicEntity medic) throws RuntimeException {
		
		Optional<MedicEntity> medicEntity = medicDao.findById(medic.getMedicId());
		if(!medicEntity.isPresent()) {
			
			throw new RuntimeException("Practitioner does not exist:" + medic.getMedicCode());
		} 
//		if(!medicDao.findByMedicCode(medic.getMedicCode()).isEmpty()) {
//			throw new RuntimeException("Practitioner Code is not unique!");
//		}
		System.out.println("updated");
		
		MedicEntity medicUpdated = medicDao.save(medic);
		
		System.out.println(medicUpdated);
		
		return medicUpdated;
	}

	@Override
	public MedicEntity save(MedicEntity medic) {
		
		if(!medicDao.findByMedicCode(medic.getMedicCode()).isEmpty()) {
			throw new RuntimeException("Medic Code is not unique!");
		}
		
		// TODO Auto-generated method stub
		return medicDao.save(medic);
	}

	@Override
	public void deleteById(Long id) {
		
		helperService.setPatientMedicNull(medicDao.findById(id).get());
		
		System.out.println("deleteMedic Service called");
		
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
		//System.out.println("organizacija je: " +  organizationService.findById(id).get());
		//
		//System.out.println(medicDao.findByOrganization(organizationService.findById(id).get()));
	
		return medicDao.findByOrganization(helperService.getOrganizationDao().findById(id).get());
	}

	@Override
	public List<MedicEntity> findByOrganizationName(String name) {
		// TODO Auto-generated method stub
		List<OrganizationEntity> listOrganization = helperService.getOrganizationDao().findByNameContaining(name);
		
		List<MedicEntity> medicList = new ArrayList<>();
		
		System.out.println("listOrganization is " + listOrganization );
		
		for(OrganizationEntity org: listOrganization) {
			System.out.print(org.getOrganizationId() + ": ");
			medicList.addAll(medicDao.findByOrganization(org));
		}
		
		
		
		return medicList;
	}

	@Override
	public List<MedicEntity> findByQualification(String qualification) {
		// TODO Auto-generated method stub
		return medicDao.findByQualification(qualification);
	}

	@Override
	public List<MedicEntity> findByMedicCode(String code) {
		// TODO Auto-generated method stub
		return medicDao.findByMedicCodeContaining(code);
	}

	

}
