package it.eng.lukapendelj.entity;

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

	
	
}
