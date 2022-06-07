package it.eng.lukapendelj.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import it.eng.lukapendelj.dao.MedicDAO;
import it.eng.lukapendelj.dao.OrganizationDAO;
//import it.eng.lukapendelj.dao.impl.OrganizationDaoImpl;
import it.eng.lukapendelj.entity.OrganizationEntity;
import it.eng.lukapendelj.service.ExaminationService;
import it.eng.lukapendelj.service.MedicService;
import it.eng.lukapendelj.service.OrganizationService;
import it.eng.lukapendelj.service.PatientService;


@Service
public class OrganizationServiceImpl implements OrganizationService {
	
	
	
	private OrganizationDAO organizationDao;
	
	
	/**
	   * This variable is used to bypass the circular referencing Spring Beans.
	   */
	private HelperServiceClass helperService;
	
	//private ExaminationService examinationService;
	
	//private MedicService medicService;
	
	//private PatientService patientService;
	
	
	@Autowired
	public OrganizationServiceImpl(OrganizationDAO organizationDao,  HelperServiceClass helperService) {
		super();
		this.organizationDao = organizationDao;
		//this.medicService = medicService;
		//this.patientService = patientService;
		this.helperService = helperService;
	}
	
	
	@Override
	public List<OrganizationEntity> findAll() {
		// TODO Auto-generated method stub
		return organizationDao.findAll();
	}

	@Override
	public Page<OrganizationEntity> findAll(Integer pageNo, Integer pageSize) {
		
		
		//System.out.println(organizationEntity);
		Pageable pageable = PageRequest.of(pageNo, pageSize);
		
		
		return organizationDao.findAll(pageable);
	}

	@Override
	public Optional<OrganizationEntity> findById(Long id) {
		// TODO Auto-generated method stub
		return organizationDao.findById(id);
	}

	@Override
	public List<OrganizationEntity> findByName(String name) {
		System.out.println("Pozvan findByName metod");
		return organizationDao.findByNameContaining(name);
	}

	@Override
	public OrganizationEntity update(OrganizationEntity organization) throws RuntimeException {
			
		Optional<OrganizationEntity> orgEntity = organizationDao.findById(organization.getOrganizationId());
		
		if(!orgEntity.isPresent()) {
			throw new RuntimeException("Organization does not exist:" + organization.getOrganizationCode());
		}
		
//		if(!organizationDao.findByOrganizationCode(organization.getOrganizationCode()).isEmpty()) {
//			throw new RuntimeException("Organization Code is not unique!");
//		}
		
		OrganizationEntity organizationUpdated = organizationDao.save(organization);
		
		return organizationUpdated;
	}

	@Override
	public OrganizationEntity save(OrganizationEntity organization) {
		System.out.println("Pozvan service orgSave ");
		
		if(!organizationDao.findByOrganizationCode(organization.getOrganizationCode()).isEmpty()) {
			throw new RuntimeException("Organization Code is not unique!");
		}
		
		
		
		
		return organizationDao.save(organization);
	}
	
	/**
	   * This method is used to delete organization Entites. First, there is a check. 
	   * If any examination in organization cooncerned is active, delete is impossible.
	   * Then, if the examination list is empty, the references to the organization are deleted inside the Exam, Medic and Patient entites??
	   * @return no value returned
	   */
	@Override
	public void deleteById(Long id) {
		
		
		System.out.println("Pozvan deleteOrgById");
		
		if(!helperService.getExaminationDao().findByOrganization(organizationDao.findById(id).get()).isEmpty()) {
			
			System.out.println("organizacija ima aktivne preglede!");
			throw new RuntimeException("Organization cannot be deleted!");
			//System.err.println("Organization cannot be deleted!");
		} else {
			System.out.println("Organization deleted");
		}
		
		//examinationSrvice.setOrganizationNull();
		helperService.getExaminationDao().deleteByOrganization(organizationDao.findById(id).get());
		helperService.setMedicOrganizationNull(organizationDao.findById(id).get());
		helperService.setPatientOrganizationNull(organizationDao.findById(id).get());
		
		organizationDao.deleteById(id);

	}
	
	
//	@Query("UPDATE medic JOIN ORGANIZATION ON medic.organization_id = organization.organization_id SET medic.organization_id=NULL WHERE organization.active = FALSE")
//	public void checkEmployees() {
//		System.out.println("Check employees FROM SERVICE called!");
//	}

	@Override
	public void delete(OrganizationEntity organization) {
		organizationDao.delete(organization);
		
		
	}


	@Override
	public List<OrganizationEntity> findByOrganizationType(String type) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public List<OrganizationEntity> findByAddress(String address) {
		// TODO Auto-generated method stub
		return organizationDao.findByAddressContaining(address);
	}


	@Override
	public List<OrganizationEntity> findByOrganizationCode(String code) {
		// TODO Auto-generated method stub
		return organizationDao.findByOrganizationCodeContaining(code);
	}


	


}
