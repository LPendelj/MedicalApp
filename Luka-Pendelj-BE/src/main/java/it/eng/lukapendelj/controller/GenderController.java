package it.eng.lukapendelj.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.eng.lukapendelj.entity.Gender;
import it.eng.lukapendelj.service.GenderService;

@RestController
@RequestMapping("genders")
public class GenderController {
	
	@Autowired
	GenderService genderService;
	
	@GetMapping()
	public List<Gender> findAll(){
		return genderService.findAll();
		
	}
	
}
