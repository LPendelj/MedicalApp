package it.eng.lukapendelj.entity;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
//import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="Examination")
public class ExaminationEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long examinationId;
	
	
	private final String examinationCode = "EXA-"+UUID.randomUUID().toString();
	@NotNull
	private String status;
	
	@ManyToOne
	@JoinColumn(name = "serviceId")
	@NotNull
	private ServiceTypeEntity serviceType;
	private String priority;
	private Date startDate;
	private Date endDate;
	private String diagnosis;
	
	
	
	@ManyToMany
	@JoinColumn(name = "medicId")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private List<MedicEntity> medicList;
	
	
	@ManyToOne
	@JoinColumn(name = "organizationId")
	private OrganizationEntity organization;
	@ManyToOne
	@JoinColumn(name = "patientId")
	private PatientEntity patient;
	
	
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
		return "ExaminationEntity [examinationId=" + examinationId + ", examinationCode=" + examinationCode
				+ ", status=" + status + ", serviceType=" + serviceType + ", priority=" + priority + ", startDate="
				+ startDate + ", endDate=" + endDate + ", diagnosis=" + diagnosis + ", medicList=" + medicList
				+ ", organization=" + organization + ", patient=" + patient + "]";
	}
	
	
	
	

}
