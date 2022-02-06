package com.workmotion.emp.mngmt.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.request;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.workmotion.emp.mngmt.dal.entity.Employee;
import com.workmotion.emp.mngmt.dal.repository.EmployeeRepository;
import com.workmotion.emp.mngmt.model.state.EmployeeStates;

@SpringBootTest
@AutoConfigureMockMvc
class EmployeeControllerTest {

	private static ObjectMapper objectMapper = new ObjectMapper();

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private EmployeeRepository repository;

	@AfterEach
	public void cleanUp() {
		repository.deleteAll();
	}

	//// @formatter:off
	@Test
	public void testCreateEmployee_Success() throws Exception {
		mockMvc.perform(request(HttpMethod.POST, "/employee")
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(""
				+ "{\r\n" + 
				"    \"employee\": {\r\n" + 
				"        \"companyId\": 10,\r\n" + 
				"        \"email\": \"abdo@workmotion.com\",\r\n" + 
				"        \"name\": \"abdo\",\r\n" + 
				"        \"phone\": \"aliquip\",\r\n" + 
				"        \"address\": \"anim consectetur aliqua sint\"\r\n" + 
				"    }\r\n" + 
				"}")).andExpect(status().isOk());
	}

	@Test
	public void testCreateEmployee_DuplicateEmail() throws Exception {

		repository.save(Employee.builder()
				.companyId(10L)
				.email("abdo@workmotion.com")
				.name("abdo")
				.phone("01064444693")
				.address("Remaya")
				.state(EmployeeStates.ADDED)
				.build());

		mockMvc.perform(request(HttpMethod.POST, "/employee")
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(""
				+ "{\r\n" + 
				"    \"employee\": {\r\n" + 
				"        \"companyId\": 10,\r\n" + 
				"        \"email\": \"abdo@workmotion.com\",\r\n" + 
				"        \"name\": \"abdo\",\r\n" + 
				"        \"phone\": \"aliquip\",\r\n" + 
				"        \"address\": \"anim consectetur aliqua sint\"\r\n" + 
				"    }\r\n" + 
				"}")).andExpect(status().isConflict());
	}

	@Test
	public void testGetEmployee_Success() throws Exception {

		Employee expectedEmployee = Employee.builder()
				.companyId(10L)
				.email("abdo@workmotion.com")
				.name("abdo")
				.phone("01064444693")
				.address("Remaya")
				.state(EmployeeStates.ADDED)
				.build();
		expectedEmployee = repository.save(expectedEmployee);

		String response = mockMvc.perform(request(HttpMethod.GET, "/employee/" + expectedEmployee.getId()))
				.andExpect(status().isOk())
				.andReturn()
				.getResponse()
				.getContentAsString();

		Employee actualEmployee = objectMapper.readValue(response, Employee.class);

		assertEquals(expectedEmployee, actualEmployee);
	}

	@Test
	public void testUpdateEmployeeState_Success() throws Exception {

		Employee expectedEmployee = Employee.builder()
				.companyId(10L)
				.email("abdo@workmotion.com")
				.name("abdo")
				.phone("01064444693")
				.address("Remaya")
				.state(EmployeeStates.ADDED)
				.build();
		expectedEmployee = repository.save(expectedEmployee);

		String response = mockMvc.perform(request(HttpMethod.PATCH, String.format("/employee/%s/state", expectedEmployee.getId()))
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.content("{\"event\": \"CHECKING\"}"))
				.andExpect(status().isOk())
				.andReturn()
				.getResponse()
				.getContentAsString();

		expectedEmployee.setState(EmployeeStates.IN_CHECK);
		Employee actualEmployee = objectMapper.readValue(response, Employee.class);

		assertEquals(expectedEmployee, actualEmployee);
	}
	// @formatter:on
}
