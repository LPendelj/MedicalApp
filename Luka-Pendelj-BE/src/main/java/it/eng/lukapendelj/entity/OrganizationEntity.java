package it.eng.lukapendelj.entity;

//import java.sql.SQLInput;
//import java.sql.SQLException;
//import java.util.List;
import java.util.Objects;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PostUpdate;
import javax.persistence.PrePersist;
import javax.persistence.PreRemove;
//import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.jpa.repository.Query;

@Entity
@Table(name="Organization", uniqueConstraints = @UniqueConstraint(columnNames = {"name", "organizationCode" }))
@SQLDelete(sql = "UPDATE organization SET active = false WHERE organization_id=?")  //proveriti!!!!
@Where(clause = "active=true")
public class OrganizationEntity {
	

	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long organizationId;
	
	//@Length(min = 5)
	private String organizationCode;
	@NotNull
	private Boolean active = true; //req
	
	//napraviti entity
	@ManyToOne
	@JoinColumn(name="organizationTypeId")
	@NotNull
	private OrganizationTypeEntity organizationType; //req
	@NotNull
	@Length(min = 5)
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
	
//	@PreRemove
//	@Query("UPDATE medic JOIN ORGANIZATION ON medic.organization_id = organization.organization_id SET medic.organization_id=NULL WHERE organization.active = FALSE")
//	public void checkEmployees() {
//		System.out.println("Check employees called!");
//	}
	
	
//	@PrePersist
//	public void check() throws Exception {
//		System.out.println("prepersist called");
//		
//		System.out.println(this.organizationType.getId());
//		//System.out.println(this.med);
//		
//		/*
//		 * if(this.name.equals("Bolnica3")) { throw new Exception(); }
//		 */
//	}
	
//	@PostUpdate
//	public void setEmplyeesOrgToNull() {
//		if(this.active==false) {
//			
//		}
//	}

	public Long getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(Long organizationId) {
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
