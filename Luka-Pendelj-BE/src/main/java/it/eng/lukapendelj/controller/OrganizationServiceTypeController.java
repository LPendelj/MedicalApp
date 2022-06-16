package it.eng.lukapendelj.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.eng.lukapendelj.entity.OrganizationTypeEntity;
import it.eng.lukapendelj.service.OrganizationTypeService;

@RestController
@RequestMapping("organizationtype")
public class OrganizationServiceTypeController {
	
	OrganizationTypeService organizationTypeService;
	
	@Autowired
	public OrganizationServiceTypeController(OrganizationTypeService organizationTypeService) {
		super();
		this.organizationTypeService = organizationTypeService;
	}
	
	@GetMapping
	public List<OrganizationTypeEntity> getAll(){
		
		return organizationTypeService.getAll();
		}
	
	
	

}
