package it.eng.lukapendelj.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

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

import it.eng.lukapendelj.entity.ExaminationEntity;
import it.eng.lukapendelj.service.ExaminationService;

//@CrossOrigin(origins = "*")
@RestController
@RequestMapping("examinations")
public class ExaminationController {
	
	
	//Check!!
	ExaminationService examinationService;

	public ExaminationController(ExaminationService examinationService) {
		super();
		this.examinationService = examinationService;
	}
	
	//ResponseEntity<Object> - CHECK THIS!
	
	@GetMapping()
	public List<ExaminationEntity> findAll(){
		

		System.out.println("Pozvan findAll EXAM metod");

		
		return examinationService.findAll();
		
	}
	
	@GetMapping("{id}")
	public ResponseEntity<Object> findById(@PathVariable Long id) {
		Optional<ExaminationEntity> examEntity = (examinationService.findById(id)); //TO BE IMPLEMENTED!
		
		System.out.println("Pozvan findById metod");

		
		if(examEntity.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Invalid examination id!");
		} return ResponseEntity.status(HttpStatus.OK).body(examEntity.get());
		
	}
	
	@GetMapping("code/{code}")
	public ResponseEntity<Object> findByCode(@PathVariable String code) {
		Optional<ExaminationEntity> examEntity = Optional.of(examinationService.findByCode(code).get(0)); //TO BE IMPLEMENTED!
		
		System.out.println("Pozvan findByCODE metod");

		
		if(examEntity.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Invalid examination code!");
		} return ResponseEntity.status(HttpStatus.OK).body(examEntity.get());
		
	}
	
	@PostMapping("save")
	public ResponseEntity<Object> save( @RequestBody ExaminationEntity examEntity) {
		
		System.out.println(examEntity);
		try {
			return ResponseEntity.status(HttpStatus.OK).body(examinationService.save(examEntity));
		} catch (Exception ex) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
		}
	}
	
	@PutMapping("{id}")
	public @ResponseBody ResponseEntity<Object> update(@PathVariable Long id, @Valid @RequestBody ExaminationEntity examEntity) {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(examinationService.update(examEntity));
		} catch (RuntimeException ex) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
		}
	}
	
	@DeleteMapping("delete/{id}")
	public ResponseEntity<Object> delete(@PathVariable Long id){
		try {
			System.out.println("Pozvan delete EXAMINATION metod");
			examinationService.deleteById(id);
			return ResponseEntity.status(HttpStatus.OK).build();
		} catch (Exception ex) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
		}
	}
	
	
	
	

}

/*	
	
	@GetMapping("{code}")
	public ResponseEntity<Object> findByCode(@PathVariable String code) {
		Optional<OrganizationEntity> examEntity = Optional.of(examinationService.findBycode(code).get(0));
		
		System.out.println("Pozvan findByCODE metod");

		
		if(orgEntity.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Invalid examination code!");
		} return ResponseEntity.status(HttpStatus.OK).body(examEntity.get());
		
	}
	
	@PostMapping("save")
	public ResponseEntity<Object> save(@Valid @RequestBody ExamEntity examEntity) {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(examinationService.save(orgEntity));
		} catch (Exception ex) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
		}
	}
	
	@PutMapping("{id}")
	public @ResponseBody ResponseEntity<Object> update(@PathVariable Long id, @Valid @RequestBody ExaminationEntity examEntity) {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(organizationService.update(examEntity));
		} catch (RuntimeException ex) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
		}
	}
	
	@DeleteMapping("delete/{id}")
	public ResponseEntity<Object> delete(@PathVariable Long id){
		try {
			System.out.println("Pozvan delete EXAMINATION metod");
			examinationService.deleteById(id);
			return ResponseEntity.ok("Examination with id " + id + ": DELETED");
		} catch (Exception ex) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
		}
	}
	*/
