package it.eng.lukapendelj.dao;

import org.springframework.data.jpa.repository.JpaRepository;


import it.eng.lukapendelj.entity.Gender;

public interface GenderDAO extends JpaRepository<Gender, Character> {

}
