package it.eng.lukapendelj.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import it.eng.lukapendelj.entity.MedicEntity;
import it.eng.lukapendelj.entity.PatientEntity;
import it.eng.lukapendelj.service.MedicService;
import it.eng.lukapendelj.service.impl.MedicServiceImpl;

//MEDICS are named PRACTITIONERS in the Frontend part of the App

//@CrossOrigin(origins = "*")
@RestController
@RequestMapping("practitioners")
public class MedicController {
	
	MedicService medicService;
	
	@Autowired
	public MedicController(MedicService medicService) {
		super();
		this.medicService = medicService;
	}
	
	@GetMapping
	public List<MedicEntity> findAll(){
		
		System.out.println("Pozvan MEDIC findAll metod");

		
		return medicService.findAll();
	}
	
	
	@GetMapping("filter")
	public ResponseEntity<Page<MedicEntity>> findAll(@RequestParam(defaultValue = "0")Integer pageNo, @RequestParam(defaultValue = "5")Integer pageSize){
		
		System.out.println("Pozvan Patient findAll metod");
		
		
		return ResponseEntity.status(HttpStatus.OK).body(medicService.findAll(pageNo, pageSize));
	}
	
	
	@GetMapping("{id}")
	public ResponseEntity<Object> findById(@PathVariable Long id){
		
		System.out.println("Pozvan findById metod");
		
		if(medicService.findById(id).isPresent()) {
			
			return ResponseEntity.ok(medicService.findById(id));
		}
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Invalid practitioner id!");
		
	}
	
	@GetMapping("name/{name}")
	public ResponseEntity<Object> findByName(@PathVariable String firstname, @PathVariable String lastname) {
		Optional<MedicEntity> medicEntity = Optional.of(medicService.findByName(firstname, lastname).get(0));
		
		System.out.println("Pozvan findByFirstLastName metod");

		
		if(medicEntity.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Invalid medic name!");
		} return ResponseEntity.status(HttpStatus.OK).body(medicEntity.get());
		
	}
	
	@PostMapping("save")
	public ResponseEntity<Object> save(@Valid @RequestBody MedicEntity medicEntity) {
		System.out.println(medicEntity);
		try {
			return ResponseEntity.status(HttpStatus.OK).body(medicService.save(medicEntity));
		} catch (Exception ex) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
		}
	}
	
	@PutMapping("{id}")
	public @ResponseBody ResponseEntity<Object> update(@PathVariable Long id, @Valid @RequestBody MedicEntity medicEntity) {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(medicService.update(medicEntity));
		} catch (RuntimeException ex) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
		}
	}
	
	@DeleteMapping("delete/{id}")
	@ResponseBody
	public ResponseEntity<Object> delete(@PathVariable Long id){
		try {
			System.out.println("Pozvan MEDIC delete metod");
			medicService.deleteById(id);
			return ResponseEntity.status(HttpStatus.OK).build();
		} catch (Exception ex) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
		}
	}
	
	////////////////////////////////////////////////////////////////////////////////////////////
	
	@GetMapping("organization/{id}")
	public ResponseEntity<Object> findByOrgId(@PathVariable Long id){
		
		System.out.println("Pozvan MEDIC findBy Organization Id metod");
		System.out.println(id);
//		if(medicService.findByOrganization(id).isEmpty()) {
//			
//			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No Practitioners in looke");
//			
//		}
			
		System.out.println("Broj zaposlenih je " + medicService.findByOrganization(id).size());
			return ResponseEntity.ok(medicService.findByOrganization(id));
		
	
	}
	

}


