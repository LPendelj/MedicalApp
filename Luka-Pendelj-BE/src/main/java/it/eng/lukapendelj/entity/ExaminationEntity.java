package it.eng.lukapendelj.entity;

import java.util.Date;
import java.util.Objects;
import java.util.UUID;

public class ExaminationEntity {
	
	private Long examinationId;
	private final String examinationCode = "EXA-"+UUID.randomUUID().toString();
	private String status;
	private ServiceType serviceType;
	private String priority;
	private Date startDate;
	private Date endDate;
	private String diagnosis;
	
	private MedicEntity medic;
	private OrganizationEntity organization;
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
	public ServiceType getServiceType() {
		return serviceType;
	}
	public void setServiceType(ServiceType serviceType) {
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
	public MedicEntity getMedic() {
		return medic;
	}
	public void setMedic(MedicEntity medic) {
		this.medic = medic;
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
	public void setPatient(PatientEntity patient) {
		this.patient = patient;
	}
	@Override
	public int hashCode() {
		return Objects.hash(diagnosis, endDate, examinationCode, examinationId, medic, organization, patient, priority,
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
				&& Objects.equals(examinationId, other.examinationId) && Objects.equals(medic, other.medic)
				&& Objects.equals(organization, other.organization) && Objects.equals(patient, other.patient)
				&& Objects.equals(priority, other.priority) && Objects.equals(serviceType, other.serviceType)
				&& Objects.equals(startDate, other.startDate) && Objects.equals(status, other.status);
	}
	@Override
	public String toString() {
		return "ExaminationEntity [examinationId=" + examinationId + ", examinationCode=" + examinationCode
				+ ", status=" + status + ", serviceType=" + serviceType + ", priority=" + priority + ", startDate="
				+ startDate + ", endDate=" + endDate + ", diagnosis=" + diagnosis + ", medic=" + medic
				+ ", organization=" + organization + ", patient=" + patient + "]";
	}
	
	
	
	

}
