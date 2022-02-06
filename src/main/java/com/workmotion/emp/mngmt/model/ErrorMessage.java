package com.workmotion.emp.mngmt.model;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class ErrorMessage {

	private LocalDateTime timestamp = LocalDateTime.now();
	private String message;
}
