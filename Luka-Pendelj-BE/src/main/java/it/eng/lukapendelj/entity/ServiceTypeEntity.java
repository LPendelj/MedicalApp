package it.eng.lukapendelj.entity;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="ServiceType")
public class ServiceTypeEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long serviceId;
	private String serviceName;
	
	public ServiceTypeEntity(Long serviceId, String serviceName) {
		super();
		this.serviceId = serviceId;
		this.serviceName = serviceName;
	}
	
	public ServiceTypeEntity() {
		
	
	}

	public Long getServiceId() {
		return serviceId;
	}

	public void setServiceId(Long serviceId) {
		this.serviceId = serviceId;
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	@Override
	public String toString() {
		return "ServiceTypeEntity [serviceName=" + serviceName + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(serviceId, serviceName);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ServiceTypeEntity other = (ServiceTypeEntity) obj;
		return Objects.equals(serviceId, other.serviceId) && Objects.equals(serviceName, other.serviceName);
	}
	
	
	
	
}
