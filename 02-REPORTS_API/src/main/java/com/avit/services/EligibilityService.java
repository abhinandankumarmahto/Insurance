package com.avit.services;

import java.util.List;
import com.avit.reponses.SearchResponse;
import com.avit.request.SearchRequest;

import jakarta.servlet.http.HttpServletResponse;


public interface EligibilityService 
{
	public List<String> getUniquePlanName();
	public List<String> getUniquePlanStatus();
	public List<SearchResponse> search(SearchRequest request);
	public void generateExcel(HttpServletResponse response);
	public void generatePdf(HttpServletResponse response);
	
	
}
