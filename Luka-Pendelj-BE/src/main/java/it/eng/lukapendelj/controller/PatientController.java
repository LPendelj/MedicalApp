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
		
		@GetMapping("filter")
		public ResponseEntity<Page<PatientEntity>> findAll(@RequestParam(defaultValue = "0")Integer pageNo, @RequestParam(defaultValue = "5")Integer pageSize){
			
			System.out.println("Pozvan Patient findAll metod");
			
			
			return ResponseEntity.status(HttpStatus.OK).body(patientService.findAll(pageNo, pageSize));
		}
		
		
		
		
		@GetMapping("{id}")
		public ResponseEntity<Object> findById(@PathVariable Long id) {
			Optional<PatientEntity> patientEntity = patientService.findById(id);
			
			System.out.println("Pozvan findById metod");

			
			if(patientEntity.isEmpty()) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Invalid patient id!");
			} return ResponseEntity.status(HttpStatus.OK).body(patientEntity.get());
			
		}
		
		
		
		@PostMapping("save")
		public ResponseEntity<Object> save(@RequestBody PatientEntity patEntity) {
			System.out.println("Pozvan save Patient");
			try {
				return ResponseEntity.status(HttpStatus.OK).body(patientService.save(patEntity));
			} catch (RuntimeException rex) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(rex.getMessage());
			} 
			catch (Exception ex) {
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
				return ResponseEntity.ok().build();
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
		
		////////////////////////// FILTER //////////////////////////
		
		@PostMapping("filter")
		public ResponseEntity<Object> findByFilter(@RequestBody String[] values){
			//List<PatientEntity> patientsList = patientService.findByOrganizationName(term);
				System.out.println(values[0]+ " " + values[1]);
				try {
				List<PatientEntity> patientsList = null;
				
				switch(values[0]) {
				case "organization":  patientsList = patientService.findByOrganizationName(values[1]);
				break;
				case "name": patientsList = patientService.findByName(values[1]);
				break;
				case "code": patientsList = patientService.findByPatientCode(values[1]);
				break;
				default:
					return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
					}
				return ResponseEntity.status(HttpStatus.OK).body(patientsList);
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
