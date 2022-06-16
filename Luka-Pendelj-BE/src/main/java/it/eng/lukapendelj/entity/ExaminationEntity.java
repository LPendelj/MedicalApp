package it.eng.lukapendelj.entity;

import java.util.Date;
import java.util.List;
import java.util.Objects;
//import java.util.Set;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
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

import org.hibernate.annotations.Cascade;
//import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.hibernate.validator.constraints.Length;
import org.springframework.lang.Nullable;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.Nulls;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import it.eng.lukapendelj.securityConfig.CustomDeserializer;


@Entity
@Table(name="Examination")
@Where(clause = "status!='entered-in-error'")
@SQLDelete(sql = "UPDATE Examination SET status = 'entered-in-error' WHERE examination_id=?") 
public class ExaminationEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long examinationId;
	
	@Length(min = 5)
	@Column(unique=true)
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

	
	@ManyToMany
	@JoinTable(name = "EXAMINATION_MEDIC", joinColumns = { @JoinColumn(name = "examination_id") }, inverseJoinColumns = { @JoinColumn(name = "medic_id") },
	uniqueConstraints =  @UniqueConstraint(columnNames = { "examination_id", "medic_id" }))
	@JsonProperty("medicList")
	private List<MedicEntity> medicList;
	
	
	@ManyToOne
	@JoinColumn(name = "organizationId")
	private OrganizationEntity organization;
	@ManyToOne
	@JoinColumn(name = "patientId")
	private PatientEntity patient;
	
	
	

	
	
	public ExaminationEntity(Long examinationId, String examinationCode, @NotNull String status,
			 ServiceTypeEntity serviceType, String priority, Date startDate, Date endDate, String diagnosis,
			 List<MedicEntity> medicList, OrganizationEntity organization, @NotNull PatientEntity patient) {
		super();
		this.examinationId = examinationId;
		this.examinationCode = examinationCode;
		this.status = status;
		
		this.serviceType = serviceType;
		this.priority = priority;
		this.startDate = startDate;
		this.endDate = endDate;
		this.diagnosis = diagnosis;
		this.medicList = medicList;
		this.organization = organization;
		this.patient = patient;
	}

	public ExaminationEntity() {
		
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
//	public List<MedicEntity> getMedic() {
//		return this.medicList;
//	}
	
	
	
	public List<MedicEntity> getMedicList() {
		return medicList;
	}
	
	//@JsonSetter(contentNulls = Nulls.AS_EMPTY )
	public void setMedicList(List<MedicEntity> medicList) {
		this.medicList = medicList;
	}

	public void setExaminationCode(String examinationCode) {
		this.examinationCode = examinationCode;
	}

	public OrganizationEntity getOrganization() {
		return organization;
	}
	public void setOrganization(OrganizationEntity organization) {
		this.organization = organization;
	}
	public PatientEntity getPatient() {
		return patient;
	}
	
	//@JsonSetter(contentNulls = Nulls.AS_EMPTY )
	
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
