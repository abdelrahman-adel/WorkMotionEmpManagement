package com.workmotion.emp.mngmt.mapper;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.workmotion.emp.mngmt.dal.entity.Employee;
import com.workmotion.emp.mngmt.model.EmployeeDetails;

@Service
public class EmployeeMapperImpl implements EmployeeMapper {

	@Override
	public Employee mapToEntity(EmployeeDetails employeeDetails) {
		Employee employee = new Employee();
		Optional.ofNullable(employeeDetails.getId()).ifPresent(id -> employee.setId(id));
		employee.setCompanyId(employeeDetails.getCompanyId());
		employee.setName(employeeDetails.getName());
		employee.setPhone(employeeDetails.getPhone());
		employee.setAddress(employeeDetails.getAddress());
		employee.setEmail(employeeDetails.getEmail());
		employee.setState(employeeDetails.getState());
		return employee;
	}

	@Override
	public EmployeeDetails mapFromEntity(Employee employee) {
		EmployeeDetails employeeDetails = new EmployeeDetails();
		employeeDetails.setId(employee.getId());
		employeeDetails.setCompanyId(employee.getCompanyId());
		employeeDetails.setName(employee.getName());
		employeeDetails.setPhone(employee.getPhone());
		employeeDetails.setAddress(employee.getAddress());
		employeeDetails.setEmail(employee.getEmail());
		employeeDetails.setState(employee.getState());
		return employeeDetails;
	}

}
