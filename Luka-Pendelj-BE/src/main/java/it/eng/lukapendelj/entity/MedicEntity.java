package it.eng.lukapendelj.entity;

import java.util.Date;
import java.util.Objects;
import java.util.UUID;

public class MedicEntity {
	
	private Long MedicId;
	private final String MedicCode = "MED-"+UUID.randomUUID().toString().substring(0, 8);
	private Boolean active; //req
	private String firstname; //req
	private String lastname; //req
	private Gender gender; 
	private Date birthDate; //req
	private String address;
	private String phone;
	private String email;
	private String qualification; //req
	
	
	public Long getMedicId() {
		return MedicId;
	}
	public void setMedicId(Long medicId) {
		MedicId = medicId;
	}
	public String getMedicCode() {
		return MedicCode;
	}
//	public void setMedicCode(String medicCode) {
//		MedicCode = medicCode;
//	}
	
	
	
	public String getFirstname() {
		return firstname;
	}
	public Boolean getActive() {
		return active;
	}
	public void setActive(Boolean active) {
		this.active = active;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public Gender getGender() {
		return gender;
	}
	public void setGender(Gender gender) {
		this.gender = gender;
	}
	public Date getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	
	
	public MedicEntity(Long medicId, String medicCode, String firstname, String lastname, Gender gender, Date birthDate,
			String address, String email, String phone, String qualification) {
		super();
		this.MedicId = medicId;
		//MedicCode = medicCode;
		this.firstname = firstname;
		this.lastname = lastname;
		this.gender = gender;
		this.birthDate = birthDate;
		this.address = address;
		this.email = email;
		this.phone = phone;
		this.qualification = qualification;
	}
	@Override
	public int hashCode() {
		return Objects.hash(MedicCode, MedicId, address, birthDate, phone, email, firstname, phone, gender, lastname,
				qualification);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MedicEntity other = (MedicEntity) obj;
		return Objects.equals(MedicCode, other.MedicCode) && Objects.equals(MedicId, other.MedicId)
				&& Objects.equals(address, other.address) && Objects.equals(birthDate, other.birthDate)
				&& Objects.equals(phone, other.phone) && Objects.equals(email, other.email)
				&& Objects.equals(firstname, other.firstname) && Objects.equals(gender, other.gender)
				&& Objects.equals(lastname, other.lastname) && Objects.equals(qualification, other.qualification);
	}
	@Override
	public String toString() {
		return "MedicCode=" + MedicCode + ", firstname=" + firstname
				+ ", lastname=" + lastname + ", gender=" + gender + ", birthDate=" + birthDate + ", address=" + address
				+ ", email=" + email + ", phone=" + phone + ", qualification=" + qualification + "]";
	}
	
	
	
	
	
	
	
	
	
	//String uniqueID = "MED-"+UUID.randomUUID().toString();
}
