package it.eng.lukapendelj.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import it.eng.lukapendelj.dao.MedicDAO;
import it.eng.lukapendelj.dao.OrganizationDAO;
//import it.eng.lukapendelj.dao.impl.OrganizationDaoImpl;
import it.eng.lukapendelj.entity.OrganizationEntity;
import it.eng.lukapendelj.service.ExaminationService;
import it.eng.lukapendelj.service.OrganizationService;


@Service
public class OrganizationServiceImpl implements OrganizationService {
	
	
	
	private OrganizationDAO organizationDao;
	
	private ExaminationService examinationService;
	
	private MedicServiceImpl medicService;
	
	private PatientServiceImpl patientService;
	
	
	@Autowired
	public OrganizationServiceImpl(OrganizationDAO organizationDao, MedicServiceImpl medicService, PatientServiceImpl patientService, ExaminationService examinationService) {
		super();
		this.organizationDao = organizationDao;
		this.medicService = medicService;
		this.patientService = patientService;
		this.examinationService = examinationService;
	}

	@Override
	public List<OrganizationEntity> findAll() {
		List<OrganizationEntity> organizationEntity = organizationDao.findAll();
		
		//System.out.println(organizationEntity);
		
		return organizationEntity;
	}

	@Override
	public Optional<OrganizationEntity> findById(Long id) {
		// TODO Auto-generated method stub
		return organizationDao.findById(id);
	}

	@Override
	public List<OrganizationEntity> findByName(String name) {
		System.out.println("Pozvan findByName metod");
		return organizationDao.findByName(name);
	}

	@Override
	public OrganizationEntity update(OrganizationEntity organization) throws RuntimeException {
			
		Optional<OrganizationEntity> orgEntity = organizationDao.findById(organization.getOrganizationId());
		
		if(!orgEntity.isPresent()) {
			throw new RuntimeException("Organization does not exist:" + organization.getOrganizationCode());
		}
		
		OrganizationEntity organizationUpdated = organizationDao.save(organization);
		
		return organizationUpdated;
	}

	@Override
	public OrganizationEntity save(OrganizationEntity organization) {
		System.out.println("Pozvan service orgSave ");
		return organizationDao.save(organization);
	}

	@Override
	public void deleteById(Long id) {
		
		
		System.out.println("Pozvan deleteOrgById");
		
		//examinationSrvice.setOrganizationNull();
		examinationService.deleteByOrganization(organizationDao.findById(id).get());
		medicService.setOrganizationNull(organizationDao.findById(id).get());
		patientService.setOrganizationNull(organizationDao.findById(id).get());
		
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
	
//	Optional<CityEntity> entity = cityDao.findById(cityDto.getZipCode());
//	if (!entity.isPresent()) {
//		throw new RuntimeException("City does not exist:" + cityDto.getZipCode());
//	}
//	CityEntity c = cityDao.save(cityMapper.toEntity(cityDto));
//	return cityMapper.toDto(c);

}
