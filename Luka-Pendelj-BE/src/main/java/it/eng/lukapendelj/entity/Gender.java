package it.eng.lukapendelj.entity;


import java.util.Objects;

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

	@Override
	public String toString() {
		return "Gender [genderCode=" + genderCode + ", genderName=" + genderName + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(genderCode, genderName);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Gender other = (Gender) obj;
		return genderCode == other.genderCode && Objects.equals(genderName, other.genderName);
	}
	
	
	
	
	
	
	
	
	
}
