package com.workmotion.emp.mngmt.model;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

import com.workmotion.emp.mngmt.model.state.EmployeeStates;

import lombok.Data;

@Data
public class EmployeeDetails {

	@Null(message = "ID is to be generated")
	private Long id;

	@NotNull(message = "You must provide a company")
	private Long companyId;

	@NotBlank(message = "You must provide employee's name")
	private String name;

	private String address;

	@NotBlank(message = "You must provide employee's phone")
	private String phone;

	@NotBlank(message = "You must provide employee's email")
	@Email(message = "Email is not in a valid format")
	private String email;

	private EmployeeStates state;

}
