package it.eng.lukapendelj.entity;

import java.util.Date;
import java.util.Objects;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

//import org.hibernate.annotations.Check;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.hibernate.validator.constraints.Length;
import org.springframework.lang.Nullable;

import it.eng.lukapendelj.securityConfig.CustomDeserializer;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@Entity
@Table(name="Patient")
//@Check(constraints = "this.medicId==medic.organizationId")
@SQLDelete(sql = "UPDATE Patient SET active = false WHERE patient_id=?")
@Where(clause = "active=true")
public class PatientEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long patientId;
	
	@Length(min = 5)
	@Column(unique=true)
	private String patientCode; 
	
	@NotNull
	private Boolean active = true; //req
	
	@NotNull
	private String firstname; //req
	
	@NotNull
	private String lastname; //req
	
	@ManyToOne
	@JoinColumn(name="genderCode")
	private Gender gender;
	
	@NotNull
	@Temporal(TemporalType.DATE) 
	private Date birthDate; //req
	private String address;
	private String phone;
	
	@Email
	private String email;
	private Boolean deceased;
	private String maritalStatus;
	
	@JoinColumn(name="medicId", nullable = true)
	@ManyToOne
	@JsonDeserialize(using = CustomDeserializer.class)
	private MedicEntity mainMedic = null;
	
	@JoinColumn(name="organizationId")
	@ManyToOne
	private OrganizationEntity organization;
	

	
	
	
	
	
	public PatientEntity(){	}

	public PatientEntity(Long patientId, @Length(min = 5) String patientCode, @NotNull Boolean active,
			@NotNull String firstname, @NotNull String lastname, Gender gender, @NotNull Date birthDate, String address,
			String phone, @Email String email, Boolean deceased, String maritalStatus, @Nullable MedicEntity mainMedic,
			@Nullable OrganizationEntity organization) {
		super();
		this.patientId = patientId;
		this.patientCode = patientCode;
		this.active = active;
		this.firstname = firstname;
		this.lastname = lastname;
		this.gender = gender;
		this.birthDate = birthDate;
		this.address = address;
		this.phone = phone;
		this.email = email;
		this.deceased = deceased;
		this.maritalStatus = maritalStatus;
		this.mainMedic = mainMedic;
		this.organization = organization;
	}

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
	
	
	public MedicEntity getMainMedic() {
		return mainMedic;
	}
	
	//@JsonSetter(contentNulls = Nulls.AS_EMPTY )
	public void setMainMedic(MedicEntity mainMedic) {
		this.mainMedic = mainMedic;
	}

	public OrganizationEntity getOrganization() {
		return organization;
	}

	public void setOrganization(OrganizationEntity organization) {
		this.organization = organization;
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

	public String getMaritalStatus() {
		return maritalStatus;
	}

	public void setMaritialStatus(String maritalStatus) {
		this.maritalStatus = maritalStatus;
	}

	@Override
	public int hashCode() {
		return Objects.hash(active, address, birthDate, deceased, email, firstname, gender, lastname, mainMedic,
				maritalStatus, organization, patientCode, patientId, phone);
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
				&& Objects.equals(mainMedic, other.mainMedic) && Objects.equals(maritalStatus, other.maritalStatus)
				&& Objects.equals(organization, other.organization) && Objects.equals(patientCode, other.patientCode)
				&& Objects.equals(patientId, other.patientId) && Objects.equals(phone, other.phone);
	}

	@Override
	public String toString() {
		return "PatientEntity [patientCode=" + patientCode + ", active=" + active + ", firstname=" + firstname
				+ ", lastname=" + lastname + ", gender=" + gender + ", birthDate=" + birthDate + ", address=" + address
				+ ", phone=" + phone + ", email=" + email + ", deceased=" + deceased + ", maritalStatus="
				+ maritalStatus + ", mainMedic=" + mainMedic.getFirstname() + " " + mainMedic.getLastname() + ", organization=" + organization.getName() + "]";
	}
	
	
	
	

}
