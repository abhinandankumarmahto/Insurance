package com.avit.enums;

public enum AppConstants
{
	EMPTY_STR(""),
	PLAN_SUCC("plan-save-succ"),
	PLAN_FAIL("plan-fail"),
	PLAN_UPDATED("plan-updated-succ"),
	PLAN_UPDATED_FAIL("plan-updated-fail"),
	PLAN_DELETED("plan-deleted-succ"),
	PLAN_DELETED_FAIL("plan-deleted-fail"),
	PLAN_STATUS_CHANGE("plan-status-change"),
	PLAN_STATUS_CHANGE_FAIL("plan-status-changeFail");
	
	
	
	private String value;
	
	private AppConstants(String value) 
	{
		this.value=value;
	}
	
	public String getValue()
	{
		return value;
	}
}