package it.eng.lukapendelj.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import it.eng.lukapendelj.entity.OrganizationEntity;

@Primary
public interface OrganizationDAO extends JpaRepository<OrganizationEntity, Long> {
	
	List<OrganizationEntity> findByName(String name);
	
	//Optional<OrganizationEntity> findByName( String name);

}
