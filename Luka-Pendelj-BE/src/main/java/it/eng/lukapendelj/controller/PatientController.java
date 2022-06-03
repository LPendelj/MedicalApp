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

import it.eng.lukapendelj.entity.PatientEntity;
import it.eng.lukapendelj.service.PatientService;

//@CrossOrigin(origins = "*")
@RestController
@RequestMapping("patients")
public class PatientController {
	
	PatientService patientService;
	
	@Autowired
	public PatientController(PatientService patientService) {
		super();
		this.patientService = patientService;
	}
	
	
	
		@GetMapping
		public List<PatientEntity> findAll(){
			
			System.out.println("Pozvan Patient findAll metod");

			
			return patientService.findAll();
		}
		
		
		@GetMapping("{id}")
		public ResponseEntity<Object> findById(@PathVariable Long id) {
			Optional<PatientEntity> patientEntity = patientService.findById(id);
			
			System.out.println("Pozvan findById metod");

			
			if(patientEntity.isEmpty()) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Invalid patient id!");
			} return ResponseEntity.status(HttpStatus.OK).body(patientEntity.get());
			
		}
		
		@GetMapping("name/{name}")
		public ResponseEntity<Object> findByName(@PathVariable String name) {
			Optional<PatientEntity> patientEntity = Optional.of(patientService.findByName(name).get(0));
			
			System.out.println("Pozvan findByName metod");

			
			if(patientEntity.isEmpty()) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Invalid patient name!");
			} return ResponseEntity.status(HttpStatus.OK).body(patientEntity.get());
			
		}
		
		@PostMapping("save")
		public ResponseEntity<Object> save(@RequestBody PatientEntity patEntity) {
			System.out.println("Pozvan save Patient");
			try {
				return ResponseEntity.status(HttpStatus.OK).body(patientService.save(patEntity));
			} catch (Exception ex) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
			}
		}
		
		@PutMapping("{id}")
		public @ResponseBody ResponseEntity<Object> update(@PathVariable Long id, @Valid @RequestBody PatientEntity patEntity) {
			try {
				return ResponseEntity.status(HttpStatus.OK).body(patientService.update(patEntity));
			} catch (RuntimeException ex) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
			}
		}
		
		@DeleteMapping("delete/{id}")
		public ResponseEntity<Object> delete(@PathVariable Long id){
			try {
				System.out.println("Pozvan PATIENT delete metod");
				patientService.deleteById(id);
				return ResponseEntity.ok("Patient with code " + id + ": DELETED");
			} catch (Exception ex) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
			}
		}

		/////////////////////////////////////////////////////////////////////
		
		@GetMapping("organization/{id}")
		public ResponseEntity<Object> findByOrganization(@PathVariable Long id){
			
			
			return ResponseEntity.status(HttpStatus.OK).body(patientService.findByOrganization(id));
		}
		
		@GetMapping("organizationName/{name}")
		public ResponseEntity<Object> findByOrganizationName(@PathVariable String name){
			List<PatientEntity> patientsList = patientService.findByOrganizationName(name);
			System.out.println("From controller " + patientsList);
			return ResponseEntity.status(HttpStatus.OK).body(patientsList);
		}
	
	
}
