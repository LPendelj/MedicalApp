package it.eng.lukapendelj.dao;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import it.eng.lukapendelj.entity.ServiceTypeEntity;

@Transactional
public interface ServiceTypeDAO extends JpaRepository<ServiceTypeEntity, Long>{

}
