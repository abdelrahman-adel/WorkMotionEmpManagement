package com.workmotion.emp.mngmt.processor;

import com.workmotion.emp.mngmt.dal.entity.Employee;
import com.workmotion.emp.mngmt.model.event.Events;

public interface StateProcessor {

	/**
	 * Process the employee state, returns <code>true</code> if the state is
	 * updated, <code>false</code> otherwise
	 * 
	 * @param employee The employee that the event is going to be processed upon
	 * @param event    The event to be processed on the employee
	 * @return <code>true</code> if the state is updated, <code>false</code>
	 *         otherwise
	 */
	boolean processEmployeeState(Employee employee, Events event);

}
