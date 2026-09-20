package com.avit.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.avit.entity.Plan;

public interface PlanRepo extends JpaRepository<Plan, Integer> {

}
