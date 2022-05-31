package it.eng.lukapendelj.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import it.eng.lukapendelj.entity.OrganizationEntity;
//import it.eng.lukapendelj.service.OrganizationService;
import it.eng.lukapendelj.service.impl.OrganizationServiceImpl;
//import it.engineering.app.dto.CityDto;



//@CrossOrigin(origins = "*")
@RestController
@RequestMapping("organizations")
public class OrganizationController {

	private OrganizationServiceImpl organizationService;
	
	@Autowired
	public OrganizationController(OrganizationServiceImpl organizationService) {
		super();
		this.organizationService = organizationService;
	}
	
	@GetMapping
	public List<OrganizationEntity> findAll(){
		
		System.out.println("Pozvan findAll metod");

		
		return organizationService.findAll();
	}
	
	@GetMapping("{id}")
	public ResponseEntity<Object> findById(@PathVariable Long id){
		
		System.out.println("Pozvan findById metod");
		
		if(organizationService.findById(id).isPresent()) {
			return ResponseEntity.ok(organizationService.findById(id));
		}
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Invalid organization id!");
		
	}
	
	
	@GetMapping("name={name}")
	public ResponseEntity<Object> findByName(@PathVariable String name) {
		Optional<OrganizationEntity> orgEntity = Optional.of(organizationService.findByName(name).get(0));
		
		System.out.println("Pozvan findByName metod");

		
		if(orgEntity.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Invalid organization name!");
		} return ResponseEntity.status(HttpStatus.OK).body(orgEntity.get());
		
	}
	
	@PostMapping("save")
	public ResponseEntity<Object> save( @RequestBody OrganizationEntity orgEntity) {
		System.out.println(orgEntity);
		try {
			return ResponseEntity.status(HttpStatus.OK).body(organizationService.save(orgEntity));
		} catch (Exception ex) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
		}
	}
	
	@PutMapping("{id}")
	public @ResponseBody ResponseEntity<Object> update(@PathVariable Long id, @Valid @RequestBody OrganizationEntity orgEntity) {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(organizationService.update(orgEntity));
		} catch (RuntimeException ex) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
		}
	}
	
	@DeleteMapping("delete/{id}")
	public ResponseEntity<Object> delete(@PathVariable Long id){
		try {
			System.out.println("Pozvan delete metod");
			organizationService.deleteById(id);
			return ResponseEntity.ok("Organization with id " + id + ": DELETED");
		} catch (Exception ex) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
		}
	}
	
	

}



