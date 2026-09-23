package com.avit.serviceImpl;

import java.awt.Color;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.openpdf.text.Document;
import org.openpdf.text.Font;
import org.openpdf.text.FontFactory;
import org.openpdf.text.PageSize;
import org.openpdf.text.Paragraph;
import org.openpdf.text.Phrase;
import org.openpdf.text.pdf.PdfPCell;
import org.openpdf.text.pdf.PdfPTable;
import org.openpdf.text.pdf.PdfWriter;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.avit.entity.EligibilityDetails;
import com.avit.reponses.SearchResponse;
import com.avit.repsitories.EligibilityRepo;
import com.avit.request.SearchRequest;
import com.avit.services.ReportService;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReportServiceImpl implements ReportService {

	private final EligibilityRepo eligibilityRepo;

	@Override
	public List<String> getUniquePlanName() {
		List<String> name = eligibilityRepo.findDistinctName();
		return name;
	}

	@Override
	public List<String> getUniquePlanStatus() {
		List<String> distinctStatus = eligibilityRepo.findDistinctStatus();
		return distinctStatus;
	}

	@Override
	public List<SearchResponse> search(SearchRequest request) {

		EligibilityDetails queryBuilder = new EligibilityDetails();
		String planName = request.getPlanName();
		if (planName != null && !planName.equals(""))
			queryBuilder.setPlanName(planName);

		String planStatus = request.getPlanStatus();
		if (planStatus != null && !planStatus.equals(""))
			queryBuilder.setPlanStatus(planStatus);

		LocalDate planStartDate = request.getPlanStartDate();
		if (planStartDate != null)
			queryBuilder.setPlanStartDate(planStartDate);

		LocalDate planEndDate = request.getPlanEndDate();
		if (planEndDate != null)
			queryBuilder.setPlanEndDate(planEndDate);

		List<EligibilityDetails> entities = eligibilityRepo.findAll();

		List<SearchResponse> response = new ArrayList<>();
		entities.forEach(entity -> {
			SearchResponse sr = new SearchResponse();
			BeanUtils.copyProperties(entity, sr);
			response.add(sr);
		});
		return response;
	}

	@Override
	public void generateExcel(HttpServletResponse response)throws Exception {
		List<EligibilityDetails> entities = eligibilityRepo.findAll();
		HSSFWorkbook hssfWorkbook = new HSSFWorkbook();
		HSSFSheet sheet = hssfWorkbook.createSheet();
		HSSFRow headerRow = sheet.createRow(0);
		headerRow.createCell(0).setCellValue("Name");
		headerRow.createCell(1).setCellValue("Mobile");
		headerRow.createCell(2).setCellValue("Gender");
		headerRow.createCell(3).setCellValue("SSN");
		headerRow.createCell(4).setCellValue("EMAIL");
		
		int i =1;
		for(EligibilityDetails entity :entities) 
		{
			HSSFRow datarow=sheet.createRow(i);
			datarow.createCell(0).setCellValue(entity.getName());
			datarow.createCell(1).setCellValue(entity.getMobile());
			datarow.createCell(2).setCellValue(entity.getGender());
			datarow.createCell(3).setCellValue(entity.getSsn());
			datarow.createCell(4).setCellValue(entity.getEmail());
			i++;
		
		}
		ServletOutputStream outputStream = response.getOutputStream();
		hssfWorkbook.write(outputStream);
		hssfWorkbook.close();
		outputStream.close(); 
	
		
		
		

	}

	@Override
	public void generatePdf(HttpServletResponse response) throws Exception 
	{
		List<EligibilityDetails> entities = eligibilityRepo.findAll();
		Document document = new Document(PageSize.A4);
		PdfWriter.getInstance(document, response.getOutputStream());
		document.open();
        Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        font.setSize(18);
        font.setColor(Color.BLUE);
        
        Paragraph p = new Paragraph("Search Report", font);
        p.setAlignment(Paragraph.ALIGN_CENTER);
        document.add(p);
        
        PdfPTable table = new PdfPTable(5);
        table.setWidthPercentage(100f);
        table.setWidths(new float[] {1.5f, 3.5f, 3.0f, 3.0f, 1.5f});
        table.setSpacingBefore(10);
        
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(Color.RED);
        cell.setPadding(5);
         
        cell.setPhrase(new Phrase("Name", font));
         
        table.addCell(cell);
         
        cell.setPhrase(new Phrase("E-mail", font));
        table.addCell(cell);
         
        cell.setPhrase(new Phrase("PhNo", font));
        table.addCell(cell);
         
        cell.setPhrase(new Phrase("Gender", font));
        table.addCell(cell);
         
        cell.setPhrase(new Phrase("Ssn", font));
        table.addCell(cell);       
        
        for(EligibilityDetails details:entities)
        {
        	table.addCell(details.getName());
        	table.addCell(details.getEmail());
        	table.addCell(String.valueOf(details.getMobile()));
        	table.addCell(String.valueOf(details.getGender()));
        	table.addCell(String.valueOf(details.getSsn()));
        	
    }
        document.add(table);
        document.close();
        
		
	}

}
