package it.eng.lukapendelj.entity;

import java.util.Date;
import java.util.Objects;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="Patient")
public class PatientEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long patientId;
	private final String patientCode = "PAT-"+UUID.randomUUID().toString().substring(0, 13); 
	
	@NotNull
	private Boolean active; //req
	@NotNull
	private String firstname; //req
	@NotNull
	private String lastname; //req
	@ManyToOne
	@JoinColumn(name="genderCode")
	private Gender gender;
	@NotNull
	private Date birthDate; //req
	private String address;
	private String phone;
	private String email;
	private Boolean deceased;
	private String maritialStatus;
	
	@JoinColumn(name="medicId")
	@ManyToOne
	private MedicEntity mainMedic;
	
	@JoinColumn(name="organizationId")
	@ManyToOne
	private OrganizationEntity organization;
	
	
	////String uniqueID = "PAT-"+UUID.randomUUID().toString();
	
	public PatientEntity(){	}

	public Long getPatientId() {
		return patientId;
	}

	public void setPatientId(Long patientId) {
		this.patientId = patientId;
	}

	public String getPatientCode() {
		return patientCode;
	}

//	public void setPatientCode(String patientCode) {
//		this.patientCode = patientCode;
//	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public String getFirstname() {
		return firstname;
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

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Boolean getDeceased() {
		return deceased;
	}

	public void setDeceased(Boolean deceased) {
		this.deceased = deceased;
	}

	public String getMaritialStatus() {
		return maritialStatus;
	}

	public void setMaritialStatus(String maritialStatus) {
		this.maritialStatus = maritialStatus;
	}

	@Override
	public int hashCode() {
		return Objects.hash(active, address, birthDate, deceased, email, firstname, gender, lastname, mainMedic,
				maritialStatus, organization, patientCode, patientId, phone);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PatientEntity other = (PatientEntity) obj;
		return Objects.equals(active, other.active) && Objects.equals(address, other.address)
				&& Objects.equals(birthDate, other.birthDate) && Objects.equals(deceased, other.deceased)
				&& Objects.equals(email, other.email) && Objects.equals(firstname, other.firstname)
				&& Objects.equals(gender, other.gender) && Objects.equals(lastname, other.lastname)
				&& Objects.equals(mainMedic, other.mainMedic) && Objects.equals(maritialStatus, other.maritialStatus)
				&& Objects.equals(organization, other.organization) && Objects.equals(patientCode, other.patientCode)
				&& Objects.equals(patientId, other.patientId) && Objects.equals(phone, other.phone);
	}
	
	
	
	

}
