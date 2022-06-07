package it.eng.lukapendelj.dao;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.query.Param;

import it.eng.lukapendelj.entity.OrganizationEntity;

@Transactional
public interface OrganizationDAO extends JpaRepository<OrganizationEntity, Long> {
	
	List<OrganizationEntity> findByNameContaining(String name); //1
	
	Page<OrganizationEntity> findByNameContaining(String name, Pageable p);
	
	List<OrganizationEntity> findByName(String name);
	
	List<OrganizationEntity> findByAddressContaining(String address); //2

	List<OrganizationEntity> findByOrganizationCodeContaining(String code); //3

}
