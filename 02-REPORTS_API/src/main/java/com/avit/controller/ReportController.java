package com.avit.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.avit.reponses.SearchResponse;
import com.avit.request.SearchRequest;
import com.avit.serviceImpl.ReportServiceImpl;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class ReportController 
{
	private final ReportServiceImpl service;
	
	@GetMapping("/plans")
	public ResponseEntity<List<String>> getPlanName()
	{
		List<String> uniquePlanName = service.getUniquePlanName();
		return new ResponseEntity<>(uniquePlanName,HttpStatus.OK);
	}
	
	@GetMapping("/planStatus")
	public ResponseEntity<List<String>> getPlanStatus()
	{
		List<String> uniquePlanStatus = service.getUniquePlanStatus();
		return new ResponseEntity<>(uniquePlanStatus,HttpStatus.OK);
	}
	
	@PostMapping("/search")
	public ResponseEntity<List<SearchResponse>> search(@RequestBody SearchRequest request)
	{
		List<SearchResponse> search = service.search(request);
		
		return new ResponseEntity<List<SearchResponse>>(search,HttpStatus.OK);
	}
	
	@GetMapping("/excel")
	public void getExcel(HttpServletResponse response) throws Exception
	{
		response.setContentType("application/octet-stream");
		String headerKey="Content-Disposition";
		String headerValue="attachment; filename=data.xls";
		response.setHeader(headerKey, headerValue);
		service.generateExcel(response);
	}
	
	@GetMapping("/pdf")
    public void exportToPDF(HttpServletResponse response) throws Exception 
	{
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());
         
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=users_" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);
        service.generatePdf(response);
	}
}
