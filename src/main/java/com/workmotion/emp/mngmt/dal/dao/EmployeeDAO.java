package com.workmotion.emp.mngmt.dal.dao;

import java.util.Optional;

import com.workmotion.emp.mngmt.dal.entity.Employee;

public interface EmployeeDAO {

	Employee save(Employee employee);

	Optional<Employee> findById(Long empId);

}
