package it.eng.lukapendelj.service.impl;


import java.util.ArrayList;
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
	
	HelperServiceClass helperService;

	
	@Autowired
	public ExaminationServiceImpl(ExaminationDAO examinationDao, HelperServiceClass helperService) {
		super();
		this.examinationDao = examinationDao;
		this.helperService = helperService;
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
			throw new RuntimeException("Examination does not exist!");
		}
		
//		if(!examinationDao.findByExaminationCode(examination.getExaminationCode()).isEmpty()) {
//			throw new RuntimeException("Examination code is not unique!");
//		}
//		
	
		
		return examinationDao.save(examination);
	}

	@Override
	public ExaminationEntity save(ExaminationEntity examination) {
		
		//System.out.println(examination);
		System.out.println("Pozvan examination-service");
		
		if(!examinationDao.findByExaminationCode(examination.getExaminationCode()).isEmpty()) {
			System.err.println("Code duplicated!");
			System.out.println(examination.getExaminationCode());
			throw new RuntimeException();
		}
		
		ExaminationEntity optExam = examinationDao.save(examination);
		//System.out.println(optExam);
		return optExam;
		

		 
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
		
		
		
		examinationList.forEach(exam -> examinationDao.delete(exam));


	}

	@Override
	public List<ExaminationEntity> findByOrganizationName(String name) {
		// TODO Auto-generated method stub
		List<OrganizationEntity> listOrganization = helperService.getOrganizationDao().findByNameContaining(name);
		
		List<ExaminationEntity> examinationList = new ArrayList<>();
	
		
		for(OrganizationEntity org: listOrganization) {
			System.out.print(org.getOrganizationId() + ": ");
			examinationList.addAll(examinationDao.findByOrganization(org));
		}
		
		
		return examinationList;
	}
	
	/**
	 * Filter examination method
	 */
	@Override
	public List<ExaminationEntity> findByPriority(String priority) {
		// TODO Auto-generated method stub
		return examinationDao.findByPriorityContaining(priority);
	}
	
	/**
	 * Filter examination method
	 */
	@Override
	public List<ExaminationEntity> findByStatus(String status) {
		// TODO Auto-generated method stub
		return examinationDao.findByStatusContaining(status);
	}

	/**
	 * Filter examination method
	 */
	@Override
	public List<ExaminationEntity> findByExaminationCode(String code) {
		// TODO Auto-generated method stub
		return examinationDao.findByExaminationCodeContaining(code);
	}

	
	

}
