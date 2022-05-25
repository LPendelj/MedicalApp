package it.eng.lukapendelj.entity;

import java.util.Date;
import java.util.Objects;
import java.util.UUID;

public class PatientEntity {
	
	private Long patientId;
	private final String patientCode = "PAT-"+UUID.randomUUID().toString().substring(0, 13); 
	private Boolean active; //req
	private String firstname; //req
	private String lastname; //req
	private Gender gender;
	private Date birthDate; //req
	private String address;
	private String phone;
	private String email;
	private Boolean deceased;
	private String maritialStatus;
	
	private MedicEntity mainMedic;
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
