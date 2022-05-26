package it.eng.lukapendelj.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import it.eng.lukapendelj.dao.OrganizationDAO;
//import it.eng.lukapendelj.dao.impl.OrganizationDaoImpl;
import it.eng.lukapendelj.entity.OrganizationEntity;
import it.eng.lukapendelj.service.OrganizationService;


@Service
public class OrganizationServiceImpl implements OrganizationService {
	
	
	OrganizationDAO organizationDao;
	
	@Autowired
	public OrganizationServiceImpl(OrganizationDAO organizationDao) {
		super();
		this.organizationDao = organizationDao;
	}

	@Override
	public List<OrganizationEntity> findAll() {
		List<OrganizationEntity> organizationEntity = organizationDao.findAll();
		
		System.out.println(organizationEntity);
		
		return organizationEntity;
	}

	@Override
	public Optional<OrganizationEntity> findById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<OrganizationEntity> findByName(String name) {
		System.out.println("Pozvan findByName metod");
		return organizationDao.findByName(name);
	}

	@Override
	public OrganizationEntity update(OrganizationEntity organization) throws RuntimeException {
			
		Optional<OrganizationEntity> orgEntity = organizationDao.findById(organization.getOrganizationID());
		
		if(!orgEntity.isPresent()) {
			throw new RuntimeException("City does not exist:" + organization.getOrganizationCode());
		}
		
		OrganizationEntity organizationUpdated = organizationDao.save(organization);
		
		return organizationUpdated;
	}

	@Override
	public OrganizationEntity save(OrganizationEntity organization) {
		// TODO Auto-generated method stub
		return organizationDao.save(organization);
	}

	@Override
	public void deleteById(Long id) {
		// TODO Auto-generated method stub

	}

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
