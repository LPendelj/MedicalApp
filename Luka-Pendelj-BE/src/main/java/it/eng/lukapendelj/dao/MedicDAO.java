package it.eng.lukapendelj.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import it.eng.lukapendelj.entity.MedicEntity;
import it.eng.lukapendelj.entity.OrganizationEntity;


public interface MedicDAO extends JpaRepository<MedicEntity, Long> {
	
	List<MedicEntity> findByFirstnameAndLastname(String firstname, String lastname);
	
	List<MedicEntity> findByOrganization(OrganizationEntity organization);
	
	//void setOrganizationToNull();
	
	
	
	
	
}
