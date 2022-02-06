package com.workmotion.emp.mngmt.processor;

import org.springframework.stereotype.Service;

import com.workmotion.emp.mngmt.dal.entity.Employee;
import com.workmotion.emp.mngmt.model.event.Events;
import com.workmotion.emp.mngmt.model.state.EmployeeStates;
import com.workmotion.emp.mngmt.model.state.SecurityCheckStates;
import com.workmotion.emp.mngmt.model.state.WorkPermitStates;

@Service
public class StateProcessorImpl implements StateProcessor {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean processEmployeeState(Employee employee, Events event) {
		boolean updated = false;
		switch (employee.getState()) {
			case ADDED:
				if (Events.CHECKING == event) {
					employee.setState(EmployeeStates.IN_CHECK);
					employee.setSecurityCheckState(SecurityCheckStates.SECURITY_CHECK_STARTED);
					employee.setWorkPermitState(WorkPermitStates.WORK_PERMIT_CHECK_STARTED);
					updated = true;
				}
				break;
			case IN_CHECK:
				updated = processInCheckEmployee(employee, event);
				break;
			case APPROVED:
				if (Events.ACTIVATE == event) {
					employee.setState(EmployeeStates.ACTIVE);
					updated = true;
				}
				break;
			default:
				break;
		}
		return updated;
	}

	/**
	 * Process the <code>IN_CHECK</code> employee state, returns <code>true</code>
	 * if the state is updated, <code>false</code> otherwise
	 * 
	 * @param employee The employee that the event is going to be processed upon
	 * @param event    The event to be processed on the employee
	 * @return <code>true</code> if the state is updated, <code>false</code>
	 *         otherwise
	 */
	private boolean processInCheckEmployee(Employee employee, Events event) {
		boolean updated = false;
		if (Events.FINISH_SECURITY_CHECK == event) {
			if (SecurityCheckStates.SECURITY_CHECK_STARTED == employee.getSecurityCheckState()) {
				employee.setSecurityCheckState(SecurityCheckStates.SECURITY_CHECK_FINISHED);
				updated = true;
			}
		} else if (Events.FINISH_WORK_PERMIT_CHECK == event) {
			if (WorkPermitStates.WORK_PERMIT_CHECK_STARTED == employee.getWorkPermitState()) {
				employee.setWorkPermitState(WorkPermitStates.WORK_PERMIT_CHECK_FINISHED);
				updated = true;
			}
		}

		if (SecurityCheckStates.SECURITY_CHECK_FINISHED == employee.getSecurityCheckState() && WorkPermitStates.WORK_PERMIT_CHECK_FINISHED == employee.getWorkPermitState()) {
			employee.setState(EmployeeStates.APPROVED);
		}
		return updated;
	}

}
