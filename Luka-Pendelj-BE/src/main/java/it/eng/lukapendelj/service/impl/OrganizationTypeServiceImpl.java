package it.eng.lukapendelj.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.eng.lukapendelj.dao.OrganizationTypeDAO;
import it.eng.lukapendelj.entity.OrganizationTypeEntity;
import it.eng.lukapendelj.service.OrganizationTypeService;

@Service
public class OrganizationTypeServiceImpl implements OrganizationTypeService {
	
	OrganizationTypeDAO organizationTypeDao;
	
	
	@Autowired
	public OrganizationTypeServiceImpl(OrganizationTypeDAO organizationTypeDao) {
		super();
		this.organizationTypeDao = organizationTypeDao;
	}






	@Override
	public List<OrganizationTypeEntity> getAll() {
		// TODO Auto-generated method stub
		return organizationTypeDao.findAll();
	}

}
