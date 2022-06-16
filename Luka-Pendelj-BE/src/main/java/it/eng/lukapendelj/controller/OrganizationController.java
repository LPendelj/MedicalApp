package it.eng.lukapendelj.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import it.eng.lukapendelj.entity.OrganizationEntity;


import it.eng.lukapendelj.service.impl.OrganizationServiceImpl;



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
	
	
	
	@GetMapping("filter")
	public ResponseEntity<Page<OrganizationEntity>> findAll(@RequestParam(defaultValue = "0")Integer pageNo, @RequestParam(defaultValue = "5")Integer pageSize){
		
		System.out.println("Pozvan Patient findAll metod");
		
		
		return ResponseEntity.status(HttpStatus.OK).body(organizationService.findAll(pageNo, pageSize));
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
		} catch (RuntimeException rex) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(rex.getMessage());
		} 
		catch (Exception ex) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
		} 
	}
	
	@PutMapping("{id}")
	public @ResponseBody ResponseEntity<Object> update(@PathVariable @Valid Long id,  @RequestBody OrganizationEntity orgEntity) {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(organizationService.update(orgEntity));
		} catch (RuntimeException ex) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
		}
	}
	
	@DeleteMapping("delete/{id}")
	public ResponseEntity<Object> delete(@PathVariable @Valid Long id){
		try {
			System.out.println("Pozvan delete metod");
			organizationService.deleteById(id);
			return ResponseEntity.ok().build();
		} catch (Exception ex) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
		}
	}
	
	///////////////////// FILTER REQUEST ////////////////
	
	@PostMapping("filter")
	public ResponseEntity<Object> findByFilter(@RequestBody String[] values){
		//List<PatientEntity> patientsList = patientService.findByOrganizationName(term);
			System.out.println(values[0]+ " " + values[1]);
			try {
			List<OrganizationEntity> organizationsList = null;
			
			switch(values[0]) {
			case "name":  organizationsList = organizationService.findByName(values[1]);
			break;
			case "address": organizationsList = organizationService.findByAddress(values[1]);
			break;
			case "code": organizationsList = organizationService.findByOrganizationCode(values[1]);
			break;
			default:
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
				}
			return ResponseEntity.status(HttpStatus.OK).body(organizationsList);
			} catch(Exception ex){
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
			}
		
		
	}
	
	//////////////////////////// Exception Handler ////////////////////////
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Map<String, String> handleValidationErrors(MethodArgumentNotValidException ex) {
		Map<String, String> errors = new HashMap<>();
		ex.getBindingResult().getAllErrors().forEach((error) -> {
		String fieldName = ((FieldError) error).getField();
		String errorMessage = error.getDefaultMessage();
		errors.put(fieldName, errorMessage);
		});
		return errors;
	}

}



