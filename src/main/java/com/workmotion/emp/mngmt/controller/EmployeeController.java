package com.workmotion.emp.mngmt.controller;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.workmotion.emp.mngmt.model.CreateEmployeeRq;
import com.workmotion.emp.mngmt.model.EmployeeDetails;
import com.workmotion.emp.mngmt.model.UpdateStateRq;
import com.workmotion.emp.mngmt.service.EmployeeService;

import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/employee")
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;

	@Operation(summary = "Creates an employee")
	@PostMapping
	public EmployeeDetails createEmployee(@Valid @RequestBody CreateEmployeeRq createEmployeeRq) {
		log.debug("EmployeeController.createEmployee, {}", createEmployeeRq);
		return employeeService.createEmployee(createEmployeeRq);
	}

	@Operation(summary = "Fetches employee by id")
	@GetMapping("/{empId}")
	public EmployeeDetails getEmployee(@PathVariable @NotNull Long empId) {
		log.debug("EmployeeController.getEmployee, {}", empId);
		return employeeService.getEmployee(empId);
	}

	@Operation(summary = "Update employee state by id")
	@PatchMapping("/{empId}/state")
	public EmployeeDetails updateEmployeeState(@PathVariable @NotNull Long empId, @Valid @RequestBody UpdateStateRq updateStateRq) {
		log.debug("EmployeeController.updateEmployeeState, {}, {}", empId, updateStateRq);
		return employeeService.updateEmployeeState(empId, updateStateRq);
	}
}
