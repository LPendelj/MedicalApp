package it.eng.lukapendelj.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import it.eng.lukapendelj.entity.OrganizationTypeEntity;

public interface OrganizationTypeDAO extends JpaRepository<OrganizationTypeEntity, Long> {

}
