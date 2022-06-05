package it.eng.lukapendelj.entity;

import java.sql.SQLException;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

//import org.hibernate.annotations.Check;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.springframework.lang.Nullable;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.Nulls;

@Entity
@Table(name="Patient")
//@Check(constraints = "this.medicId==medic.organizationId")
@SQLDelete(sql = "UPDATE Patient SET active = false WHERE patient_id=?")
@Where(clause = "active=true")
public class PatientEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long patientId;
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
	private String email;
	private Boolean deceased;
	private String maritalStatus;
	
	@JoinColumn(name="medicId", nullable = true)
	@ManyToOne
	@Nullable
	private MedicEntity mainMedic;
	
	@JoinColumn(name="organizationId")
	@ManyToOne
	private OrganizationEntity organization;
	
	
	////String uniqueID = "PAT-"+UUID.randomUUID().toString();
	
	
//	  @PrePersist public void prePersist() throws SQLException {
//	  System.out.println("prepersist called");
//	  
//	  if(mainMedic.getOrganization().getOrganizationId()!=this.organization.
//	  getOrganizationId()) {
//	  
//	  throw new SQLException(); }
//	  
//	  }
	 
	
	
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
	
	
	public MedicEntity getMainMedic() {
		return mainMedic;
	}
	
	@JsonSetter(contentNulls = Nulls.AS_EMPTY )
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
