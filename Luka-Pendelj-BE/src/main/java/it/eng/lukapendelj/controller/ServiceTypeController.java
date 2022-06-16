package it.eng.lukapendelj.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.eng.lukapendelj.entity.ServiceTypeEntity;
import it.eng.lukapendelj.service.ServiceTypeService;

@RestController
@RequestMapping("servicetype")
public class ServiceTypeController {
	
	@Autowired
	ServiceTypeService serviceTypeService;
	
	@GetMapping
	public List<ServiceTypeEntity> findAll(){
		
		return serviceTypeService.findAll();
	}

}
