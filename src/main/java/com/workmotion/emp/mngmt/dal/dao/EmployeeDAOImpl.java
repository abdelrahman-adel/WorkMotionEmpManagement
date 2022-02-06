package com.workmotion.emp.mngmt.dal.dao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.workmotion.emp.mngmt.dal.entity.Employee;
import com.workmotion.emp.mngmt.dal.repository.EmployeeRepository;

@Service
public class EmployeeDAOImpl implements EmployeeDAO {

	@Autowired
	private EmployeeRepository repository;

	@Override
	public Employee save(Employee employee) {
		return repository.save(employee);
	}

	@Override
	public Optional<Employee> findById(Long empId) {
		return repository.findById(empId);
	}

}
