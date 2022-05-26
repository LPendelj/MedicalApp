package it.eng.lukapendelj.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import it.eng.lukapendelj.entity.PatientEntity;

public interface PatientDAO extends JpaRepository<PatientEntity, Long> {

}
