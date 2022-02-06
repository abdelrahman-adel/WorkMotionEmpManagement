package com.workmotion.emp.mngmt.service;

import com.workmotion.emp.mngmt.model.CreateEmployeeRq;
import com.workmotion.emp.mngmt.model.EmployeeDetails;
import com.workmotion.emp.mngmt.model.UpdateStateRq;

public interface EmployeeService {

	EmployeeDetails createEmployee(CreateEmployeeRq createEmployeeRq);

	EmployeeDetails getEmployee(Long empId);

	EmployeeDetails updateEmployeeState(Long empId, UpdateStateRq employeeState);

}
