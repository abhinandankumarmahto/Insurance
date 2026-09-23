package com.avit.repsitories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.avit.entity.EligibilityDetails;

public interface EligibilityRepo extends JpaRepository<EligibilityDetails, Integer> 
{
	@Query("select distinct (planName) from EligibilityDetails")
	public List<String> findDistinctName();
	@Query("select distinct (planStatus)  from EligibilityDetails")
	public List<String> findDistinctStatus();
}
