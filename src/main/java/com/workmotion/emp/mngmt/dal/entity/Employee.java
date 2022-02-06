package com.workmotion.emp.mngmt.dal.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.workmotion.emp.mngmt.model.state.EmployeeStates;
import com.workmotion.emp.mngmt.model.state.SecurityCheckStates;
import com.workmotion.emp.mngmt.model.state.WorkPermitStates;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Entity
public class Employee {

	@Id
	@GeneratedValue
	private long id;

	@Column(nullable = false)
	private Long companyId;

	@Column(nullable = false)
	private String name;

	private String address;

	private String phone;

	@Column(nullable = false, unique = true)
	private String email;

	@Enumerated(EnumType.STRING)
	private EmployeeStates state;

	@Enumerated(EnumType.STRING)
	private SecurityCheckStates securityCheckState;

	@Enumerated(EnumType.STRING)
	private WorkPermitStates workPermitState;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
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

	public EmployeeStates getState() {
		return state;
	}

	public void setState(EmployeeStates state) {
		this.state = state;
	}

	public SecurityCheckStates getSecurityCheckState() {
		return securityCheckState;
	}

	public void setSecurityCheckState(SecurityCheckStates securityCheckState) {
		this.securityCheckState = securityCheckState;
	}

	public WorkPermitStates getWorkPermitState() {
		return workPermitState;
	}

	public void setWorkPermitState(WorkPermitStates workPermitState) {
		this.workPermitState = workPermitState;
	}

}
