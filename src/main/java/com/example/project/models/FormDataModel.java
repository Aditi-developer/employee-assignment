package com.example.project.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "employee")
public class FormDataModel {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "employee_id")
	@NotBlank(message = "employeeID should not be blank")
	@NotNull(message = "employeeID should not be null")
	@Size(min = 8, message = "employeeID should be minimum 8 characters")
	public String employeeID;

	@Column(name = "employee_name")
	@NotBlank(message = "employeeName should not be blank")
	@NotNull(message = "employeeName should not be null")
	@Size(min = 5, message = "employeeName should be minimum 5 characters")
	public String employeeName;

	@Column(name = "mobile_number")
	@NotBlank(message = "mobile number should not be blank")
	@NotNull(message = "mobile number should not be null")
	@Size(min = 10, message = "mobile number should be 10 digits")
	public String mobileNumber;
	
}
