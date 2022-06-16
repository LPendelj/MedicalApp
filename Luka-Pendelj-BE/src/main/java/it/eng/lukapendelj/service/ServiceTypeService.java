package it.eng.lukapendelj.service;

import java.util.List;

import it.eng.lukapendelj.entity.ServiceTypeEntity;

public interface ServiceTypeService {
	
	List<ServiceTypeEntity> findAll();

}
