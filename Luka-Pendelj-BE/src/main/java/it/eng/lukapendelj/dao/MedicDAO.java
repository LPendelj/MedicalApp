package it.eng.lukapendelj.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import it.eng.lukapendelj.entity.MedicEntity;

public interface MedicDAO extends JpaRepository<MedicEntity, Long> {

}
