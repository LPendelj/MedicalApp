package it.eng.lukapendelj.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.eng.lukapendelj.dao.MedicDAO;
import it.eng.lukapendelj.entity.MedicEntity;
import it.eng.lukapendelj.entity.OrganizationEntity;
import it.eng.lukapendelj.service.MedicService;

@Service
public class MedicServiceImpl implements MedicService {
	
	
	MedicDAO medicDao;
	
	
	
	@Autowired
	public MedicServiceImpl(MedicDAO medicDao) {
		super();
		this.medicDao = medicDao;
	}

	@Override
	public List<MedicEntity> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<MedicEntity> findById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<MedicEntity> findByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MedicEntity update(MedicEntity medic) throws RuntimeException {
		
		Optional<MedicEntity> medicEntity = medicDao.findById(medic.getMedicId());
		if(!medicEntity.isPresent()) {
			throw new RuntimeException("Medic does not exist:" + medic.getMedicCode());
		}
		
		MedicEntity medicUpdated = medicDao.save(medic);
		
		return medicUpdated;
	}

	@Override
	public MedicEntity save(MedicEntity medic) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteById(Long id) {
		// TODO Auto-generated method stub

	}
	
	@Override
	public void setOrganizationNull(OrganizationEntity organizationInactive) {
		List<MedicEntity> medicList = medicDao.findByOrganization(organizationInactive);
		
		System.out.println("Pozvan setOrgNull");
		
		medicList.forEach(medicItem -> {
			medicItem.setOrganization(null);
			//System.out.println("organizacije za brisanje: " + medicItem.getOrganization().getName());
			medicDao.save(medicItem);
		}
		);
		
	}

}
