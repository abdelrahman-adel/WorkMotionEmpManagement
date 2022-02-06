package com.workmotion.emp.mngmt.mapper;

import com.workmotion.emp.mngmt.dal.entity.Employee;
import com.workmotion.emp.mngmt.model.EmployeeDetails;

public interface EmployeeMapper {

	Employee mapToEntity(EmployeeDetails employeeDetails);

	EmployeeDetails mapFromEntity(Employee employee);

}
