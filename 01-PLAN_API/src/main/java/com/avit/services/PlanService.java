package com.avit.services;

import java.util.List;
import java.util.Map;

import com.avit.entity.Plan;

public interface PlanService 
{
	public Map<Integer,String> getPlanCatogories();
	public boolean savePlan(Plan plan);
	public List<Plan> getAllPlan();
	public Plan getPlanById(Integer PlanId);
	public boolean UpdatePlan(Plan plan);
	public boolean deletePlanById(Integer PlanId);
	public boolean planStatusChange(Integer PlanId, String status);
}
