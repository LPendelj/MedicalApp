package it.eng.lukapendelj.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="OrganizationType")
public class OrganizationTypeEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long organizationTypeId;
	private String name;
	
	public OrganizationTypeEntity(Long id, String name) {
		super();
		this.organizationTypeId = id;
		this.name = name;
	}
	public Long getId() {
		return organizationTypeId;
	}
	public void setId(Long id) {
		this.organizationTypeId = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
	
}
