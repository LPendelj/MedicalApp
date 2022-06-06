package it.eng.lukapendelj.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.eng.lukapendelj.entity.ServiceTypeEntity;
import it.eng.lukapendelj.service.ServiceTypeService;

@RestController
@RequestMapping("services")
public class ServiceTypeController {
	
	ServiceTypeService serviceTypeService;
	
	@GetMapping
	public List<ServiceTypeEntity> findAll(){
		
		return serviceTypeService.findAll();
	}

}
