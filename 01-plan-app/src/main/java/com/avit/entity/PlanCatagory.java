package com.avit.entity;

import java.time.LocalDate;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "PLAN_CATOGORY")
public class PlanCatagory {
	@Column(name = "category_id")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer categoryId;

	@Column(name = "category_name")
	private String categoryName;

	@Column(name = "Active_Switch")
	private String ActiveSw;

	@Column(name = "plan_catogory_id")
	private Integer planCatogoryId;

	@Column(name = "created_date", updatable = false)
	@CreationTimestamp
	private LocalDate createdDate;

	@Column(name = "updaopted_date", insertable = false)
	@UpdateTimestamp
	private LocalDate updatedDate;

}
