package it.eng.lukapendelj.entity;

//import java.util.List;
import java.util.Objects;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="Organization")
public class OrganizationEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long organizationId;
	
	private final String organizationCode = "ORG-"+UUID.randomUUID().toString().substring(0, 8);
	@NotNull
	private Boolean active; //req
	
	//napraviti entity
	@ManyToOne
	@JoinColumn(name="organizationTypeId")
	@NotNull
	private OrganizationTypeEntity organizationType; //req
	@NotNull
	private String name; //req
	private String address;
	private String phone;
	private String email;
	
	//private List<ExaminationEntity> examList; ??
	//private List<MedicEntity> employeesList;
	//private List<PatientEntity> patientsList;
	
	
	//String uniqueID = "ORG-"+UUID.randomUUID().toString();
	
	public OrganizationEntity(){
		
	}

	public Long getOrganizationID() {
		return organizationId;
	}

	public void setOrganizationID(Long organizationId) {
		this.organizationId = organizationId;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public OrganizationTypeEntity getOrganizationType() {
		return organizationType;
	}

	public void setOrganizationType(OrganizationTypeEntity organizationType) {
		this.organizationType = organizationType;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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
	
	
//	public List<ExaminationEntity> getExamList() {
//		return examList;
//	}
//
//	public void setExamList(List<ExaminationEntity> examList) {
//		this.examList = examList;
//	}
	
	
	public String getOrganizationCode() {
		return organizationCode;
	}

	//final
//	public void setOrganizationCode(String organizationCode) {
//		this.organizationCode = organizationCode;
//	}

	@Override
	public int hashCode() {
		return Objects.hash(active, address, email, name, organizationId, organizationType, phone);
	}
	
	

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OrganizationEntity other = (OrganizationEntity) obj;
		return Objects.equals(active, other.active) && Objects.equals(address, other.address)
				&& Objects.equals(email, other.email) && Objects.equals(name, other.name)
				&& Objects.equals(organizationId, other.organizationId)
				&& Objects.equals(organizationType, other.organizationType) && Objects.equals(phone, other.phone);
	}

	@Override
	public String toString() {
		return "OrganizationEntity [organizationId=" + organizationId + ", organizationCode=" + organizationCode
				+ ", active=" + active + ", organizationType=" + organizationType + ", name=" + name + ", address="
				+ address + ", phone=" + phone + ", email=" + email + "]";
	}


	
	
	
	
	
	
}
