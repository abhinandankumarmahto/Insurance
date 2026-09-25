package com.avit.bindings;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User 
{
	private String fullname;
	private String email;
	private String gender;
	private Long mobile;
	private LocalDate dob;
	private Long ssn;
	
}
