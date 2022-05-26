package it.eng.lukapendelj.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import it.eng.lukapendelj.entity.ExaminationEntity;

public interface ExaminationDAO extends JpaRepository<ExaminationEntity, Long> {

}
