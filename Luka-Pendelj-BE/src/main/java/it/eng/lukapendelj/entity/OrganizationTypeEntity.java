package it.eng.lukapendelj.entity;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="OrganizationType")
public class OrganizationTypeEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long organizationTypeId;
	private String name;
	
	public OrganizationTypeEntity(Long id, String name) {
		super();
		this.organizationTypeId = id;
		this.name = name;
	}
	
	public OrganizationTypeEntity() {
	
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

	@Override
	public int hashCode() {
		return Objects.hash(name, organizationTypeId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OrganizationTypeEntity other = (OrganizationTypeEntity) obj;
		return Objects.equals(name, other.name) && Objects.equals(organizationTypeId, other.organizationTypeId);
	}

	@Override
	public String toString() {
		return "OrganizationTypeEntity [organizationTypeId=" + organizationTypeId + ", name=" + name + "]";
	}
	
	
	
	
	
}
