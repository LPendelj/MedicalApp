package it.eng.lukapendelj.entity;

import java.util.Date;
import java.util.List;
import java.util.Objects;
//import java.util.Set;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.PostPersist;
import javax.persistence.PrePersist;
//import javax.persistence.OneToMany;
//import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

//import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import com.fasterxml.jackson.annotation.JsonProperty;

//CHECK SQLDELETE!!!

@Entity
@Table(name="Examination")
//@Where(clause = "status!=entered-in-error")
@SQLDelete(sql = "UPDATE Examination SET status = 'entered-in-error' WHERE examination_id=?") //TO CHECK!!!
public class ExaminationEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long examinationId;
	
	
	private String examinationCode;
	@NotNull
	private String status = "planned";
	
	@ManyToOne
	@JoinColumn(name = "serviceId")
	@NotNull
	private ServiceTypeEntity serviceType;
	private String priority;
	private Date startDate;
	private Date endDate;
	private String diagnosis;
	
	// Another possible solution for creating ManyToMany relation?
	//	@JoinTable(name = "STUDENT_COURSE", joinColumns = { @JoinColumn(name = "STUDENT_ID") }, inverseJoinColumns = { @JoinColumn(name = "COURSE_ID") })

	
	
	//ADD Cascading! CHECK JsonProperty!
	@ManyToMany(cascade= {CascadeType.REMOVE})
	@JoinTable(name = "EXAMINATION_MEDIC", joinColumns = { @JoinColumn(name = "examination_id") }, inverseJoinColumns = { @JoinColumn(name = "medic_id") },
	uniqueConstraints =  @UniqueConstraint(columnNames = { "examination_id", "medic_id" }))
	@JsonProperty("medic")
	private List<MedicEntity> medicList;
	
	
	@ManyToOne
	@JoinColumn(name = "organizationId")
	private OrganizationEntity organization;
	@ManyToOne
	@JoinColumn(name = "patientId")
	private PatientEntity patient;
	
	//SHOULD be CHECKED!!!
	
	@PrePersist
	public void checkOrg()  {
		System.out.println("prepersist called");
		
		System.out.println("organizacija ID: " + this.organization.getOrganizationId());
		System.out.println("pacijent org ID: " + this.patient.getOrganization().getOrganizationId());
		System.out.println("medic org id: " +  this.medicList.get(0).getOrganization().getOrganizationId());
		
//		
//		if(this.patient.getOrganization().getOrganizationId() != this.organization.getOrganizationId()){
//			//Patient must have Examination in the same place where he is staying
//			throw new Exception();
//		}
		
		
		//System.out.println(this.med);
		
		/*
		 * if(this.name.equals("Bolnica3")) { throw new Exception(); }
		 */
	}
	
	@PostPersist
	public void checkMedics() {
		medicList.forEach(medic -> System.out.println("LekarID: " + medic.getOrganization().getOrganizationId()));
	}
	
	public ExaminationEntity() {
		super();
	}
	
	public Long getExaminationId() {
		return examinationId;
	}
	public void setExaminationId(Long examinationId) {
		this.examinationId = examinationId;
	}
	public String getExaminationCode() {
		return examinationCode;
	}
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public ServiceTypeEntity getServiceType() {
		return serviceType;
	}
	public void setServiceType(ServiceTypeEntity serviceType) {
		this.serviceType = serviceType;
	}
	public String getPriority() {
		return priority;
	}
	public void setPriority(String priority) {
		this.priority = priority;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public String getDiagnosis() {
		return diagnosis;
	}
	public void setDiagnosis(String diagnosis) {
		this.diagnosis = diagnosis;
	}
	public List<MedicEntity> getMedic() {
		return this.medicList;
	}
//	public void setMedic(MedicEntity medic) {
//		this.medic = medic;
//	}
	public OrganizationEntity getOrganization() {
		return organization;
	}
	public void setOrganization(OrganizationEntity organization) {
		this.organization = organization;
	}
	public PatientEntity getPatient() {
		return patient;
	}
	public void setPatient(PatientEntity patient) {
		this.patient = patient;
	}
	@Override
	public int hashCode() {
		return Objects.hash(diagnosis, endDate, examinationCode, examinationId, organization, patient, priority,
				serviceType, startDate, status);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ExaminationEntity other = (ExaminationEntity) obj;
		return Objects.equals(diagnosis, other.diagnosis) && Objects.equals(endDate, other.endDate)
				&& Objects.equals(examinationCode, other.examinationCode)
				&& Objects.equals(examinationId, other.examinationId) && Objects.equals(medicList, other.medicList)
				&& Objects.equals(organization, other.organization) && Objects.equals(patient, other.patient)
				&& Objects.equals(priority, other.priority) && Objects.equals(serviceType, other.serviceType)
				&& Objects.equals(startDate, other.startDate) && Objects.equals(status, other.status);
	}
	@Override
	public String toString() {
		return "ExaminationEntity [examinationCode=" + examinationCode
				+ ", status=" + status + ", serviceType=" + serviceType + ", priority=" + priority + ", startDate="
				+ startDate + ", endDate=" + endDate + ", diagnosis=" + diagnosis + ", medicList=" + medicList
				+ ", organization=" + organization + ", patient=" + patient + "]";
	}
	
	
	
	

}
