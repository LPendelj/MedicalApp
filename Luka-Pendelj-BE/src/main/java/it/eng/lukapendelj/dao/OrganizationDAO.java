package it.eng.lukapendelj.dao;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.query.Param;

import it.eng.lukapendelj.entity.OrganizationEntity;

@Transactional
public interface OrganizationDAO extends JpaRepository<OrganizationEntity, Long> {
	
	List<OrganizationEntity> findByName(String name);
	
	//Optional<OrganizationEntity> findByName( String name);

}
