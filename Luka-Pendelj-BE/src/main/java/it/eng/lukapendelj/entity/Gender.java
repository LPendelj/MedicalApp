package it.eng.lukapendelj.entity;


import javax.persistence.Entity;
import javax.persistence.Id;
//import javax.persistence.UniqueConstraint;

@Entity
public class Gender {
	
	@Id
	private char genderCode;
	private String genderName;
	
	
	public Gender(char genderCode, String genderName) {
		super();
		this.genderCode = genderCode;
		this.genderName = genderName;
	}
	
	public Gender() {
		super();
	}


	public char getGenderCode() {
		return genderCode;
	}


	public void setGenderCode(char genderCode) {
		this.genderCode = genderCode;
	}


	public String getGenderName() {
		return genderName;
	}


	public void setGenderName(String genderName) {
		this.genderName = genderName;
	}
	
	
	
	
	
	
	
	
	
}
