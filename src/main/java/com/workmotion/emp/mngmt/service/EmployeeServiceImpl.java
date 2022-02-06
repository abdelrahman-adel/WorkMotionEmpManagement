package com.workmotion.emp.mngmt.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.workmotion.emp.mngmt.dal.dao.EmployeeDAO;
import com.workmotion.emp.mngmt.dal.entity.Employee;
import com.workmotion.emp.mngmt.mapper.EmployeeMapper;
import com.workmotion.emp.mngmt.model.CreateEmployeeRq;
import com.workmotion.emp.mngmt.model.EmployeeDetails;
import com.workmotion.emp.mngmt.model.UpdateStateRq;
import com.workmotion.emp.mngmt.model.state.EmployeeStates;
import com.workmotion.emp.mngmt.processor.StateProcessor;

@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeDAO employeeDAO;

	@Autowired
	private EmployeeMapper mapper;

	@Autowired
	private StateProcessor stateProcessor;

	@Override
	public EmployeeDetails createEmployee(CreateEmployeeRq createEmployeeRq) {
		EmployeeDetails employeeDetails = createEmployeeRq.getEmployee();
		employeeDetails.setState(EmployeeStates.ADDED);

		Employee employee = mapper.mapToEntity(employeeDetails);

		return mapper.mapFromEntity(employeeDAO.save(employee));
	}

	@Transactional(readOnly = true)
	@Override
	public EmployeeDetails getEmployee(Long empId) {
		return employeeDAO.findById(empId).map(mapper::mapFromEntity).orElseThrow();
	}

	@Override
	public EmployeeDetails updateEmployeeState(Long empId, UpdateStateRq updateStateRq) {
		Employee employee = employeeDAO.findById(empId).orElseThrow();
		stateProcessor.processEmployeeState(employee, updateStateRq.getEvent());
		return mapper.mapFromEntity(employeeDAO.save(employee));
	}

}
