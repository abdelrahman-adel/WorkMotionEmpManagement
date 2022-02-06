package com.workmotion.emp.mngmt.model;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import lombok.Getter;

@Getter
public class CreateEmployeeRq {

	@Valid
	@NotNull
	private EmployeeDetails employee;

}
