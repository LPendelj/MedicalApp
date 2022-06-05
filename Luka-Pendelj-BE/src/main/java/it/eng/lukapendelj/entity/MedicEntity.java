package it.eng.lukapendelj.entity;

import java.util.Date;
import java.util.Objects;
//import java.util.UUID;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
//import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.Nulls;

//Changed the name of this Entity from Practitioner to Medic for its clarity and length. UI Entity name will be as requested (Practitioner)


@Entity
@Table(name="Medic", uniqueConstraints = @UniqueConstraint(columnNames = {"medicCode" }))
@SQLDelete(sql = "UPDATE medic SET active = false WHERE medic_id=?")
@Where(clause = "active=true")
public class MedicEntity {
	

	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long medicId;
	private String medicCode;
	@NotNull
	private Boolean active = true; //req
	@NotNull
	private String firstname; //req
	@NotNull
	private String lastname; //req
	
	@JoinColumn(name="genderCode")
	@OneToOne
	private Gender gender; 
	@NotNull
	@Temporal(TemporalType.DATE)
	private Date birthDate; //req
	private String address;
	private String phone;
	private String email;
	@NotNull
	private String qualification; //req
	
	@JoinColumn(name="organizationId")
	@ManyToOne
	private OrganizationEntity organization;
	
	
	
	public MedicEntity(Long medicId, String medicCode, String firstname, String lastname, Gender gender, Date birthDate,
			String address, String email, String phone, String qualification) {
		super();
		this.medicId = medicId;
		
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
	
	public MedicEntity() {
		
	}
	
	public Nulls setNull() {
		this.organization = null;
		return null;
	}
	
	
	public Long getMedicId() {
		return medicId;
	}
	public void setMedicId(Long medicId) {
		this.medicId = medicId;
	}
	public String getMedicCode() {
		return medicCode;
	}
//	public void setMedicCode(String medicCode) {
//		MedicCode = medicCode;
//	}
	
	
	
	
	public String getFirstname() {
		return firstname;
	}
	public String getQualification() {
		return qualification;
	}

	public void setQualification(String qualification) {
		this.qualification = qualification;
	}

	public OrganizationEntity getOrganization() {
		return organization;
	}

	public void setOrganization(OrganizationEntity organization) {
		this.organization = organization;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Boolean getActive() {
		return active;
	}
	public void setActive(Boolean active) {
		this.active = active;
	}
	@JsonSetter(contentNulls = Nulls.AS_EMPTY )
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	
	@JsonSetter(contentNulls = Nulls.AS_EMPTY )
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
	
	
	
	
	
	@Override
	public int hashCode() {
		return Objects.hash(medicCode, medicId, address, birthDate, phone, email, firstname, phone, gender, lastname,
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
		return Objects.equals(medicCode, other.medicCode) && Objects.equals(medicId, other.medicId)
				&& Objects.equals(address, other.address) && Objects.equals(birthDate, other.birthDate)
				&& Objects.equals(phone, other.phone) && Objects.equals(email, other.email)
				&& Objects.equals(firstname, other.firstname) && Objects.equals(gender, other.gender)
				&& Objects.equals(lastname, other.lastname) && Objects.equals(qualification, other.qualification);
	}
	@Override
	public String toString() {
		return "MedicCode=" + medicCode + ", firstname=" + firstname
				+ ", lastname=" + lastname + ", gender=" + gender + ", birthDate=" + birthDate + ", address=" + address
				+ ", email=" + email + ", phone=" + phone + ", qualification=" + qualification + "]";
	}
	
	
	
	
	
	
	
	
	
	//String uniqueID = "MED-"+UUID.randomUUID().toString();
}
