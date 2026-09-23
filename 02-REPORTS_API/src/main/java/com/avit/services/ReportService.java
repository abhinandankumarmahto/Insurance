package com.avit.services;

import java.util.List;
import com.avit.reponses.SearchResponse;
import com.avit.request.SearchRequest;

import jakarta.servlet.http.HttpServletResponse;


public interface ReportService 
{
	public List<String> getUniquePlanName();
	public List<String> getUniquePlanStatus();
	public List<SearchResponse> search(SearchRequest request);
	public void generateExcel(HttpServletResponse response) throws Exception;
	public void generatePdf(HttpServletResponse response) throws Exception;
	
	
}
