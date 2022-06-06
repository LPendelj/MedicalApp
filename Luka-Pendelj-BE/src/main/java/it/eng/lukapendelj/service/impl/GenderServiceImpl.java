package it.eng.lukapendelj.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.eng.lukapendelj.dao.GenderDAO;
import it.eng.lukapendelj.entity.Gender;
import it.eng.lukapendelj.service.GenderService;

@Service
public class GenderServiceImpl implements GenderService {
	
	@Autowired
	GenderDAO genderDao;
	
	@Override
	public Optional<Gender> findById(Character id) {
		// TODO Auto-generated method stub
		return genderDao.findById(id);
	}

	@Override
	public List<Gender> findAll() {
		// TODO Auto-generated method stub
		return genderDao.findAll();
	}
	
	

}
