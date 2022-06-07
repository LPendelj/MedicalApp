package it.eng.lukapendelj.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

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

import it.eng.lukapendelj.entity.ExaminationEntity;

import it.eng.lukapendelj.service.ExaminationService;


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
	
	
	@GetMapping("filter")
	public ResponseEntity<Page<ExaminationEntity>> findAll(@RequestParam(defaultValue = "0")Integer pageNo, @RequestParam(defaultValue = "5")Integer pageSize){
		
		System.out.println("Pozvan Patient findAll metod");
		
		
		return ResponseEntity.status(HttpStatus.OK).body(examinationService.findAll(pageNo, pageSize));
	}
	
	@GetMapping("{id}")
	public ResponseEntity<Object> findById(@PathVariable Long id) {
		Optional<ExaminationEntity> examEntity = (examinationService.findById(id)); //TO BE IMPLEMENTED!
		
		System.out.println("Pozvan findById metod");

		
		if(examEntity.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Invalid examination id!");
		} return ResponseEntity.status(HttpStatus.OK).body(examEntity.get());
		
	}
	
//	@GetMapping("code/{code}")
//	public ResponseEntity<Object> findByCode(@PathVariable String code) {
//		Optional<ExaminationEntity> examEntity = Optional.of(examinationService.findByCode(code).get(0)); //TO BE IMPLEMENTED!
//		
//		System.out.println("Pozvan findByCODE metod");
//
//		
//		if(examEntity.isEmpty()) {
//			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Invalid examination code!");
//		} return ResponseEntity.status(HttpStatus.OK).body(examEntity.get());
//		
//	}
	
	@PostMapping("save")
	public ResponseEntity<Object> save( @RequestBody ExaminationEntity examEntity) {
		System.out.println("Pozvan examination-controller");
		//System.out.println(examEntity);
		try {
			
			return ResponseEntity.status(HttpStatus.OK).body(examinationService.save(examEntity));
		} catch (Exception rex) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(rex.getMessage());
		} 
		
	
	}
	
	@PutMapping("{id}")
	public @ResponseBody ResponseEntity<Object> update(@Valid @PathVariable Long id, @Valid @RequestBody ExaminationEntity examEntity) {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(examinationService.update(examEntity));
		} catch (RuntimeException ex) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
		}
	}
	
	@DeleteMapping("delete/{id}")
	public ResponseEntity<Object> delete(@Valid @PathVariable Long id){
		try {
			System.out.println("Pozvan delete EXAMINATION metod");
			examinationService.deleteById(id);
			return ResponseEntity.status(HttpStatus.OK).build();
		} catch (Exception ex) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
		}
	}
	
	
	//////////////////////////// FILTER //////////////////////////
	/**
	 * Filter method recieves string of values[filterName, filterValue]
	 * switching value of filter name choose which find method is called
	 * @param values
	 * @return
	 */
	@PostMapping("filter")
	public ResponseEntity<Object> findByFilter(@RequestBody String[] values){
		//List<PatientEntity> patientsList = patientService.findByOrganizationName(term);
			System.out.println(values[0]+ " " + values[1]);
			try {
			List<ExaminationEntity> examinationsList = null;
			
			switch(values[0]) {
			case "organization":  examinationsList = examinationService.findByOrganizationName(values[1]);
			break;
			case "priority":  examinationsList = examinationService.findByPriority(values[1]);
			break;
			case "status": examinationsList = examinationService.findByStatus(values[1]);
			break;
			case "code": examinationsList = examinationService.findByExaminationCode(values[1]);
			break;
			default:
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
				}
			return ResponseEntity.status(HttpStatus.OK).body(examinationsList);
			} catch(Exception ex){
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
			}
		
		
	}
	
	///////////////// Exception Handler ///////////////////
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

