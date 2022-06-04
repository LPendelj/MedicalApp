package it.eng.lukapendelj.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import it.eng.lukapendelj.dao.ExaminationDAO;
import it.eng.lukapendelj.entity.ExaminationEntity;
import it.eng.lukapendelj.entity.OrganizationEntity;
import it.eng.lukapendelj.service.ExaminationService;

@Service
public class ExaminationServiceImpl implements ExaminationService {
	
	ExaminationDAO examinationDao;
	

	
	@Autowired
	public ExaminationServiceImpl(ExaminationDAO examinationDao) {
		super();
		this.examinationDao = examinationDao;
	}

	@Override
	public List<ExaminationEntity> findAll() {
		// TODO Auto-generated method stub
		return examinationDao.findAll();
	}
	
	
	@Override
	public Page<ExaminationEntity> findAll(Integer pageNo, Integer pageSize) {
		// TODO Auto-generated method stub
		Pageable pageable = PageRequest.of(pageNo, pageSize);
		
		return examinationDao.findAll(pageable);
	}

	@Override
	public Optional<ExaminationEntity> findById(Long id) {
		// TODO Auto-generated method stub
		return examinationDao.findById(id);
	}
	
	
	//THIS ONE!!
	
	@Override
	public List<ExaminationEntity> findByCode(String code) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ExaminationEntity update(ExaminationEntity examination) throws RuntimeException {
			
		Optional<ExaminationEntity> optExam = examinationDao.findById(examination.getExaminationId());
		
		if(optExam.isEmpty()) {
			throw new RuntimeException();
		}
		
		//CHECK THIS!
		
		return examinationDao.save(examination);
	}

	@Override
	public ExaminationEntity save(ExaminationEntity examination) {
		// TODO Auto-generated method stub
		return examinationDao.save(examination);
	}

	@Override
	public void deleteById(Long id) {
		
		examinationDao.deleteById(id);
	}

	@Override
	public void delete(ExaminationEntity examination) {
		// TODO Auto-generated method stub

	}
	
	@Override
	public void deleteByOrganization(OrganizationEntity organizationEntity) {
		List<ExaminationEntity> examinationList = examinationDao.findByOrganization(organizationEntity);
		
		System.out.println("Delete Examination by organization called!");
		
		examinationList.forEach(exam -> examinationDao.delete(exam));
// @formatter:on

	}

	
	

}
