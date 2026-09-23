package com.avit.entity;
import java.time.LocalDate;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "eligibility_table")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EligibilityDetails {
	@Id
	private Integer id;
	private String name;
	private Long mobile;
	private String email;
	private Character gender;
	private Long ssn;
	private String planName;
	private String planStatus;
	private LocalDate planStartDate;
	private LocalDate planEndDate;
	private LocalDate planCreatedDate;
	private LocalDate planUptedDate;
	private String createdBy;
	private String updatedBy;

}
