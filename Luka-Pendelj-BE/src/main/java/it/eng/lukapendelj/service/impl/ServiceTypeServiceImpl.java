package it.eng.lukapendelj.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import it.eng.lukapendelj.dao.ServiceTypeDAO;
import it.eng.lukapendelj.entity.ServiceTypeEntity;
import it.eng.lukapendelj.service.ServiceTypeService;

@Service
public class ServiceTypeServiceImpl implements ServiceTypeService {

	ServiceTypeDAO serviceTypeDao;
	
	@Override
	public List<ServiceTypeEntity> findAll() {
		// TODO Auto-generated method stub
		return serviceTypeDao.findAll();
	}

}
