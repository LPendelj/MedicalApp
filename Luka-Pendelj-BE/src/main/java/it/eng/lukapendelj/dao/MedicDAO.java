package it.eng.lukapendelj.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
//import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import it.eng.lukapendelj.entity.MedicEntity;
import it.eng.lukapendelj.entity.OrganizationEntity;


@Transactional
public interface MedicDAO extends JpaRepository<MedicEntity, Long> {
	
	List<MedicEntity> findByFirstnameAndLastname(String firstname, String lastname);
	
	List<MedicEntity> findByOrganization(OrganizationEntity organization);
	
	//List<MedicEntity> findByOrganization(Long organizationId);
	
	//void setOrganizationToNull();
//	@Modifying
//	@Query("update medic m where m.organization =  ?1")
//	int updateMedicSetOrganizationForOrganization(@Param("organization") Long organization);
	
	
	
	
	
}
